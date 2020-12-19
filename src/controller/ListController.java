package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CafeMemberDao;
import dao1.CafeListTableDao;
import dao1.CatTableDao;
import vo.CafeListCatNameVo;
import vo.CafeListVo;
import vo.CatTableVo;


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
		if (spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		
		
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		CafeListTableDao dao=new CafeListTableDao();
		ArrayList<CafeListCatNameVo> list=dao.catList(startRow, endRow, field, keyword);
		if(catNum != null) {
			list=dao.catList(startRow, endRow, "cl.catnum", catNum);
		} else {
			list=dao.catList(startRow, endRow, field, keyword);
		}
		
		int pageCount=(int)Math.ceil(dao.getCount(field,keyword)/10.0);
		int startPageNum=(pageNum-1)/10*10+1;
		int endPageNum=startPageNum+9;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		CatTableDao catDao = CatTableDao.getInstance();
		CatTableVo catVo = new CatTableVo();
		
		ArrayList<CatTableVo> catList = catDao.list();
		CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
		HashMap<Integer, Integer> map = cafeMemDao.getCafeCount();
		
		req.setAttribute("map", map);
		req.setAttribute("catList", catList);
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
