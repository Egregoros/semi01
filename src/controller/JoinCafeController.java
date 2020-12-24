package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMainDao;

@WebServlet("/joinCafe")
public class JoinCafeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		int userNum=(int)session.getAttribute("userNum");
		int cafeNum=Integer.parseInt((String)req.getParameter("cafeNum"));
		String cafeNickName=(String)req.getParameter("cafeNickName");
		
		CafeMainDao cmdao = CafeMainDao.getInstance();
		cmdao.joinCafeMember(userNum, cafeNum, cafeNickName); 
		resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
		return;
	}
}
