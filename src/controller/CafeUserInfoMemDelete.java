package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemberDao;
@WebServlet("/cafe/cafeUserInfoMemDelete")
public class CafeUserInfoMemDelete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int userNum = Integer.parseInt(req.getParameter("userNum"));
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		CafeMemberDao cafeMemberDao = CafeMemberDao.getInstance();
		int n = cafeMemberDao.deleteCafeNumUserNum(userNum, cafeNum);

		resp.sendRedirect(req.getContextPath()+"/cafe/cafeUserInfoList?cafeNum="+cafeNum);
	}
}
