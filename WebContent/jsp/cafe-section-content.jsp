<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="section-content-header">
	<h1>${boardInfo.boardName }</h1>
	<span><span class="bold">${boardInfo.postCount }</span>개의 글</span>
</div>
<div id="section-content-list">
	<table>
		<tr>
			<th style="width:70px;" class="center"></th>
			<th style="width:530px;" class="left">제목</th>
			<th style="width:100px;" class="left">작성자</th>
			<th style="width:130px;" class="center">작성일</th>
			<th style="width:100px;" class="center">조회</th>
		</tr>
		<c:forEach items="${noticeInfo }" var="notice">
			<tr class="notice">
				<td class="center">공지</td>
				<td class="left">${notice.postTitle }</td>
				<td class="left">${notice.postWriter }</td>
				<td class="center">${notice.postDate }</td>
				<td class="center">${notice.postInviteCount }</td>
			</tr>
			<tr>
			</tr>
		</c:forEach>
		<c:forEach items="${postInfo }" var="post">
			<tr>
				<td class="center">${post.cafePostNum }</td>
				<td class="left">${post.postTitle }</td>
				<td class="left">${post.postWriter }</td>
				<td class="center">${post.postDate }</td>
				<td class="center">${post.postInviteCount }</td>
			</tr>
		</c:forEach>
	</table>
</div>