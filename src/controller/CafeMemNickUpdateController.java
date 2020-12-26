package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeListTableDao;
import dao.CafeMemberDao;
import dao.UserInfoDao;
import vo.CafeListVo;
import vo.CafeMemberVo;
import vo.UserInfoVo;

@WebServlet("/cafe/nickUpdate")
public class CafeMemNickUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userNum=Integer.parseInt(req.getParameter("userNum"));
		int cafeNum=Integer.parseInt(req.getParameter("cafeNum"));
		CafeMemberDao dao=CafeMemberDao.getInstance();
		CafeMemberVo vo=dao.getNum(userNum, cafeNum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/cafe/nickUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int userNum=Integer.parseInt(req.getParameter("userNum"));
		int cafeNum=Integer.parseInt(req.getParameter("cafeNum"));
		String cafeMemNick=req.getParameter("cafeMemNick");
		CafeMemberVo vo=new CafeMemberVo(userNum, cafeNum, cafeMemNick, 1, 1, null);
		CafeMemberDao dao=CafeMemberDao.getInstance();
		int n=dao.update(vo);
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.getRequestDispatcher("/cafeList.jsp").forward(req, resp);
	}
	}