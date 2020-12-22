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
			pw.print("ȸ������ ���̵��" + n + "�Դϴ�.");
		}else {
			pw.print("�Է��Ͻ� �޴��� ��ȣ �Ǵ� �̸��ϰ� ��ġ�ϴ� ���̵� �����ϴ�.");
		}
		pw.print("</result>");
	}
}