package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao1.CatTableDao;
import vo.CatTableVo;
@WebServlet("/cafe/cafeCreate")
public class CafeCreate extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("id") != null && session.getAttribute("pwd") != null) {
			req.setCharacterEncoding("utf-8");
			CatTableDao catDao = CatTableDao.getInstance();
			ArrayList<CatTableVo> catList = catDao.list();
			req.setAttribute("catList", catList);
			req.getRequestDispatcher("/cafe/cafeCreate.jsp").forward(req, resp);
			System.out.println(1);
		} else {
			req.setAttribute("needLogin", "로그인이 필요한 작업입니다. 로그인해주세요.");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
	}
}
