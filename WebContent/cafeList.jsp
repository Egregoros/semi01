<%@page import="dao.CatTableDao"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafeList.jsp</title>
<style>
* {
	margin: 0px;
	padding: 0px;
	border-spacing: 0;
	border-collapse: collapse;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: underline;
}

#wrapper {
	width: 1100px;
	margin: auto;
	text-align: center;
}

#header {
	text-align: center;
}

#header h1 {
	line-height: 48px;
	border-bottom: 2px solid black;
	display: inline-block;
	margin-bottom: 10px;
}

#body {
	position: relative;
}

#left {
	margin: 10px;
	display: inline-block;
	border: 1px solid black;
  	vertical-align: top;
}

#left>* {
	margin: 10px;
}

#middle {
	margin: 10px;
	display: inline-block;
	width: 50%;
	text-align: left;
  	vertical-align: top;
}

#middle th {
	border-bottom: 2px solid black;
	padding: 10px;
}

#middle td {
	border-bottom: 2px solid #e3e3e3;
	padding: 5px 10px;
}

#middle table>* {
	margin: 10px;
}

#search {
	width: 70%;
	display: block;
	margin: auto;
}

#middle div {
	width: 100%;
	margin: auto;
	display: block;
	text-align: center;
}

#middle form{
	display: inline-block;
	margin: auto;
}

table {
	margin-left: auto;
	margin-right: auto;
}

#right {
	display: inline-block;
  	vertical-align: top;
	margin: 10px;
	border: 1px solid black;
	padding:10px;
}
#right th{ 
	border-bottom: 2px solid black;
}

#right td{
	border-bottom: 2px solid #e3e3e3;
}

#footer {
	width: 100%;
	clear: both;
	text-align: left;
	border-top: 2px solid black;
	padding-top: 5px;
	font-size: 0.8em;
}
</style>
<%
	CatTableDao catDao = CatTableDao.getInstance();
%>


</head>
<body>
	<c:set var="cp" value="${pageContext.request.contextPath }" />
	<div id="wrapper">
		<div id="header">
			<h1>네*음 카페 홈페이지</h1>

		</div>

		<div id="body"> 
			<div id="left">
				<c:choose>
					<c:when test="${sessionScope.userNum != null}">
						<div id="welcome">
							<a href="/myPage/myPageMain.jsp">마이페이지</a><br>
							<a href="${cp }/message">메세지</a><br>
							 <a href="/myPage/myCafeList.jsp">내 카페 목록</a><br> 
							 <a href="/login/update.jsp">정보수정</a><br> 
							 <a href="${cp }/cafe/cafeCreate">카페생성</a><br>
							 <a href = "${cp }/logout">로그아웃</a><br>
						</div>
					</c:when>
					<c:otherwise>
						<div id="login">
							<form method="post" action="${cp }/login/login">
								아이디<br> <input type="text" name="id" value="${param.id }"><br>
								비밀번호<br> <input type="password" name="pwd"
									value="${param.pwd }"><br>
								<div>${errMsg }</div>
								<input type="submit" value="로그인">
							</form>
						</div>
					</c:otherwise>
				</c:choose>

			</div>

			<div id="middle">
				<div id="search">
					<form method="post" action="${cp }/cafeList">
						카페 검색: <select name="field" hidden="hidden">
							<option value="cafeName"
								<c:if test="${field=='cafeName' }">selected</c:if>>카페명</option>
						</select> <input type="text" name="keyword" value="${keyword }"
							style="width: 300px;"> <input type="submit" value="검색">
					</form>
				</div>

				<div id="list">
					<table>
						<tr>
						
						</tr>
						<tr>
							<th>카페번호</th>
							<th>카페명</th>
							<th>카테고리</th>
							<th>가입자수</th>
							<th>개설일자</th>
						</tr>
						<c:forEach var="vo" items="${list }">
							<tr>
								<td>${vo.cafeNum }</td>
								<td><a
									href="${cp }/jsp/cafe-main.do?cafeNum=${vo.cafeNum }">${vo.cafeName }</a></td>
								<td><a href="${cp }/cafeList?catNum=${vo.catNum }">${vo.catName }</a></td>
								<td>${map[vo.cafeNum] }</td>
								<td>${vo.cafeRegDate }</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" style="text-align: center; border:none;">
								<div id="pageNum">
									<c:if test="${startPageNum>10 }">
										<a
											href="${cp }/cafeList?pageNum=${startPageNum-1 }&field=${field }&keyword=${keyword }">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${startPageNum }"
										end="${endPageNum }">
										<a
											href="${cp }/cafeList?pageNum=${i }&field=${field }&keyword=${keyword }">[${i }]</a>
									</c:forEach>
									<c:if test="${endPageNum<pageCount }">
										<a
											href="${cp }/cafeList?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">[다음]</a>
									</c:if>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="right">
				<div id="catList">
					<table>
						<tr>
							<th>카테고리 이름</th>
						</tr>
						<tr>
							<td><a href="${cp }/cafeList">전체</a></td>
						</tr>
						<c:forEach var="cvo" items="${catList }">
							<tr>
								<td><a href="${cp }/cafeList?catNum=${cvo.catNum }">${cvo.catName }</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

		<div id="footer">
			<p>작성자 : 이준수, 송하경, 김상순, 김재홍</p>
		</div>
	</div>
</body>
</html>