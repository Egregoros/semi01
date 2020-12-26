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
<c:set var = "cafeBoardVo" value = "${requestScope.cafeBoardVo }" />
<div id = "wrapper">
	<div id = "header">
	</div>
	
	<div id = "body">
		<form method = "post" action = "${cp }/cafe/cafeBoardUpdate">
			게시판 이름:<input type = "text" name = "boardName" value = "${param.boardName }">
			<input type = "submit" value = "카테고리 수정">
			<input type = "text" name = "boardNum" value = "${param.boardNum }" hidden="hidden">
			<input type = "text" name = "cafeNum" value = "${param.cafeNum }" hidden="hidden">
		</form>
	</div>

	<div id = "footer">
	</div>
</div>
</body>
</html>