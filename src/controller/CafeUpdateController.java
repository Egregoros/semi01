package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeListTableDao;
import dao.CafeMemberDao;
import dao.CatTableDao;
import dao.UserInfoDao;
import vo.CafeListVo;
import vo.CafeMemberVo;
import vo.CatTableVo;
import vo.UserInfoVo;
@WebServlet("/cafe/cafeUpdate")
public class CafeUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		Integer userNum = (Integer)session.getAttribute("userNum");
		UserInfoDao userDao = new UserInfoDao();
		UserInfoVo userVo = userDao.getOne(userNum);
		String id = userVo.getId();
		
		CafeListTableDao cafeListDao = new CafeListTableDao();
		CafeListVo cafeListVo = cafeListDao.getOne(cafeNum);
		
		CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
		CafeMemberVo cafeMemVo = cafeMemDao.getcafeMemGradeNum(userNum, cafeNum);
		
		CatTableDao catTableDao = CatTableDao.getInstance();
		ArrayList<CatTableVo> catList = catTableDao.list();
		
		//get userNum from cafeNum
		if(session.getAttribute("userNum") == null) {
			resp.sendRedirect("/login/login.jsp");
		} else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() > 1) { // 운영자 이상의 등급을 갖고 잇으면 가능. 카페 넘버도 맞아야함. &&로 해주면 될듯.
			req.setAttribute("errMsg", "카페장 혹은 운영자가 아니므로 수정이 불가합니다.");
		} else if (userNum != cafeMemVo.getUserNum()) {
			req.setAttribute("errMsg", "카페 회원이 아닙니다. 카페 가입부터 해주세요.");
			resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
		} else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() <= 1) {
			req.setAttribute("cafeListVo", cafeListVo);
			req.setAttribute("catList", catList);
			req.getRequestDispatcher("/cafe/cafeUpdate.jsp?cafeNum="+cafeNum).forward(req, resp);
		} else {
			req.setAttribute("errMsg", "잘못된 접근입니다.");
			resp.sendRedirect(req.getContextPath()+"/cafeList");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		Integer userNum = (Integer)session.getAttribute("userNum");
	    int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		String cafeName = req.getParameter("cafeName");
		String catName = req.getParameter("catName");
		String content = req.getParameter("content");
		
		CafeListTableDao listDao = new CafeListTableDao();
		CafeListVo listVo = listDao.getOne(cafeNum);
		
		CatTableDao catDao = CatTableDao.getInstance();
		CatTableVo catVo = catDao.getCatVo(catName);
		
		UserInfoDao userDao = new UserInfoDao();
		UserInfoVo userVo = userDao.getOne(userNum);
		
		CafeListVo cafeListVo = new CafeListVo(cafeNum, listVo.getGradeNum(), catVo.getCatNum(), cafeName, userVo.getUserNum(), content, null);
		System.out.println(cafeNum);
		System.out.println(listVo.getGradeNum());
		System.out.println(catVo.getCatNum());
		System.out.println(cafeName);
		System.out.println(userVo.getUserNum());
		System.out.println(content);
		
		int n = listDao.update(cafeListVo);
		System.out.println(n);
		resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
	}
}
