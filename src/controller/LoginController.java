package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;

@WebServlet("/login/login")
public class LoginController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id").toUpperCase();
		String pwd=req.getParameter("pwd");
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);
		LoginDao dao=LoginDao.getInstance();
		int a=dao.getUserNum(map);
		if(a>0) {
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("userNum", a);
			resp.sendRedirect(req.getContextPath()+"/cafeList");
		}else if(a==0){
			req.setAttribute("errMsg", "���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�." + "<br>" + "�Է��Ͻ� ������ �ٽ� Ȯ�����ּ���.");
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		}
	}
}