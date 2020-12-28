<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="post-content" style="border: 1px solid black; padding:10px;">
	<h3>${postInfo['postTitle'] }</h3> 
	<p style="font-size: 0.8em;margin-top:10px;">작성자 : <a href="${pageContext.request.contextPath }/message?messageUserNum=${postInfo['userNum']}">${postInfo['userName'] }</a></p>
	<p style="font-size: 0.8em;margin-top:10px;">조회수 : ${postInfo['postInviteCount'] }</p>
	<p style="font-size: 0.8em;margin-top:10px;">작성일 : ${postInfo['postDate'] }</p>
	<p style="font-size: 0.9em;margin-top:10px;border: 1px solid black; padding:10px;">${postInfo['postContent'] }</p>
</div>
<div style="font-size: 0.9em;margin-top:10px;">
	<c:if test="${postInfo['userNum']==userNum||userInfo['userGradeNum']==0}">
		<button>수정</button> <button type="button" onclick="location.href='${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${param.cafeNum }&deletePostNum=${param.postNum }';">삭제</button>
	</c:if>
</div>
<div id="post-comment" style="margin-top: 30px;">
	<form action="${pageContext.request.contextPath }/commentInsert">
		<textarea style="width:100%;height: 60px;" name="comment"></textarea>
		<input type="hidden" name="cafeNum" value="${cafeInfo['cafeNum'] }">
		<input type="hidden" name="postNum" value="${postInfo['postNum'] }">
		<input type="hidden" name="pageCount" value="${pageCount }">
		<input type="submit" value="댓글 입력">
	</form>
</div>
<select onchange="if(this.value) location.href=(this.value);" id="section-content-page-count">
	<c:set var="pageCountArray">10,15,20,25,30,50</c:set>
	<c:forEach var="pageCountNum" items="${pageCountArray }">
		<c:choose>
			<c:when test="${pageCount==pageCountNum }">
				<option value="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${postInfo['postNum']}&pageCount=${pageCountNum }" selected="selected">${pageCountNum }개 </option>
			</c:when>
			<c:otherwise>
				<option value="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${postInfo['postNum']}&pageCount=${pageCountNum }">${pageCountNum }개 </option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
<c:if test="${fn:length(postCommentList)!=0 }">
	<div style="margin-top:30px; border:1px solid black;padding:10px;padding-top:0px;">
		<c:forEach items="${postCommentList }" var="comment">
			<div style="border-bottom: 1px solid #e3e3e3;margin:5px;padding:5px;">
				<span style="margin-right: 10px; font-size: 0.8em;"><a href="${pageContext.request.contextPath }/message?messageUserNum=${comment['userNum']}">${comment['userName'] }</a></span><span style="margin-right: 10px; font-size: 0.6em;">${comment['commentRegdate'] }</span><span style="margin-right: 10px; font-size: 0.8em;">${comment['postComment'] }</span><c:if test="${comment['userNum']==userNum }"><button type="button" onclick="location.href='${pageContext.request.contextPath }/commentDelete?cafeNum=${param.cafeNum }&postNum=${param.postNum }&commentNum=${comment['commentNum'] }&pageNum=${pageNum }&pageCount=${pageCount }';">삭제</button></c:if>
			</div>
		</c:forEach>
	</div> 
</c:if>
<c:set var="startPageNum" value="${Math.floor(Math.floor((pageNum-1)/10)*10+1)}"/>
<c:set var="endPageNum" value="${startPageNum+9 }"/>
<div id="section-content-page">
	<c:choose>
		<c:when test="${startPageNum>10}">
	   		<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${postInfo['postNum']}&pageNum=${Integer.valueOf(Math.floor(startPageNum-1))}&pageCount=${pageCount }">[이전]</a>
		</c:when>
	</c:choose>
	<c:forEach var="i" begin="${startPageNum}"	end="${endPageNum}">
		<c:if test="${i<=((postCommentCount[postInfo['postNum']]-1)/pageCount)+1 }">
			<c:choose>
				<c:when test="${i==pageNum }">
					<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${postInfo['postNum']}&pageNum=${i}&pageCount=${pageCount }"><span class="bold">[${i }]</span></a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${postInfo['postNum']}&pageNum=${i}&pageCount=${pageCount }"><span class="underline">[${i }]</span></a>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:forEach>
	<c:choose>
		<c:when test="${endPageNum<((postCommentCount[postInfo['postNum']]-1)/pageCount)+1 }">
			<a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }&postNum=${postInfo['postNum']}&pageNum=${Integer.valueOf(Math.floor(endPageNum+1))}&pageCount=${pageCount }">[다음]</a>
		</c:when>
	</c:choose>
</div>