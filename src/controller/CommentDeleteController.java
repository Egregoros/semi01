package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CafeMainDao;
@WebServlet("/commentDelete")
public class CommentDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int cafeNum;
		int postNum;
		int commentNum;
		int pageCount;
		try {
			cafeNum=Integer.parseInt((String)req.getParameter("cafeNum"));
			postNum=Integer.parseInt((String)req.getParameter("postNum"));
			commentNum=Integer.parseInt((String)req.getParameter("commentNum"));
		}catch(Exception e) {
			resp.sendRedirect(req.getContextPath()+"/cafeList");
			cafeNum=0;
			postNum=0;
			commentNum=0;
		}
		try {
			pageCount=Integer.parseInt((String)req.getParameter("pageCount"));
		}catch(Exception e) {
			pageCount=10;
		}
		CafeMainDao cmdao = CafeMainDao.getInstance();
		cmdao.deleteComment(commentNum);
		resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum+"&postNum="+postNum+"&pageNum="+1+"&pageCount="+pageCount);
	}
}
