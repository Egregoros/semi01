package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemberDao;
import vo.MemberCafeListVo;
@WebServlet("/cafe/MemberCafeList")
public class MemberCafeListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int userNum = (Integer)session.getAttribute("userNum");
		System.out.println(userNum);
		
		CafeMemberDao cafeMemberDao = CafeMemberDao.getInstance();
		ArrayList<MemberCafeListVo> list = cafeMemberDao.getList(userNum);
		
		req.setAttribute("list", list);
		req.setAttribute("userNum", userNum);
		req.getRequestDispatcher("/cafe/MemberCafeList.jsp?userNum="+userNum).forward(req, resp);
	}
}
