package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDao;

@WebServlet("/message")
public class MessageController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		MessageDao mdao = MessageDao.getInstance();
		int userNum = (int)session.getAttribute("userNum");
		try {
			int toUserNum = Integer.parseInt((String)req.getParameter("toUserNum"));
			String title=(String)req.getParameter("messTitle");
			String content=(String)req.getParameter("messContent");
			int i = mdao.insertMessage(userNum,toUserNum,title,content);
			int messageUserNum = toUserNum;
			req.setAttribute("messageList", mdao.getMessage(messageUserNum, userNum));
			req.setAttribute("messageUserNum", messageUserNum);
			req.getRequestDispatcher("/message/messageReceive.jsp").forward(req, resp);
			return;
		}catch(Exception e) {
			
		}
		
		try {
			int messageUserNum = Integer.parseInt((String)req.getParameter("messageUserNum"));
			req.setAttribute("messageList", mdao.getMessage(messageUserNum, userNum));
			req.setAttribute("messageUserNum", messageUserNum);
			mdao.readMessage(userNum, messageUserNum);
			req.getRequestDispatcher("/message/messageReceive.jsp").forward(req, resp);
			return;
			
		}catch(Exception e) {
			
		}
		
		try {
			int deleteMessageUserNum = Integer.parseInt((String)req.getParameter("deleteMessageUserNum"));
			mdao.deleteMessage(userNum, deleteMessageUserNum);
		}catch(Exception e) {
			
		}
		req.setAttribute("userList", mdao.getUserList(userNum));
		req.setAttribute("newMessage", mdao.getNewMessage(userNum));
		req.getRequestDispatcher("/message/message.jsp").forward(req, resp);
		return;
	}
}
