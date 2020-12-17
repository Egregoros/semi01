<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>~~카페</title>
<link href="../css/cafe-main.css" rel="stylesheet" type="text/css">
<link href="../css/cafe-header.css" rel="stylesheet" type="text/css">
<link href="../css/cafe-footer.css" rel="stylesheet" type="text/css">
<link href="../css/cafe-section.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/cafe-section-nav.js"></script>
</head>
<body>
	<div id="content">
		<header>
			<jsp:include page="cafe-header.jsp">
				<jsp:param value="${cafeInfo }" name="cafeInfo"/>
			</jsp:include>
		</header>
		<section>
			<div id="section-nav">
				<jsp:include page="cafe-section-nav.jsp">
				<jsp:param value="${cafeInfo }" name="cafeInfo"/>
				<jsp:param value="${cafeNavList }" name="cafeNavList"/>
			</jsp:include>
			</div>
			<div id="section-content">
				<jsp:include page="cafe-section-content.jsp">
					<jsp:param value="${postInfo }" name="postInfo"/>
					<jsp:param value="${postCount }" name="postCount"/>
					<jsp:param value="${noticeInfo }" name="noticeInfo"/>
				</jsp:include>
			</div>
		</section>
		<footer>
			<jsp:include page="cafe-footer.jsp">
				<jsp:param value="${cafeInfo }" name="cafeInfo"/>
			</jsp:include>
		</footer>
	</div>
</body>
</html>