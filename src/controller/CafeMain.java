package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMainDao;

@WebServlet("/jsp/cafe-main.do")
public class CafeMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		int userNum = -1;
		try {
			userNum = (int)session.getAttribute("userNum");
		}catch(NullPointerException ne) {
			userNum=-1;
		}
		CafeMainDao cmdao = CafeMainDao.getInstance();
		String cafeNum = (String)req.getParameter("cafeNum");
		int boardNum=0;
		try {
			boardNum = Integer.parseInt((String)req.getParameter("boardNum"));
		}catch(Exception e) {
			boardNum=0;
		}
		int pageCount=10;
		try {
			pageCount = Integer.parseInt((String)req.getParameter("pageCount"));
		}catch(Exception e) {
			pageCount=10;
		}
		int pageNum=1;
		try {
			pageNum = Integer.parseInt((String)req.getParameter("pageNum"));
		}catch(Exception e) {
			pageNum=1;
		}
		int startRow = (pageNum-1)*pageCount+1;
		int endRow = startRow+pageCount-1;
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("cafeInfo", cmdao.getCafeInfo(Integer.parseInt(cafeNum)));
		req.setAttribute("cafeNavList", cmdao.getCafeNavList(Integer.parseInt(cafeNum)));
		req.setAttribute("postInfo", cmdao.getCafePostList(Integer.parseInt(cafeNum), boardNum, startRow, endRow));
		req.setAttribute("boardInfo", cmdao.getCafeBoardInfo(Integer.parseInt(cafeNum), boardNum));
		req.setAttribute("noticeInfo", cmdao.getCafeNoticeList(Integer.parseInt(cafeNum), boardNum));
		if(userNum>-1) {
			req.setAttribute("userInfo", cmdao.getUserInfo(userNum, Integer.parseInt(cafeNum)));
		}
		
		req.getRequestDispatcher("/jsp/cafe-main.jsp").forward(req, resp);
		
		
		
		
		
		
		
		
		
		
		
//		if(session.getAttribute("userNum")!=null) {
//			int userNum = (int)session.getAttribute("userNum");
//			CafeMainDao cmdao = CafeMainDao.getInstance();
//			String cafeNum = (String)req.getParameter("cafeNum");
//			HashMap<String, String> cafeInfo = cmdao.getDefaultInfo(userNum, Integer.parseInt(cafeNum));
//			if(cafeInfo!=null) {
//				cafeInfo.put("cafeNum", cafeNum);
//				cafeInfo.put("userNum", Integer.toString(userNum));
//				if(cafeInfo.get("isUser").equals("true")) {
//					req.setAttribute("cafeInfo", cafeInfo);
//					req.setAttribute("cafeNavList", cmdao.getCafeNavList(Integer.parseInt(cafeNum)));
//					req.getRequestDispatcher("/jsp/cafe-main.jsp").forward(req, resp);
//				}else {
//					req.setAttribute("errMsg", "카페에 가입해주세요.");
//					req.getRequestDispatcher("/main.jsp").forward(req, resp);
//				}
//			}else {
//				req.setAttribute("errMsg", "잘못된 이동경로");
//				req.getRequestDispatcher("/main.jsp").forward(req, resp);
//			}
//		}else {
//			req.setAttribute("errMsg", "로그인 해주세요.");
//			req.getRequestDispatcher("/main.jsp").forward(req, resp);
//		}
	}
	
}
