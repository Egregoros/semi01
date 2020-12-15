<%@page import="db.DBCPBean"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginOk.jsp</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{
		con=DBCPBean.getConn()	;
		String sql="select * from userinfo where id=? and pwd=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		rs=pstmt.executeQuery();
		if(rs.next()){
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			response.sendRedirect(request.getContextPath()+"/main.jsp");
		}else{
				request.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지 않습니다. 아이디 또는 비밀번호를 다시 입력해주세요.");
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				}
	}catch(SQLException se){
		se.printStackTrace();
	}finally{
		DBCPBean.close(con, pstmt, rs);
	}
%>
</body>
</html>