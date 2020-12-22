<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafe/cafeUpdate.jsp</title>
</head>
<body>
<c:set var = "cp" value = "${pageContext.request.contextPath }"/>

<div id = "wrapper">
<div id = "header">
</div>

<div id = "body">

<c:set var = "cafeListVo" value = "${cafeListVo }"></c:set>
<form method = "post" action = "${cp }/cafe/cafeUpdate">
	카페이름 : <input type = "text" name = "cafeName" value = "${cafeListVo.cafeName }"><br>
	카테고리: 
	<select id = "catName" name = "catName">
		<c:forEach var = "cvo" items = "${catList }">
			<option value="${cvo.catName }">${cvo.catName }</option>
		</c:forEach>
	</select><br>
	카페 설명: <textarea rows="5" cols="50" name = "content">${cafeListVo.content }</textarea><br>
	<input type = "submit" value = "카페수정">
	<input type = "text" name = "cafeNum" value = "${cafeListVo.cafeNum }" hidden="hidden">
</form>

</div>

<div id = "footer">
</div>


</div>
</body>
</html>