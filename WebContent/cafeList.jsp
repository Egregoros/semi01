<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cafeList.jsp</title>
</head>
<body>
<h1>카페리스트</h1>


<c:set var = "cp" value = "${pageContext.request.contextPath }"/>
<div id = "list">
<a href = "${pageContext.request.contextPath }/list">글목록</a><br>
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
			<td>${vo.catNum}</td>
			<td>${vo.userNum }</td>
			<td>${vo.cafeRegDate }</td>
		</tr>
	
	</c:forEach>
</table>
</div>
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

<div>
	<form method="post" action="${cp }/cafeList">
		<select name="field">
			<option value="cafeName" <c:if test="${field=='cafeName' }">selected</c:if>>카페명</option>
			<option value="catNum" <c:if test="${field=='catNum' }">selected</c:if>>카테고리</option>
		</select>
		<input type="text" name="keyword" value="${keyword }">
		<input type="submit" value="검색">
	</form>
</div>
</body>
</html>