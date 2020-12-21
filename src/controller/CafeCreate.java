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

import dao.CatTableDao;
import vo.CatTableVo;
@WebServlet("/cafe/cafeCreate")
public class CafeCreate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("id") != null && session.getAttribute("pwd") != null) {
			req.setCharacterEncoding("utf-8");
			CatTableDao catDao = CatTableDao.getInstance();
			ArrayList<CatTableVo> catList = catDao.list();
			req.setAttribute("catList", catList);
			req.getRequestDispatcher("/cafe/cafeCreate.jsp").forward(req, resp);
		} else {
			req.setAttribute("needLogin", "�α����� �ʿ��� �۾��Դϴ�. �α������ּ���.");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(1);
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		CatTableDao catDao = CatTableDao.getInstance();
		
		String cafeName = req.getParameter("cafeName");
		String catName = req.getParameter("catName");
		String file = req.getParameter("cafePicName");
		String content = req.getParameter("content");
		
		System.out.println(cafeName);
		System.out.println(catName);
		System.out.println(file);
		System.out.println(content);
		
		
	}
}
