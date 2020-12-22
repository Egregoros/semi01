<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>messageWrite.jsp</title>
<style>
	#writer1{width: 437px;}
</style>
</head>
<body>
<a href="${pageContext.request.contextPath }/message/message.jsp">쪽지함 메인</a>
<h2>쪽지 보내기</h2>
<form method="post" action="insert.jsp">
	작성자	<input type="text" name="writer">
	수신자 <input type="text" name="receiver"><br><br>
	<textarea type="text" id="writer1" placeholder="메시지를 입력하세요."></textarea><br>
	<input type="submit" value="보내기">
</form>
</body>
</html>