package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeBoardCatDao;
import dao.CafeBoardDao;

@WebServlet("/cafe/cafeBoardCatDelete")
public class CafeBoardCatDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int boardCatNum = Integer.parseInt(req.getParameter("boardCatNum"));
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		CafeBoardCatDao cafeBoardCatDao = CafeBoardCatDao.getInstance();
		cafeBoardCatDao.deleteBoardCatNum(boardCatNum);
		
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		cafeBoardCatDao.deleteBoardCatNum(boardCatNum);
		
		req.getRequestDispatcher(req.getContextPath()+"/cafe/cafeBoardUpdate?cafeNum="+cafeNum).forward(req, resp);;
	}
}
