<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/cafe-main.css" rel="stylesheet" type="text/css">
<link href="css/cafe-header.css" rel="stylesheet" type="text/css">
<link href="css/cafe-section.css" rel="stylesheet" type="text/css">
<link href="css/cafe-footer.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/cafe-section-nav.js"></script>
</head>
<body>
	<div id="content">
		<header>
			<jsp:include page="jsp/cafe-header.jsp" />
		</header>
		<section>
			<div id="section-nav">
				<jsp:include page="jsp/cafe-section-nav.jsp"/>
			</div>
			<div id="section-content">
				<jsp:include page="jsp/cafe-section-content.jsp"/>
			</div>
		</section>
		<footer>
			<%-- 
			<jsp:include page="footer.jsp"/>
		--%>
		</footer>
	</div>
</body>
</html>