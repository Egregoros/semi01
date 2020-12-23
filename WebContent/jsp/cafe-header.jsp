<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header-nav">
	<span style="font-size: 0.8em;">${cafeInfo['cafeName'] }</span>
	<ul>
		<c:choose>
			<c:when test="${userNum==null }">
				<li><a href="" class="underline">로그인</a></li>
				<li class="slash">┃</li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }/logout" class="underline">로그아웃</a></li>
				<li class="slash">┃</li>
				<li><a href="" class="underline">내 카페</a></li>
				<li class="slash">┃</li>
			</c:otherwise>
			</c:choose>
		<li><a href="${pageContext.request.contextPath }/cafeList" class="underline">카페 홈</a></li>
	</ul>
</div>
<div id="header-img-div" style="background-image : url('../cafeMainPic/${cafeMainPic['saveFileName']}');">
</div>
<div id="header-content-nav">
	<ul>
		<li><a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${param.cafeNum }" class="underline">전체글보기</a></li>	<!-- 처음에는 굵은글씨 선택하면 선택링크 굵은 글씨 -->
		<li>┃</li>
		<li><a href="" class="underline">이미지모아보기</a></li>
		<li>┃</li>
		<li><a href="" class="underline">카페태그보기</a></li>
		<li>┃</li>
		<li><a href="" class="underline">카페 캘린더</a></li>
	</ul>
	<div id="header-content-search">
		<form action="${pageContext.request.contextPath }/jsp/cafe-main.do" method="GET">
			<input type="hidden" name="cafeNum" value="${cafeInfo['cafeNum'] }"><input type="hidden" name="pageCount" value="${pageCountNum }">
			<input id="header-content-search-text" type="text" name="search" value="${search }"><input type="submit" value="검색" id="header-content-search-button">
		</form>
	</div>
</div>