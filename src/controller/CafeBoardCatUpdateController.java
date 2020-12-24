package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CafeBoardCatDao;
import vo.CafeBoardCatVo;

@WebServlet("/cafe/cafeBoardCatUpdate")
public class CafeBoardCatUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		int boardCatNum = Integer.parseInt(req.getParameter("boardCatNum"));
		
		CafeBoardCatDao cafeBoardCatDao = CafeBoardCatDao.getInstance();
		CafeBoardCatVo cafeBoardCatVo = cafeBoardCatDao.getOneBoardCatNum(boardCatNum);
		
		req.setAttribute("cafeBoardCatVo", cafeBoardCatVo);
	
		req.getRequestDispatcher(req.getContextPath()+"/cafe/CafeBoardCatUpdate.jsp?boardCatNum="+boardCatNum).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int boardCatNum = Integer.parseInt(req.getParameter("boardCatNum"));
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		String catName = req.getParameter("catName");
		
		CafeBoardCatDao cafeBoardCatDao = CafeBoardCatDao.getInstance();
		cafeBoardCatDao.updateBoardCatNum(boardCatNum, catName);
		
		req.getRequestDispatcher(req.getContextPath()+"/cafe/cafeBoardUpdate?cafeNum="+cafeNum).forward(req, resp);
		
	}
}
