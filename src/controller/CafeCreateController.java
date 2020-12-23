package controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.CafeListTableDao;
import dao.CafeMainPicDao;
import dao.CafeMemGradeDao;
import dao.CafeMemberDao;
import dao.CatTableDao;
import dao.UserInfoDao;
import vo.CafeListVo;
import vo.CafeMainPicVo;
import vo.CafeMemGradeVo;
import vo.CafeMemberVo;
import vo.CatTableVo;
import vo.UserInfoVo;
@WebServlet("/cafe/cafeCreate")
public class CafeCreateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("userNum") != null) {
			req.setCharacterEncoding("utf-8");
			CatTableDao catDao = CatTableDao.getInstance();
			ArrayList<CatTableVo> catList = catDao.list();
			req.setAttribute("catList", catList);
			req.getRequestDispatcher("/cafe/cafeCreate.jsp").forward(req, resp);
		} else {
			req.setAttribute("needLogin", "�α����� �ʿ��� �۾��Դϴ�. �α������ּ���.");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		Integer userNum = (Integer)session.getAttribute("userNum");
		CafeListTableDao cafeListDao = new CafeListTableDao();
		String saveDir =  getServletContext().getRealPath("/cafeMainPic");
		
		
		MultipartRequest mr = new MultipartRequest(
					req,
					saveDir,
					1024*1024*5,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
		
		String cafeName = mr.getParameter("cafeName");
		String catName = mr.getParameter("catName");
		String content = mr.getParameter("content");
		String cafeMemNickName = mr.getParameter("cafeMemNickName");
		
		
		CatTableDao catDao = CatTableDao.getInstance();
		CatTableVo catVo = catDao.getCatVo(catName);
		UserInfoDao userDao = new UserInfoDao();
		UserInfoVo userVo = userDao.getOne(userNum);
		CafeListVo cafeListVo = new CafeListVo(0, 1, catVo.getCatNum(), cafeName, userVo.getUserNum(), content, null);
		int n = cafeListDao.insert(cafeListVo); // ī���ȣ return.
		
		
		if (n>0) {
			CafeMemGradeVo cafeMemGradeVo = new CafeMemGradeVo(n, 0, "������");
			CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
			cafeMemGradeDao.insert(cafeMemGradeVo);
			
			CafeMainPicDao cafeMainPicDao = CafeMainPicDao.getInstance();
			CafeMainPicVo cafeMainPicVo = null;
			
			CafeMemberVo cafeMemVo = new CafeMemberVo(userVo.getUserNum(), n, cafeMemNickName, 0, 1, null); 
			CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
			cafeMemDao.insert(cafeMemVo);
			
			String orgFileName = mr.getOriginalFileName("cafePicName");
			String saveFileName = mr.getFilesystemName("cafePicName");
			File f = mr.getFile("cafePicName");
			long fileSize = f.length();
			
			cafeMainPicVo = new CafeMainPicVo(0, n, orgFileName, saveFileName, fileSize);
			cafeMainPicDao.insert(cafeMainPicVo);
			resp.sendRedirect(req.getContextPath()+"/cafeList");
		} else {
			req.setAttribute("errMsg", "������ ���� ī�� ������ �Ұ��մϴ�.\n���Ŀ� �ٽ� �õ����ֽñ� �ٶ��ϴ�.");
			req.getRequestDispatcher(req.getContextPath()+"/cafe/cafeCreate").forward(req, resp);
		}
		
	}
}
