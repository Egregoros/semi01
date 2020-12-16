package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao1.CafeListTableDao;
import vo.CafeListVo;


@WebServlet("/cafeList")
public class ListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String spageNum = req.getParameter("pageNum");
		String field = req.getParameter("field");
		String keyword = req.getParameter("keyword");
		String catNum = req.getParameter("catNum");
		int pageNum = 1;
		int catNum1 = 0;
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		
		
		
		HashMap<Integer, String> catMap = new HashMap<Integer, String>();
		String[] categories = {"종합", "게임", "건강", "취미"};
		for (int i = 0; i < 4; i++) {
			catMap.put(i, categories[i]);
		}
		
		
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		CafeListTableDao dao=new CafeListTableDao();
		ArrayList<CafeListVo> list=dao.list(startRow, endRow, field, keyword);
		if(catNum != null) {
			list=dao.list(startRow, endRow, "catNum", catNum);
		} else {
			list=dao.list(startRow, endRow, field, keyword);
		}
		
		int pageCount=(int)Math.ceil(dao.getCount(field,keyword)/10.0);
		int startPageNum=(pageNum-1)/10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		
		
		req.setAttribute("catMap", catMap);
		req.setAttribute("list",list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPageNum",startPageNum);
		req.setAttribute("endPageNum",endPageNum);
		req.setAttribute("pageNum",pageNum);
		req.setAttribute("field", field);
		req.setAttribute("keyword",keyword);
		req.getRequestDispatcher("/cafeList.jsp").forward(req, resp);
		
	}
}
