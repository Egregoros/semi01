<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafe/MemberCafeList.jsp</title>
</head>
<body>
<div id = "wrapper">
	
	<div id = "header">
	</div>
	
	<div id = "body">
		<a href = "${pageContext.request.contextPath }/cafeList">메인으로</a><br>
		<table border="1">
			<tr>
				<th>카페번호</th>
				<th>카페이름</th>
				<th>회원등급</th>
				<th>닉네임</th>
				<th>카페 가입일자</th>
			</tr>
			<c:forEach var = "memberCafeList" items  = "${list }">
				<tr>
					<td>${memberCafeList.cafeNum }</td>
					<td><a href = "${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${memberCafeList.cafeNum }">${memberCafeList.cafeName }</a></td>
					<td>${memberCafeList.cafeMemGradeName }</td>
					<td>${memberCafeList.nickName }</td>
					<td>${memberCafeList.cafeMemRegDate }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id = "footer">
	</div>

</div>
</body>
</html>