<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeMemGradeList.jsp</title>
</head>
<body>
<c:set var = "cp" value = "${pageContext.request.contextPath }"/>
<c:set var = "maxNum" value = "${maxNum }" />
<div  id = "wrapper">
	<div id = "header">
	</div>
	
	<div id = "body">
		<table border="1">
			<tr>
				<th>등급 번호</th>
				<th>등급 이름</th>
				<th>등급↑</th>
				<th>등급↓</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<c:forEach var = "cafeMemGradeList" items="${cafeMemGradeList }" >
				<tr>
					<td>${cafeMemGradeList.cafeMemGradeNum }</td>
					<td>${cafeMemGradeList.cafeMemGradeName }</td>
					<c:choose>
						<c:when test="${cafeMemGradeList.cafeMemGradeNum == 0 }">
							<td></td>
							<td><a href = "${cp }/cafe/CafeMemGradeDown?cafeNum=${param.cafeNum}&cafeMemGradeNum=${cafeMemGradeList.cafeMemGradeNum }&cafeMemGradeName=${cafeMemGradeList.cafeMemGradeName }">등급↓</a></td>
						</c:when>
						<c:when test="${cafeMemGradeList.cafeMemGradeNum > 0 && cafeMemGradeList.cafeMemGradeNum < maxNum}">
							<td><a href = "${cp }/cafe/CafeMemGradeUp?cafeNum=${param.cafeNum}&cafeMemGradeNum=${cafeMemGradeList.cafeMemGradeNum }&cafeMemGradeName=${cafeMemGradeList.cafeMemGradeName }">등급↑</a></td>
							<td><a href = "${cp }/cafe/CafeMemGradeDown?cafeNum=${param.cafeNum}&cafeMemGradeNum=${cafeMemGradeList.cafeMemGradeNum }&cafeMemGradeName=${cafeMemGradeList.cafeMemGradeName }">등급↓</a></td>
						</c:when>
						<c:when test="${cafeMemGradeList.cafeMemGradeNum == maxNum }">
							<td><a href = "${cp }/cafe/CafeMemGradeUp?cafeNum=${param.cafeNum}&cafeMemGradeNum=${cafeMemGradeList.cafeMemGradeNum }&cafeMemGradeName=${cafeMemGradeList.cafeMemGradeName }">등급↑</a></td>
							<td></td>
						</c:when>
					</c:choose>
					<td><a href = "${cp }/cafe/CafeMemGradeUpdate?cafeNum=${param.cafeNum }&cafeMemGradeName=${cafeMemGradeList.cafeMemGradeName }&cafeMemGradeNum=${cafeMemGradeList.cafeMemGradeNum }">수정</a></td>
					<td><a href = "${cp }/cafe/CafeMemGradeDelete?cafeNum=${param.cafeNum }&cafeMemGradeNum=${cafeMemGradeList.cafeMemGradeNum }">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id = "footer">
	</div>

</div>
</body>
</html>