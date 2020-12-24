<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeBoardUpdate.jsp</title>
</head>
<body>
	<c:set var = "cp" value = "${pageContext.request.contextPath }" />
	<div id = "wrapper">
		<div id = "header">
			<h1>${cafeName } 게시판/카테고리 수정/삭제</h1>
		</div>
		
		<div id = "body">
			<table>
				<tr>
					<th>게시판/카테고리</th>
					<th>이름</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
				<c:forEach var = "cafeNavBoardList" items = "${cafeNavBoardList }">
					<tr>
						<td>when</td>
						<td>${cafeNavBoardList.catName }</td>
						<td>수정</td>
						<td>삭제</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div id = "footer">
		</div>
	</div>
</body>
</html>