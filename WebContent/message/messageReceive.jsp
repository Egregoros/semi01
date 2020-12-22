<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>messageReceive.jsp</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/message/message.jsp">쪽지함 메인화면</a>
<h2>받은 쪽지함</h2>
<table border="2" width="1000">
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>내용</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.num }</td>
			<td>${vo.writer }</td>
			<td>${vo.content }</td>
		</tr>
	</c:forEach>
</table>
<div>
	<c:if test="${startPageNum>10 }">
		<a href="${pageContext.request.contextPath }/message/list?pageNum=${startPageNum-1}&field=${field}&keyword=${keyword}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
		<a href="${pageContext.request.contextPath }/message/list?pageNum=${i}&field=${field}&keyword=${keyword}">[${i }]</a> 
	</c:forEach>
	<c:if test="${endPageNum<pageCount }">
		<a href="${pageContext.request.contextPath }/message/list?pageNum=${endPageNum+1}&field=${field}&keyword=${keyword}">[다음]</a>
	</c:if>
</div>
<div>
	<form method="post" action="${pageContext.request.contextPath }/message/list">
		<select name="field">
			<option value="writer" <c:if test="${field=='writer' }">selected</c:if>>작성자</option>
			<option value="content" <c:if test="${field=='content' }">selected</c:if>>내용</option>
		</select>
		<input type="text" name="keyword" value="${keyword }">
		<input type="submit" value="검색">
	</form>
</div>
</body>
</html>