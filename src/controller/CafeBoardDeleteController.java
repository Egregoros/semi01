package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeBoardDao;
@WebServlet("/cafe/cafeBoardDelete")
public class CafeBoardDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		cafeBoardDao.delete(boardNum);
	
		req.getRequestDispatcher(req.getContextPath()+"/cafe/cafeBoardUpdate?cafeNum="+cafeNum).forward(req, resp);
	}
}
