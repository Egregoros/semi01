package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeBoardCatDao;
import dao.CafeBoardDao;
import dao.CafeListTableDao;
import dao.CafeMemGradeDao;
import vo.CafeBoardCatVo;
import vo.CafeBoardVo;
import vo.CafeListVo;
@WebServlet("/cafe/cafeDelete")
public class CafeDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		int userNum = (Integer)session.getAttribute("userNum");
		
		req.setCharacterEncoding("utf-8");
		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		String confirm = req.getParameter("confirm");
		CafeListTableDao listDao = new CafeListTableDao();
		CafeListVo listVo = listDao.getOne(cafeNum);
		String cafeName = listVo.getCafeName();
		
		CafeBoardCatDao cafeBoardCatDao = CafeBoardCatDao.getInstance();
		CafeBoardCatVo cafeBoardCatVo = cafeBoardCatDao.getOne(cafeNum);
		
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		ArrayList<CafeBoardVo> cafeBoardList = cafeBoardDao.getList(cafeNum);
		CafeBoardVo cafeBoardVo = new CafeBoardVo();
		
		CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
		
		if(cafeName.equals(confirm)) {
			for (int i = 0; i < cafeBoardList.size(); i++) {
				int boardNum = cafeBoardList.get(i).getBoardNum();
				cafeBoardDao.delete(boardNum);
			}
			cafeMemGradeDao.delete(cafeNum);
			cafeBoardCatDao.delete(cafeNum);
			listDao.delete(cafeNum);
		}
		
		resp.sendRedirect(req.getContextPath()+"/cafe/cafeList");
	}
}
