package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeBoardCatDao;
import dao.CafeBoardDao;
import dao.CafeListTableDao;
import dao.CafeMainDao;
import dao.CafeMemberDao;
import dao.UserInfoDao;
import vo.CafeBoardCatVo;
import vo.CafeBoardVo;
import vo.CafeListVo;
import vo.CafeMemberVo;
import vo.CafeNavBoardVo;
import vo.CafeNavCatVo;
import vo.UserInfoVo;
@WebServlet("/cafe/cafeBoardUpdate")
public class CafeBoardUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userNum = (Integer)session.getAttribute("userNum");
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		req.setCharacterEncoding("utf-8");
		
		//카페 관리자 맞는지 확인용
		CafeListTableDao cafeListTableDao = new CafeListTableDao();
		CafeListVo cafeListVo = cafeListTableDao.getOne(cafeNum);
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfoVo userInfoVo = userInfoDao.getOne(userNum);
		
		CafeMemberDao cafeMemberDao = CafeMemberDao.getInstance();
		CafeMemberVo cafeMemberVo = cafeMemberDao.getcafeMemGradeNum(userNum, cafeNum);
		
		//카테고리 불러오기
		CafeBoardCatDao cafeBoardCatDao = CafeBoardCatDao.getInstance();
		ArrayList<CafeBoardCatVo> cafeBoardCatList = cafeBoardCatDao.getCafeCat(cafeNum);
		
		//게시판 불러오기
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		ArrayList<CafeBoardVo> cafeBoardList = cafeBoardDao.getList(cafeNum);
		
		CafeMainDao cafeMainDao = CafeMainDao.getInstance();
		ArrayList<CafeNavCatVo> cafeNavBoardList = cafeMainDao.getCafeNavList(cafeNum);
		
		if(cafeMemberVo.getCafeMemGradeNum() < 2) {
			req.setAttribute("cafeNum", cafeListVo.getCafeNum());
			req.setAttribute("cafeName", cafeListVo.getCafeName());
			req.setAttribute("cafeBoardCatList", cafeBoardCatList);
			req.setAttribute("cafeBoardList", cafeBoardList);
			req.setAttribute("cafeNavBoardList", cafeNavBoardList);
			req.getRequestDispatcher("/cafe/CafeBoardUpdate.jsp?cafeNum="+cafeNum).forward(req, resp);
		} else if (cafeMemberVo.getCafeMemGradeNum() > 1) {
			req.setAttribute("errMsg", "관리자가 아니라 접근 불가능한 메뉴입니다.");
			req.getRequestDispatcher("/jsp/cafe-main.do?cafeNum="+cafeNum).forward(req, resp);
		} else if (session.getAttribute("userNum") != null) {
			req.setAttribute("errMsg", "로그인이 필요한 메뉴입니다.");
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
		}
	}
	
}
