package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeMainDao;
import vo.PostVo;
import vo.PostCommentVo;

@WebServlet("/jsp/cafe-main.do")
public class CafeMain extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		CafeMainDao cmdao = CafeMainDao.getInstance();
		int userNum = -1;
		int cafeNum = Integer.parseInt((String)req.getParameter("cafeNum"));
		try {
			userNum = (int)session.getAttribute("userNum");
			ArrayList<Integer> inviteCafe = new ArrayList<Integer>();
			if(session.getAttribute("inviteCafe")!=null) {
				inviteCafe=(ArrayList<Integer>)session.getAttribute("inviteCafe");
			}
			boolean visit = false;
			for (int i = 0; i < inviteCafe.size(); i++) {
				if(inviteCafe.get(i)==cafeNum) {
					visit=true;
				}
			}
			if(!visit) {
				inviteCafe.add(cafeNum);
				if(!(cmdao.invitePlus(cafeNum, userNum)>0)) {
					req.getRequestDispatcher("/cafeList").forward(req, resp);
				}else {
					session.setAttribute("inviteCafe", inviteCafe);
				}
			}
		}catch(NullPointerException ne) {
			userNum=-1;
		}
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
		try {
			int postNum = Integer.parseInt((String)req.getParameter("postNum"));
			PostVo postInfo = cmdao.getPostInfo(postNum, cafeNum);
			if(postInfo!=null) {
				req.setAttribute("postInfo", postInfo);
				ArrayList<PostCommentVo> postCommentList = cmdao.getPostCommentInfo(postNum, cafeNum, startRow, endRow);
				req.setAttribute("postCommentList", postCommentList);
			}
		}catch(Exception e) {
			
		}
		String search=req.getParameter("search");
		if(search==""||search==null) {
			req.setAttribute("postList", cmdao.getCafePostList(cafeNum, boardNum, startRow, endRow));
			req.setAttribute("boardInfo", cmdao.getCafeBoardInfo(cafeNum, boardNum));
			req.setAttribute("noticeInfo", cmdao.getCafeNoticeList(cafeNum, boardNum));
			search="";
		}else {
			req.setAttribute("postList", cmdao.getCafeSearchList(cafeNum, startRow, endRow, search));
			req.setAttribute("boardInfo", cmdao.getCafeSearchInfo(cafeNum, search));
		}
		
		
		req.setAttribute("search", search);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("postCommentCount", cmdao.getPostCommentCount());
		req.setAttribute("cafeInfo", cmdao.getCafeInfo(cafeNum));
		req.setAttribute("cafeNavList", cmdao.getCafeNavList(cafeNum));
		if(userNum>-1) {
			req.setAttribute("userInfo", cmdao.getUserInfo(userNum, cafeNum));
		}
		
		req.getRequestDispatcher("/jsp/cafe-main.jsp").forward(req, resp);
	
	}
	
}
