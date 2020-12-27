package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemGradeDao;
@WebServlet("/cafe/CafeMemGradeDelete")
public class CafeMemGradeDelete extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		int cafeMemGradeNum = Integer.parseInt(req.getParameter("cafeMemGradeNum"));
		
		CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
		cafeMemGradeDao.deleteOne(cafeNum, cafeMemGradeNum);
	
	}
}
