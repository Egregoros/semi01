<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.apache.catalina.startup.SetContextPropertiesRule"%>
<%@page import="java.sql.SQLException"%>
<%@page import="db.DBCPBean"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	Boolean using=false;
	try{
		con=DBCPBean.getConn();
		String sql="select * from userinfo where id=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		if(rs.next()){
			using=true;
		}
	}catch(SQLException se){
		se.printStackTrace();
	}finally{
		DBCPBean.close(con, pstmt, rs);
	}
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print("<?xml version='1.0' encoding='utf-8'?>");
	pw.print("<data>");
	pw.print("<using>" + using + "</using>");
	pw.print("</data>");
%>

<!-- 추가기능 -->