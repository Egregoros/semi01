<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>catList.jsp</title>
</head>
<body>
<h1>카테고리 리스트</h1>
<c:set var = "cp" value = "${pageContext.request.contextPath }"/>

<div id = "list">
<table border = "1" width = "500">
	<tr>
		<th>카테고리 번호</th>
		<th>카테고리 이름</th>
	</tr>
	<c:forEach var = "vo" items = "${list }">
		<tr>
			<td>${vo.catNum }</td>
			<td><a href = "${cp }/cafeList?catNum=${vo.catNum-1 }">${vo.catName }</a></td>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>