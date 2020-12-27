package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemGradeDao;
@WebServlet("/cafe/CafeMemGradeUpdate")
public class CafeMemGradeUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		String cafeMemGradeName = req.getParameter("cafeMemGradeName");
		
		
		req.getRequestDispatcher("/cafe/CafeMemGradeUpdate.jsp?cafeNum="+cafeNum);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		String cafeMemGradeName = req.getParameter("cafeMemGradeName");
		int cafeMemGradeNum = Integer.parseInt(req.getParameter("cafeMemGradeNum"));
		
		CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
		int n = cafeMemGradeDao.update(cafeNum, cafeMemGradeName, cafeMemGradeNum);
		
		
		req.getRequestDispatcher("/cafe/CafeBoardMemGradeList?cafeNum="+cafeNum).forward(req, resp);
		
	}
}
