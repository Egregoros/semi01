<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${cafeInfo['cafeName'] }</title>
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
				<jsp:param value="${cafeInfo }" name="cafeInfo" />
				<jsp:param value="${pageCount }" name="pageCount" />
				<jsp:param value="${search }" name="search" />
			</jsp:include>
		</header>
		<section>
			<div id="section-nav">
				<jsp:include page="cafe-section-nav.jsp">
					<jsp:param value="${cafeInfo }" name="cafeInfo" /> 
					<jsp:param value="${cafeNavList }" name="cafeNavList" />
					<jsp:param value="${pageCount }" name="pageCount" />
				</jsp:include>
			</div>
 
			<div id="section-content">
				<c:choose>
					<c:when test="${postInfo != null}">
						<jsp:include page="cafe-post.jsp">
							<jsp:param value="${postInfo }" name="postInfo" />
							<jsp:param value="${pageNum }" name="pageNum" />
							<jsp:param value="${pageCount }" name="pageCount" />
							<jsp:param value="${postCommentList }" name="postCommentList" />
							<jsp:param value="${postCommentCount }" name="postCommentCount" />
						</jsp:include>
					</c:when>
					<c:when test="${writeCafeNum!=null }">
						<jsp:include page="cafe-write.jsp">
							<jsp:param value="${userInfo }" name="userInfo" />
							<jsp:param value="${cafeNavList }" name="cafeNavList" />
							<jsp:param value="${postCatList }" name="postCatList"/>
						</jsp:include>
					</c:when>
					<c:otherwise>
						<jsp:include page="cafe-section-content.jsp">
							<jsp:param value="${postList }" name="postInfo" />
							<jsp:param value="${postCommentCount }" name="postCommentCount" />
							<jsp:param value="${boardInfo }" name="boardInfo" />
							<jsp:param value="${noticeInfo }" name="noticeInfo" />
							<jsp:param value="${pageNum }" name="pageNum" />
							<jsp:param value="${pageCount }" name="pageCount" />
							<jsp:param value="${search }" name="search" />
						</jsp:include>
					</c:otherwise>
				</c:choose>
			</div>
		</section>
		<footer>
			<jsp:include page="cafe-footer.jsp">
				<jsp:param value="${cafeInfo }" name="cafeInfo" />
			</jsp:include>
		</footer>
	</div>
</body>
</html>