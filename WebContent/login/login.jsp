<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<style>
	#loginbtn{width: 165px;}
	ul{list-style: none;display: inline-block;font-size: 12px;margin: 0px;padding: 0px;}
	a{text-decoration: none;}
</style>
</head><br>
<body>
<%
	String errMsg=(String)request.getAttribute("errMsg");
	String id=(String)request.getParameter("id");
	String pwd=(String)request.getParameter("pwd");
	if(errMsg==null) errMsg="";
%>

<div style="text-align: center;">
	<div>
	<hr size="3" width="500" color="gray">
	<h1>카페 로그인</h1>
	<hr size="3" width="500" color="gray"><br>
	<form method="post" action="${cp }/login/login">
		<input type="text" name="id" value="${param.id }" placeholder="아이디 입력"><br>
		<input type="password" name="pwd" placeholder="비밀번호 입력"><br>
		<div style="font-size: 15px;color: red;width: 300px;position: relative;left: 40%"><%= errMsg %></div><br>
		<input type="submit" value="로그인" id=loginbtn><br>
	</form>

	<ul>
		<li><a href="${pageContext.request.contextPath }/login/findid.html">아이디 찾기</a>
	</ul>
	<ul>
		<li><a href="${pageContext.request.contextPath }/login/findpwd.html">비밀번호 찾기</a>	
	</ul>
	<br>
	<ul>
		<li><a href="${pageContext.request.contextPath }/login/join.jsp">회원가입</a>
	</ul>
	</div>
</div>
</body>
</html>