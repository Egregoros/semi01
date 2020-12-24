<%@page import="java.sql.SQLException"%>
<%@page import="db.DBCPBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="section-nav-header">
	<div id="section-nav-header-nav">
		<div>
			<p id="cafeInfoBtn" class="cursor bold">카페정보</p>
			<p id="selfInfoBtn" class="cursor bold">나의활동</p>
		</div>
	</div>
	<div id="section-nav-header-content">
		<div id="section-nav-header-content-cafe">
			<div>
				<p class="gray"><a href="${pageContext.request.contextPath }/message?messageUserNum=${cafeInfo['cafeAdminNum']}" style="color:gray;">${cafeInfo['cafeAdmin'] }</a>&nbsp;&nbsp;<c:if test="${userNum==cafeInfo['cafeAdminNum'] }"><a href="${pageContext.request.contextPath }/cafe/cafeUpdate?cafeNum=${cafeInfo['cafeNum'] }" class="underline" style="font-size: 0.8em; color:#3e3e3e;">[관리]</a></c:if></p>
				<p class="gray">since ${cafeInfo['cafeRegdate'] }</p>
			</div>
			<div>
				<p class="gray">${cafeInfo['cafeGrade'] }</p>
				<p class="gray"><fmt:formatNumber value="${cafeInfo['cafeUsers'] }" type="number"/> 명</p>
			</div>
		</div>
		<c:choose>
			<c:when test="${userInfo['isUser']=='true' }">
				<div id="section-nav-header-content-self">
					<div>
						<p class="gray">${userInfo['userNick'] }&nbsp;&nbsp;<a href="" style="font-size: 0.8em; color:gray;">[정보수정]</a></p>
						<p class="gray">가입 ${userInfo['userRegdate'] }</p>
					</div>
					<div>
						<p class="gray">${userInfo['userGrade'] }</p>
						<p class="gray">방문 ${userInfo['userInvite'] }회</p>
						<p class="gray">내가 쓴 글 보기 <a href=""><fmt:formatNumber value="${userInfo['userCountPost'] }" type="number"/> 개</a></p>
						<p class="gray">내가 쓴 댓글 보기 <a href=""><fmt:formatNumber value="${userInfo['userCountPostComment'] }" type="number"/> 개</a></p>
					</div>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${userInfo['isUser']!='true' }">
				<a id="joinCafe" class="underline" 
					<c:choose>
						<c:when test="${userNum==null }">
							href="${pageContext.request.contextPath }/login/login.jsp"
						</c:when>
						<c:otherwise>
							href="${pageContext.request.contextPath }"
						</c:otherwise>
					</c:choose>
				>카페 가입</a>
			</c:when>
			<c:otherwise>
				<a id="write" class="underline" href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&writeCafeNum=${cafeInfo['cafeNum']}">카페 글쓰기</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div id="section-nav-list">
	<c:forEach items="${cafeNavList }" var="list">
		<h3 class="underline cat" id="cat${list.catNum }">${list.catName } <span id="cat${list.catNum }_hide">숨기기</span></h3>
		<div id="cat${list.catNum }_div">
			<c:forEach items="${list.board }" var="board">
				<p><a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${board.boardNum}" class="underline">${board.boardName }</a></p>
			</c:forEach>
		</div>
	</c:forEach>
</div>