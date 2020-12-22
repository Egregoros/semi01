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

@WebServlet("/findpwd")
public class FindPwdController extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String phone=req.getParameter("phone");
		LoginDao dao=LoginDao.getInstance();
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("id", id);
		hm.put("phone",phone);
		String n=dao.findPwd(hm);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<result>");
		if(n!=null) {
			pw.print("ȸ������ ��й�ȣ��" + n + "�Դϴ�.");
		}else {
			pw.print("�Է��Ͻ� ���̵� �Ǵ� �޴��� ��ȣ�� ��ġ�ϴ� ��й�ȣ�� �����ϴ�.");
		}
		pw.print("</result>");
	}
}