package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao1.UsersDao;
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
		UsersDao dao=UsersDao.getInstance();
		int n=dao.cafeMember(map);
		if(n==1) {
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
		}else if(n==0) {
			req.setAttribute("errMsg", "���̵� �Ǵ� ��й�ȣ�� �ٽ� Ȯ�����ּ���.");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}else {
			req.setAttribute("errMsg", "������ ���� �α������� ���߽��ϴ�.");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}