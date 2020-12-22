package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeListTableDao;
import dao.UserInfoDao;
import vo.CafeListVo;
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
		CafeListTableDao cafeListDao = new CafeListTableDao();
		CafeListVo cafeListVo = cafeListDao.getUserNum(cafeNum);
		
		int userNum = userVo.getUserNum();
		//get userNum from cafeNum
		if(session.getAttribute("id") == null) {
			resp.sendRedirect("/login/login.jsp");
		} else if () { // 운영자 이상의 등급을 갖고 잇으면 가능. 카페 넘버도 맞아야함. &&로 해주면 될듯.
			req.setAttribute("errMsg", "카페장 혹은 운영자가 아니므로 ");
		}
		
	}
}
