package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.userInfoVo;
@WebServlet("/login/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int usernum=Integer.parseInt(req.getParameter("usernum"));
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String name=req.getParameter("name");
		String nickname=req.getParameter("nickname");
		String addr=req.getParameter("addr");
		String email=req.getParameter("email");
		String birth=req.getParameter("birth");
		String phone=req.getParameter("phone");
		userInfoVo 
		userInfoVo vo=new userInfoVo();
		String resultCode="success";
		int n=vo.insert()
	}
}
