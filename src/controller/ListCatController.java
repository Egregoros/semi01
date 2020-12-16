package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao1.CatTableDao;
import vo.CatTableVo;
@WebServlet("/cafeListCat")
public class ListCatController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		HashMap<Integer, String> catMap = new HashMap<Integer, String>();
		String[] categories = {"종합", "게임", "건강", "취미"};
		for (int i = 0; i < 4; i++) {
			catMap.put(i, categories[i]);
		}
		
		String spageNum = req.getParameter("pageNum");
		
		CatTableDao dao = CatTableDao.getInstance();
		ArrayList<CatTableVo> list = dao.list();
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/catList.jsp").forward(req, resp);
	}
}
