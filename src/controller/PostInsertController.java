package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMainDao;
@WebServlet("/postInsert")
public class PostInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int boardNum = Integer.parseInt((String)req.getParameter("boardNum"));
		int cafeNum = Integer.parseInt((String)req.getParameter("cafeNum"));
		int postCatNum = Integer.parseInt((String)req.getParameter("postCatNum"));
		String postTitle = (String)req.getParameter("postTitle");
		String postContent = (String)req.getParameter("postContent");
		HttpSession session = req.getSession();
		int userNum = (int)session.getAttribute("userNum");
		
		CafeMainDao cmdao = CafeMainDao.getInstance();
		cmdao.insertPost(userNum, boardNum, postTitle, postContent, postCatNum);
		resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
	}
}
