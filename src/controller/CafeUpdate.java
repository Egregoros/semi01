package controller;

import java.io.IOException;
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
public class CafeUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		String id = (String)session.getAttribute("id");
		UserInfoDao userDao = new UserInfoDao();
		UserInfoVo userVo = userDao.getOne(id);
		int userNum = Integer.parseInt((String)session.getAttribute("userNum"));
		
		CafeListTableDao cafeListDao = new CafeListTableDao();
		CafeListVo cafeListVo = cafeListDao.getOne(cafeNum);
		
		CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
		CafeMemberVo cafeMemVo = cafeMemDao.getcafeMemGradeNum(userNum, cafeNum);
		
		CatTableDao catTableDao = CatTableDao.getInstance();
		ArrayList<CatTableVo> catList = catTableDao.list();
		
		//get userNum from cafeNum
		if(session.getAttribute("id") == null) {
			resp.sendRedirect("/login/login.jsp");
		} else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() > 1) { // 운영자 이상의 등급을 갖고 잇으면 가능. 카페 넘버도 맞아야함. &&로 해주면 될듯.
			req.setAttribute("errMsg", "카페장 혹은 운영자가 아니므로 수정이 불가합니다.");
		} else if (userNum != cafeMemVo.getUserNum()) {
			req.setAttribute("errMsg", "카페 회원이 아닙니다. 카페 가입부터 해주세요.");
			resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
		} else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() <= 1) {
			req.setAttribute("cafeListVo", cafeListVo);
			req.setAttribute("catList", catList);
			req.getRequestDispatcher(req.getContextPath() + "/cafe/cafeUpdate.jsp").forward(req, resp);
		} else {
			req.setAttribute("errMsg", "잘못된 접근입니다.");
			resp.sendRedirect(req.getContextPath()+"/cafeList");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		
	}
}
