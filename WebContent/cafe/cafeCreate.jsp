<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafeCreate.jsp</title>
</head>
<c:set var = "cp" value = "${pageContext.request.contextPath }" />
<body>
<div id = "wrapper">
<div id = "header">
<h1>카페생성</h1>
</div>
<div id = "body">
<form method = "post" action = "${cp }/cafeCreate">
	<input type = "text" name = "cafeName" id = "cafeName" placeholder = "생성할 카페이름"><br>
	<select name = "catName" id = "catName">
		<c:forEach var = "catVo" items = "${catList } ">
			<option value = "${catVo.catName }">${catVo.catName }</option>
		</c:forEach>
	</select>
	<!-- 파일 업로드 -->
	<textarea rows="5" cols="50"></textarea>
</form>
</div>
<div id = "footer">
</div>
</div>
</body>
</html>