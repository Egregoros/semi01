package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMainDao;

@WebServlet("/commentInsert")
public class CommentInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int pageCount;
		try {
			pageCount=Integer.parseInt((String)req.getParameter("pageCount"));
		}catch(Exception e) {
			pageCount=10;
		}
		int cafeNum=Integer.parseInt(req.getParameter("cafeNum"));
		int postNum=Integer.parseInt(req.getParameter("postNum"));
		String comment = req.getParameter("comment");
		HttpSession session = req.getSession();
		int userNum;
		try {
			userNum=(int)session.getAttribute("userNum");
			CafeMainDao cmdao = CafeMainDao.getInstance();
			HashMap<String, String> userInfo = cmdao.getUserInfo(userNum, cafeNum);
			if(userInfo.get("isUser").equals("false")) {
				resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum+"&joinCafeUserNum="+userNum);
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
			userNum=-1;
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
			return;
		}
		CafeMainDao cmdao = CafeMainDao.getInstance();
		cmdao.insertComment(userNum, postNum, comment);
		resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum+"&postNum="+postNum+"&pageNum="+1+"&pageCount="+pageCount);
		return;
	}
}
