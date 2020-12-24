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
import dao.CafeMemberDao;
import dao.UserInfoDao;
import vo.CafeBoardCatVo;
import vo.CafeBoardVo;
import vo.CafeListVo;
import vo.CafeMemberVo;
import vo.UserInfoVo;
@WebServlet("/cafe/cafeBoardUpdate")
public class CafeBoardUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userNum = (Integer)session.getAttribute("userNum");
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		req.setCharacterEncoding("utf-8");
		
		//ī�� ������ �´��� Ȯ�ο�
		CafeListTableDao cafeListTableDao = new CafeListTableDao();
		CafeListVo cafeListVo = cafeListTableDao.getOne(cafeNum);
		UserInfoDao userInfoDao = new UserInfoDao();
		UserInfoVo userInfoVo = userInfoDao.getOne(userNum);
		
		CafeMemberDao cafeMemberDao = CafeMemberDao.getInstance();
		CafeMemberVo cafeMemberVo = cafeMemberDao.getcafeMemGradeNum(userNum, cafeNum);
		
		//ī�װ� �ҷ�����
		CafeBoardCatDao cafeBoardCatDao = CafeBoardCatDao.getInstance();
		ArrayList<CafeBoardCatVo> cafeBoardCatList = cafeBoardCatDao.getCafeCat(cafeNum);
		
		//�Խ��� �ҷ�����
		CafeBoardDao cafeBoardDao = CafeBoardDao.getInstance();
		ArrayList<CafeBoardVo> cafeBoardList = cafeBoardDao.getList(cafeNum);
		
		if(cafeMemberVo.getCafeMemGradeNum() < 2) {
			req.setAttribute("cafeName", cafeListVo.getCafeName());
			req.setAttribute("cafeBoardCatList", cafeBoardCatList);
			req.setAttribute("cafeBoardList", cafeBoardList);
			req.getRequestDispatcher("/cafe/cafeBoardUpdate.jsp?cafeNum="+cafeNum).forward(req, resp);
		} else if (cafeMemberVo.getCafeMemGradeNum() > 1) {
			req.setAttribute("errMsg", "�����ڰ� �ƴ϶� ���� �Ұ����� �޴��Դϴ�.");
			req.getRequestDispatcher("/jsp/cafe-main.do?cafeNum="+cafeNum).forward(req, resp);
		} else if (session.getAttribute("userNum") != null) {
			req.setAttribute("errMsg", "�α����� �ʿ��� �޴��Դϴ�.");
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
		}
	}
}
