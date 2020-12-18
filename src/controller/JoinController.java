package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao1.UserInfoDao;
import vo.UserInfoVo;
@WebServlet("/login/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");		
		String pwd=req.getParameter("pwd");
		String name=req.getParameter("name");
		String nickname=req.getParameter("nickname");
		String addr=req.getParameter("addr");
		String email=req.getParameter("email");
		String birth=req.getParameter("birth");
		Date d=Date.valueOf(birth);
		String phone=req.getParameter("phone");
		UserInfoVo uiv=new UserInfoVo(0, id, pwd, nickname, nickname, addr, email, d, phone, 1);
		UserInfoDao dao=new UserInfoDao();
		String resultCode="success";
		
		int n=dao.insert(uiv);
		if(n<1) {
			resultCode="fail";
		}
		req.setAttribute("code", resultCode);
		req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
	}
}
