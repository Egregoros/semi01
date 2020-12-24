package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;

@WebServlet("/findid")
public class FindIdController extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phone=req.getParameter("phone");
		String email=req.getParameter("email");
		LoginDao dao=LoginDao.getInstance();
		HashMap<String, String> mh=new HashMap<String, String>();
		mh.put("phone", phone);
		mh.put("email",email);
		String n=dao.findId(mh);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<result>");
		if(n!=null) {
			pw.print("회원님의 아이디는 " + n + " 입니다.");
		}else {
			pw.print("입력하신 휴대폰 번호 또는 이메일과 일치하는 아이디가 없습니다.");
		}
		pw.print("</result>");
	}
}