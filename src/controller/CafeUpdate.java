package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeListTableDao;
import dao.CafeMemberDao;
import dao.CatTableDao;
import dao.UserInfoDao;
import vo.CafeListVo;
import vo.CafeMemberVo;
import vo.CatTableVo;
import vo.UserInfoVo;
@WebServlet("/cafe/cafeUpdate")
public class CafeUpdate extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		String id = (String)session.getAttribute("id");
		UserInfoDao userDao = new UserInfoDao();
		UserInfoVo userVo = userDao.getOne(id);
		int userNum = userVo.getUserNum();
		
		CafeListTableDao cafeListDao = new CafeListTableDao();
		CafeListVo cafeListVo = cafeListDao.getOne(cafeNum);
		
		CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
		CafeMemberVo cafeMemVo = cafeMemDao.getcafeMemGradeNum(userNum, cafeNum);
		
		CatTableDao catTableDao = CatTableDao.getInstance();
		ArrayList<CatTableVo> catList = catTableDao.list();
		
		//get userNum from cafeNum
		if(session.getAttribute("id") == null) {
			resp.sendRedirect("/login/login.jsp");
		} else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() > 1) { // ��� �̻��� ����� ���� ������ ����. ī�� �ѹ��� �¾ƾ���. &&�� ���ָ� �ɵ�.
			req.setAttribute("errMsg", "ī���� Ȥ�� ��ڰ� �ƴϹǷ� ������ �Ұ��մϴ�.");
		} else if (userNum != cafeMemVo.getUserNum()) {
			req.setAttribute("errMsg", "ī�� ȸ���� �ƴմϴ�. ī�� ���Ժ��� ���ּ���.");
			resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
		} else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() <= 1) {
			req.setAttribute("cafeListVo", cafeListVo);
			req.setAttribute("catList", catList);
			req.getRequestDispatcher(req.getContextPath() + "/cafe/cafeUpdate.jsp").forward(req, resp);
		} else {
			req.setAttribute("errMsg", "�߸��� �����Դϴ�.");
			resp.sendRedirect(req.getContextPath()+"/cafeList");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		
		
	}
}
