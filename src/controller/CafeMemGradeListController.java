package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemGradeDao;
import vo.CafeMemGradeVo;
@WebServlet("/cafe/CafeBoardMemGradeList")
public class CafeMemGradeListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
		ArrayList<CafeMemGradeVo> cafeMemGradeList = cafeMemGradeDao.getList(cafeNum);
		int maxNum = cafeMemGradeDao.getMaxNum(cafeNum);
		
		req.setAttribute("maxNum", maxNum);
		req.setAttribute("cafeMemGradeList", cafeMemGradeList);
		req.getRequestDispatcher("/cafe/CafeMemGradeList.jsp?cafeNum="+cafeNum).forward(req, resp);
	}
	
}
