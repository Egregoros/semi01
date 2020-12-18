package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao1.LoginDao;

@WebServlet("/login/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		LoginDao dao=new LoginDao();
		Boolean a=dao.isUser(map);
		if(a) {
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pwd",pwd);
			resp.sendRedirect(req.getContextPath()+"/cafeList.jsp");
		}else	{
			req.setAttribute("errMsg", "아이디 또는 비밀번호가 틀렸습니다." + "<br>" + "입력하신 정보를 다시 확인해주세요.");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
}