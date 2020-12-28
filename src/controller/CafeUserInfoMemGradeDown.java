package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemberDao;
import vo.CafeMemberVo;
@WebServlet("/cafe/CafeUserInfoMemGradeDown")
public class CafeUserInfoMemGradeDown extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		int userNum = Integer.parseInt(req.getParameter("userNum"));
		
		CafeMemberDao cafeMemberDao = CafeMemberDao.getInstance();
		CafeMemberVo cafeMemberVo = cafeMemberDao.getOne(userNum);
		
		cafeMemberDao.oneGradeDown(cafeMemberVo);
		
		req.getRequestDispatcher("/cafe/cafeUserInfoList?cafeNum="+cafeNum).forward(req, resp);
	
	}
}
