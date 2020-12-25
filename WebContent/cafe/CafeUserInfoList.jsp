<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeUserInfoList.jsp</title>
</head>
<body>
<c:set var = "cp" value = "${pageContext.request.contextPath }" />
<c:set var = "cafeNum" value = "${param.cafeNum }" />
<c:set var = "maxGradeNum" value = "${maxGradeNum }" />
<div id = "wrapper">
	<div id = "header">
	</div>
	
	<div id = "body">
		<a href="${cp }/jsp/cafe-main.do?cafeNum=${cafeNum }"></a>
		<table border= "1">
			<tr>
				<th>회원번호</th>
				<th>등급</th>
				<th>닉네임</th>
				<th>등급↑</th>
				<th>등급↓</th>
				<th>강퇴</th>
			</tr>
			<c:forEach var = "cafeMemberGradeNameList" items ="${cafeMemberGradeNameList }">
				<tr>
					<td>${cafeMemberGradeNameList.userNum }</td>
					<td>${cafeMemberGradeNameList.cafeMemGradeName }</td>
					<td><a href="${pageContext.request.contextPath }/message?messageUserNum=${cafeMemberGradeNameList.userNum }">${cafeMemberGradeNameList.cafeMemNick }</a></td>
					<c:choose>
						<c:when test="${cafeMemberGradeNameList.cafeMemGradeNum == 0 }">
							<td></td>
							<td><a href = "${cp }/cafe/CafeUserInfoMemGradeDown?cafeNum=${param.cafeNum }&userNum=${cafeMemberGradeList.userNum }">등급↓</a></td>
						</c:when>
						<c:when test="${(cafeMemberGradeNameList.cafeMemGradeNum < maxGradeNum) && (cafeMemberGradeNameList.cafeMemGradeNum > 0) }">
							<td><a href = "${cp }/cafe/CafeUserInfoMemGradeUp?cafeNum=${param.cafeNum }&userNum=${cafeMemberGradeList.userNum }">등급↑</a></td>
							<td><a href = "${cp }/cafe/CafeUserInfoMemGradeDown?cafeNum=${param.cafeNum }&userNum=${cafeMemberGradeList.userNum }">등급↓</a></td>
						</c:when>
						<c:when test="${cafeMemberGradeNameList.cafeMemGradeNum == maxGradeNum }">
							<td><a href = "${cp }/cafe/CafeUserInfoMemGradeUp?cafeNum=${param.cafeNum }&userNum=${cafeMemberGradeList.userNum }">등급↑</a></td>
							<td></td>
						</c:when>
					</c:choose>
					<td><a href = "${cp }/cafe/cafeUserInfoMemDelete">강퇴</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div id = "footer">
	</div>
</div>
</body>
</html>