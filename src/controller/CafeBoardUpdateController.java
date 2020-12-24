package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeBoardDao;
import vo.CafeBoardVo;
@WebServlet("/cafe/cafeBoardUpdate")
public class CafeBoardUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		CafeBoardVo cafeBoardVo = cafeBoardDao.getBoard(boardNum);
		
		String boardName = cafeBoardDao.getBoardName(boardNum);
		
		req.setAttribute("cafeBoardVo", cafeBoardVo);
		req.getRequestDispatcher(req.getContextPath()+"/cafe/CafeBoardUpdate.jsp?cafeNum="+cafeNum+"&boardNum="+boardNum+"&boardName="+boardName).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String boardName = req.getParameter("boardName");
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		cafeBoardDao.updateBoard(boardNum, boardName);
		
		req.getRequestDispatcher(req.getContextPath()+"/cafe/cafeBoardUpdate?cafeNum="+cafeNum).forward(req, resp);
	}
}
