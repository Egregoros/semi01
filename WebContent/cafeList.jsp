<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafeList.jsp</title>
<style>
	#wrapper {background-color: gray; display: block;}
	#header{background-color:red; text-align: center;}
	#left{background-color: blue; display: inline-block; float: left;width: 25%;}
	#middle{background-color : yellow; display: inline-block; float: left; width: 50%;text-align: center;}
	#search{background-color: green; width: 70%; display: block; margin: auto;}
	#middle div{width: 100%; margin: auto; display: block;}
	table{margin-left: auto; margin-right: auto;}
</style>
<%
	HashMap catMap = (HashMap)request.getAttribute("catMap");
%>


</head>
<body>
<div id = "wrapper">
<div id = "header">
<h1>네*음 카페 홈페이지</h1>

</div>

<div id = "body">
<div id = "left">
<div id = "login">
<form action="post" action = "">
	아이디<br>
	<input type = "text" name = "id" value = "${param.id }"><br>
	비밀번호<br>
	<input type = "password" name = "pwd" value = "${param.pwd }"><br>
	<div>${errMsg }</div>
	<input type = "submit" value = "로그인">
</form>
</div>
</div>

<div id = "middle">
<c:set var = "cp" value = "${pageContext.request.contextPath }"/>
<div id = "search">
	<form method="post" action="${cp }/cafeList">
		카페 검색: 
		<select name="field" hidden="hidden">
			<option value="cafeName" <c:if test="${field=='cafeName' }">selected</c:if>>카페명</option>
		</select>
		<input type="text" name="keyword" value="${keyword }" style = "width:400px;">
		<input type="submit" value="검색">
	</form>
</div>

<div id = "list">
<a href = "${cp }/cafeListCat">카테고리별 카페 목록</a>
<table border = "1" width = "500">
	<tr>
		<th>카페번호</th>
		<th>카페명</th>
		<th>카테고리</th>
		<th>가입자수</th>
		<th>개설일자</th>
	</tr>
	<c:forEach var = "vo" items = "${list }">
		<tr>
			<td>${vo.cafeNum }</td>
			<td>${vo.cafeName }</td>
			<td>${catMap[vo.catNum]}</td>
			<td>${vo.userNum }</td>
			<td>${vo.cafeRegDate }</td>
		</tr>
	</c:forEach>
</table>
<div id = "pageNum">
	<c:if test = "${startPageNum>10 }">
		<a href = "${cp }/cafeList?pageNum=${startPageNum-1 }&field=${field }&keyword=${keyword }">[이전]</a>
	</c:if>
	<c:forEach var = "i" begin = "${startPageNum }" end = "${endPageNum }">
		<a href="${cp }/cafeList?pageNum=${i }&field=${field }&keyword=${keyword }">[${i }]</a>
	</c:forEach>
	<c:if test="${endPageNum<pageCount }">
		<a href="${cp }/cafeList?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">[다음]</a>
	</c:if>
</div>
</div>
</div>
</div>
</div>
</body>
</html>