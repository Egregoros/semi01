package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.CafeListTableDao;
import dao.CafeMainPicDao;
import dao.CatTableDao;
import vo.CafeListVo;
import vo.CafeMainPicVo;
import vo.CatTableVo;
@WebServlet("/cafe/cafeMainPicUpdate")
public class CafeMainPicUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		Integer userNum = (Integer)session.getAttribute("userNum");
		
		CafeListTableDao listDao = new CafeListTableDao();
		CafeListVo listVo = listDao.getOne(cafeNum);
		
		
		if(session.getAttribute("userNum") == null) {
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
		} else if (userNum == listVo.getUserNum()) {
			req.setAttribute("userNum", userNum);
			req.setAttribute("listVo", listVo);
			req.getRequestDispatcher("/cafe/cafeMainPicUpdate.jsp").forward(req, resp);
		} else {
			req.setAttribute("errMsg", "운영자 혹은 관리자만 할수 있습니다.");
			req.getRequestDispatcher("/jsp/cafe-main.do?cafeNum="+cafeNum).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		Integer userNum = (Integer)session.getAttribute("userNum");
		
		CafeMainPicDao cafeMainPicDao = CafeMainPicDao.getInstance();
		CafeMainPicVo mainPicVo = null;
		CafeMainPicVo mainPicVo1 = null;
		int n = 0;
		
		String saveDir = getServletContext().getRealPath("/cafeMainPic");
		
		
		MultipartRequest mr = new MultipartRequest(
				req,
				saveDir,
				1024*1024*5,
				"utf-8",
				new DefaultFileRenamePolicy()
			);
		String cafeName = mr.getParameter("cafeName");
		String content = mr.getParameter("content");
		int cafeNum = Integer.parseInt(mr.getParameter("cafeNum"));
		n = cafeMainPicDao.delete(cafeNum);
		
		
		String orgFileName = mr.getOriginalFileName("cafePicName");
		String saveFileName = mr.getFilesystemName("cafePicName");
		File f = mr.getFile("cafePicName");
		long fileSize = f.length();
		f.delete();
		mainPicVo1 = new CafeMainPicVo(0, cafeNum, orgFileName, saveFileName, fileSize);
		cafeMainPicDao.insert(mainPicVo1);
		
		resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
	}
}
