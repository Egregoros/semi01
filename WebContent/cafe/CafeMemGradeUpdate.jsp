<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeMemGradeUpdate.jsp</title>
</head>
<body>
<c:set var = "cp" value = "${param.cafeNum }" />
<div id = "wrapper">
	<div id = "header">
	</div>
	
	<div id = "body">
		<form action="${cp }/cafe/CafeMemGradeUpdate?cafeNum=${param.cafeNum }" method = "post">
			카페 등급 이름: <input type = "text" name = "cafeMemGradeName" value = "${param.cafeMemGradeName } ">
			<input type = "text" name = "cafeMemGradeNum" value = "${param.cafeMemGradeNum }" hidden="hidden">
			<input type = "submit" value = "등급 수정">
		</form>
	</div>
	
	<div id = "footer">
	</div>
</div>
</body>
</html>