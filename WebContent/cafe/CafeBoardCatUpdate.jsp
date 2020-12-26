<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeBoardCatUpdate.jsp</title>
</head>
<body>
<div id = "wrapper">
	<div id = "header">
	</div>
	
	<div id = "body">
		<form method = "post" action = "${cp }/cafe/cafeBoardCatUpdate">
			카테고리 이름 : <input type = "text" name = "catName" value = "${cafeBoardCatVo.catName }">
			<input type = "submit" value = "카테고리 수정">
			<input type = "text" name = "boardCatNum" value = "${cafeBoardCatVo.boardCatNum }" hidden="hidden">
		</form>
	</div>

	<div id = "footer">
	</div>
</div>
</body>
</html>