<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<div id="section-content-header">
	<h1>${boardInfo.boardName }</h1>
	<span><span class="bold"><fmt:formatNumber value="${boardInfo.postCount }" type="number"/></span>개의 글</span>
	<select onchange="if(this.value) location.href=(this.value);" id="section-content-page-count">
		<c:set var="pageCountArray">10,15,20,25,30,50</c:set>
		<c:forEach var="pageCountNum" items="${pageCountArray }">
			<c:choose>
				<c:when test="${pageCount==pageCountNum }">
					<option value="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${boardInfo.boardNum}&pageCount=${pageCountNum }&search=${search }" selected="selected">${pageCountNum }개 </option>
				</c:when>
				<c:otherwise>
					<option value="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${boardInfo.boardNum}&pageCount=${pageCountNum }&search=${search }">${pageCountNum }개 </option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>
<div id="section-content-list">
	<table>
		<tr>
			<th style="width: 70px;" class="center"></th>
			<th style="width: 530px;" class="left">제목</th>
			<th style="width: 100px;" class="left">작성자</th>
			<th style="width: 130px;" class="center">작성일</th>
			<th style="width: 100px;" class="center">조회</th> 
		</tr>
		<c:forEach items="${noticeInfo }" var="notice">
			<tr class="notice">
				<td class="center">공지</td>
				<td class="left"><a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${notice.postNum}" class="underline">${notice.postTitle } <c:if test="${postCommentCount[notice.postNum]!=null }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; +${postCommentCount[notice.postNum] }</c:if></a></td>
				<td class="left"><a href="${pageContext.request.contextPath }/message?messageUserNum=${notice.postWriterNum}">${notice.postWriter }</a></td>
				<td class="center">${notice.postDate }</td>
				<td class="center">${notice.postInviteCount }</td>
			</tr>
			<tr>
			</tr>
		</c:forEach>
		<c:forEach items="${postList }" var="post">
			<tr>
				<td class="center">${post.cafePostNum }</td>
				<td class="left"><a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${post.postNum}" class="underline">${post.postTitle } <c:if test="${postCommentCount[post.postNum]!=null }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; +${postCommentCount[post.postNum] }</c:if></a></td>
				<td class="left"><a href="${pageContext.request.contextPath }/message?messageUserNum=${post.postWriterNum}">${post.postWriter }</a></td>
				<td class="center">${post.postDate }</td>
				<td class="center">${post.postInviteCount }</td>
			</tr>
		</c:forEach>
	</table>
	<c:set var="startPageNum" value="${Math.floor(Math.floor((pageNum-1)/10)*10+1)}"/>
	<c:set var="endPageNum" value="${startPageNum+9 }"/>
	<div id="section-content-page">
		<c:choose>
			<c:when test="${startPageNum>10}">
		   		<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${boardInfo.boardNum}&pageNum=${Integer.valueOf(Math.floor(startPageNum-1))}&pageCount=${pageCount }&search=${search }">[이전]</a>
			</c:when>
		</c:choose>
		<c:forEach var="i" begin="${startPageNum}"	end="${endPageNum}">
			<c:if test="${i<=((boardInfo.postCount-1)/pageCount)+1 }">
				<c:choose>
					<c:when test="${i==pageNum }">
						<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${boardInfo.boardNum}&pageNum=${i}&pageCount=${pageCount }&search=${search }"><span class="bold">[${i }]</span></a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${boardInfo.boardNum}&pageNum=${i}&pageCount=${pageCount }&search=${search }"><span class="underline">[${i }]</span></a>
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${endPageNum<((boardInfo.postCount-1)/pageCount)+1 }">
				<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&boardNum=${boardInfo.boardNum}&pageNum=${Integer.valueOf(Math.floor(endPageNum+1))}&pageCount=${pageCount }&search=${search }">[다음]</a>
			</c:when>
		</c:choose>
	</div>
</div>