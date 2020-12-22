<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message.jsp</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/cafeList">카페리스트로 돌아가기...</a>
<h1>쪽지함 메인</h1>
<table border="1" width="150" style="text-align: center">
	<tr>
		<td><a href="${pageContext.request.contextPath }/message/messageWrite.jsp">쪽지 보내기</a></td>
	</tr>
	<tr>
		<td><a href="${pageContext.request.contextPath }/message//messageReceive.jsp">받은 쪽지함</a></td>
	</tr>
</table>
</body>
</html>