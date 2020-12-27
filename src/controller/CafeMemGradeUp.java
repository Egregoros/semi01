package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemGradeDao;
@WebServlet("/cafe/CafeMemGradeUp")
public class CafeMemGradeUp extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		int cafeMemGradeNum = Integer.parseInt(req.getParameter("cafeMemGradeNum"));
		String cafeMemGradeName = req.getParameter("cafeMemGradeName");
		
		CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
		cafeMemGradeDao.getMaxNum(cafeNum);
		
		if(cafeMemGradeNum == 0) {
			resp.sendRedirect(req.getContextPath()+"/cafe/CafeBoardMemGradeList?cafeNum="+cafeNum);
		} else {
			cafeMemGradeDao.memGradeUp(cafeNum, cafeMemGradeNum, cafeMemGradeName);
			req.getRequestDispatcher("/cafe/CafeBoardMemGradeList?cafeNum="+cafeNum).forward(req, resp);
		}
				
	}
}
