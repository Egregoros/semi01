<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
</head>
<body>
${errMsg }
<form action="${cp }/jsp/cafe-main.do">
	<%request.getSession().setAttribute("userNum", 1); %>
	<input type="text" name="cafeNum">
	<input type="submit" value="카페이동">
</form>
</body>
</html>