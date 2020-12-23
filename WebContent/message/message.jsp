<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message.jsp</title>
<style>
	*{
	
	}
	a{
		color:black;
		text-decoration: none;
	}
	a:hover {
		text-decoration: underline;
	}

</style>
</head>
<body>
<a href="${pageContext.request.contextPath }/cafeList">카페리스트로 돌아가기...</a>
<h1>쪽지함 메인</h1>
<a href="${pageContext.request.contextPath }/message/messageWrite.jsp">쪽지 보내기</a>
<table border="1" width="400">
	<tr>
		<th><a href="${pageContext.request.contextPath }/message/messageReceive.jsp">받은 쪽지함</a></th>
		<th><a href="">삭제</a></th>
	</tr>
	<c:forEach items="${userList }" var="list">
		<tr>
			<td><a href="${pageContext.request.contextPath }/message?messageUserNum=${list.key }">${list.value }<c:if test="${newMessage[list.key]!=null }">&nbsp;&nbsp;&nbsp;+${newMessage[list.key] }</c:if></a></td>
			<td><a href="${pageContext.request.contextPath }/message?deleteMessageUserNum=${list.key }">삭제</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>