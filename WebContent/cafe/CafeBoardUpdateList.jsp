<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeBoardUpdateList.jsp</title>
</head>
<body>
	<c:set var = "cp" value = "${pageContext.request.contextPath }" />
	<c:set var = "cafeNum" value = "${param.cafeNum }" />
	<div id = "wrapper">
		<div id = "header">
			<h1>${cafeName } 게시판/카테고리 수정/삭제</h1>
		</div>
		
		<div id = "body">
			<a>카테고리 생성</a><a>게시판 생성</a>
			<table border="1">
				<tr>
					<th>게시판/카테고리</th>
					<th>이름</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
				<c:forEach var = "cafeNavBoardList1" items = "${cafeNavBoardList }">
					<tr>
						<td>카테고리</td>
						<td>${cafeNavBoardList1.catName }</td>
						<td><a href = "${cp }/cafe/cafeBoardCatUpdate?cafeNum=${cafeNum }&boardCatNum=${cafeNavBoardList1.catNum }">수정</a></td>
						<td><a href = "${cp }/cafe/cafeBoardCatDelete?cafeNum=${cafeNum }&boardCatNum=${cafeNavBoardList1.catNum }">삭제</a></td>
					</tr>
					<c:forEach var = "cafeNavBoardListBoard" items = "${cafeNavBoardList1.board }">
						<tr>
							<td>게시판</td>
							<td>${cafeNavBoardListBoard.boardName }</td>
							<td><a href = "${cp }/cafe/cafeBoardUpdate?cafeNum=${cafeNum }&boardNum=${cafeNavBoardListBoard.boardNum }">수정</a></td>
							<td><a href = "${cp }/cafe/cafeBoardDelete?cafeNum=${cafeNum }&boardNum=${cafeNavBoardListBoard.boardNum }">삭제</a></td>
						</tr>
					
					</c:forEach>
				</c:forEach>
			</table>
		</div>
		
		<div id = "footer">
		</div>
	</div>

</body>
</html>