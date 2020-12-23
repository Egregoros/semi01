<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>messageReceive.jsp</title>
<style>
	#messageBox{
		position: relative;
	}
	
	.right{
		position:relative;
		right:0px;
		top:0px;
		margin:10px;
		margin-left:auto;
		border: 1px solid black;
		border-radius: 3px;
		max-width: 200px;
	}
	.left{
		position:relative;
		left:0px;
		top:0px;
		margin:10px;
		border: 1px solid black;
		border-radius: 3px;
		max-width: 200px;
	}
	.messagebox *{
		margin:0px;
		padding:0px 5px;
	}
	.messagebox h3{
		font-size: 0.9em;
	}
	.messagebox div {
		font-size: 0.8em;
	}
</style>
<script>
	window.onload=function(){
		function updateScroll() {
			var messageBox = document.getElementById("messageBox");
			messageBox.scrollTop=messageBox.scrollHeight;
		}
		updateScroll();
	}
</script>
</head>
<body>
<a href="${pageContext.request.contextPath }/message/message.jsp">쪽지함 메인화면</a>
<h2>받은 쪽지함</h2>
<div id="messageBox" style="width:300px; height:350px; border: 1px solid black;overflow: scroll;overflow-x:hidden;">
	<c:forEach items="${messageList }" var="list">
		<c:choose>
			<c:when test="${list['recUserNum']==userNum }">
				<div class="right messagebox">
					<h3>${list['messTitle'] }</h3>
					<div>${list['messContent'] }</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="left messagebox">
					<h3>${list['messTitle'] }</h3>
					<div>${list['messContent'] }</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>
<div style="width:300px; border:1px solid black; border-top:none;">
	<form action="" style="text-align: center;">
		<input type="hidden" name="toUserNum" value=${messageUserNum }>
		<input type="text" name="messTitle" style="width:292px;" placeholder="제목">
		<textarea rows="7" style="width:294px;resize:none;"  name="messContent" placeholder="내용"></textarea>
		<input type="submit" value="보내기" style="margin:5px;">
	</form>
</div>
</body>
</html>