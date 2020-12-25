package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMemberDao;
import vo.CafeMemberGradeNameVo;
@WebServlet("/cafe/cafeUserInfoList")
public class CafeUserInfoList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		CafeMemberDao cafeMemberDao = CafeMemberDao.getInstance();
		ArrayList<CafeMemberGradeNameVo> cafeMemberGradeNameList = cafeMemberDao.getListGradeName(cafeNum);
		int maxGradeNum = cafeMemberDao.getMaxGradeNum(cafeNum);
		req.setAttribute("cafeMemberGradeNameList", cafeMemberGradeNameList);
		req.setAttribute("maxGradeNum", maxGradeNum);
		req.getRequestDispatcher("/cafe/CafeUserInfoList.jsp?cafeNum="+cafeNum).forward(req, resp);
	}
}
