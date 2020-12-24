package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfoDao;
import vo.UserInfoVo;
@WebServlet("/login/update")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userNum=Integer.parseInt(req.getParameter("userNum"));
		UserInfoDao dao=new UserInfoDao();
		UserInfoVo vo=dao.getOne(userNum);
		System.out.println("vo: " + vo);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/login/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int userNum=Integer.parseInt(req.getParameter("userNum"));
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String name=req.getParameter("name");
		String nickname=req.getParameter("nickname");
		String addr=req.getParameter("addr");
		String email=req.getParameter("email");
		String birth=req.getParameter("birth");
		Date d=Date.valueOf(birth);
		String phone=req.getParameter("phone");
		UserInfoVo vo=new UserInfoVo(userNum, id, pwd, name, nickname, addr, email, d, phone, 1);
		UserInfoDao dao=new UserInfoDao();
		int n=dao.update(vo);
		if(n>0) {
			req.setAttribute("code", "success");
		}else {
			req.setAttribute("code", "fail");
		}
		req.getRequestDispatcher("/cafeList.jsp").forward(req, resp);
	}
}
