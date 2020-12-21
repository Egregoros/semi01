<%@page import="dao.CatTableDao"%>
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
	#left{background-color: blue; float: left;width: 25%;}
	#middle{background-color : yellow; float: left; width: 50%;text-align: center;}
	#search{background-color: green; width: 70%; display: block; margin: auto;}
	#middle div{width: 100%; margin: auto; display: block;}
	
	table{margin-left: auto; margin-right: auto;}
	#right{background-color: cyan; width: 25%; float: left;}
	
	#footer{background-color: magenta; width: 100%; display: block; clear: both; text-align : center;}
</style> 
<%
	CatTableDao catDao = CatTableDao.getInstance();
	
%>


</head>
<body>
<div id = "wrapper">
<div id = "header">
<h1>네*음 카페 홈페이지</h1>

</div>

<div id = "body">
<div id = "left">
<c:choose>
	<c:when test="${sessionScope.id != null}">
<div id = "welcome">
	<a href = "/myPage/myPageMain.jsp">마이페이지</a><br>
	<a href = "/myPage/myCafeList.jsp">내 카페 목록</a><br>
	<a href = "/login/update.jsp">정보수정</a><br>
	<a href = "${pageContext.request.contextPath }/cafe/cafeCreate">카페생성</a><br>
</div>
	</c:when>
	<c:otherwise>
<div id = "login">
<form method="post" action="${pageContext.request.contextPath }/login/login">
	아이디<br>
	<input type = "text" name = "id" value = "${param.id }"><br>
	비밀번호<br>
	<input type = "password" name = "pwd" value = "${param.pwd }"><br>
	<div>${errMsg }</div>
	<input type = "submit" value = "로그인">
</form>
</div>
	</c:otherwise>
</c:choose>

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
<table border = "1" width = "90%">
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
			<td><a href = "${cp }/jsp/cafe-main.do?cafeNum=${vo.cafeNum }">${vo.cafeName }</a></td>
			<td><a href = "${cp }/cafeList?catNum=${vo.catNum }">${vo.catName }</a></td>
			<td>${map[vo.cafeNum] }</td>
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
<div id = "right">
<div id = "catList" style = "width: 50%; margin: auto;">
<table border = "1" width = "100%">
	<tr>
		<th>카테고리 이름</th>
	</tr>
	<tr>
		<td><a href = "${cp }/cafeList">전체</a></td>
	</tr>
	<c:forEach var = "cvo" items = "${catList }">
		<tr>
			<td><a href = "${cp }/cafeList?catNum=${cvo.catNum }">${cvo.catName }</a></td>
		</tr>
	</c:forEach>
</table>
</div>
</div>
</div>

<div id = "footer">
<p>작성자 : 이준수, 송하경, 김상순, 김재홍</p>
</div>
</div>
</body>
</html>