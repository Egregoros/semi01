<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>messageWrite.jsp</title>
<style>
	#writer1{width: 437px;}
	form *{
	display: block;
	width:300px;
	}
	a{
		color:black;
		text-decoration: none;
	}
	a:hover {
		text-decoration: underline;
	}
</style>
<script type="text/javascript">
	var xhr=null;
	function checkNickName(){
		var nickName=document.getElementById("nickName").value;
		if(nickName.trim()==""){
			document.getElementById("nickNameCheck").innerHTML="";
			return;
		}
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open('get','nicknamecheck.jsp?nickName=' + nickName,true);
		xhr.send();
	}
	function callback(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseXML;
			var using=data.getElementsByTagName("using")[0].firstChild.nodeValue;
			var span=document.getElementById("nickNameCheck");
			if(eval(using)==true){
				span.innerHTML="";
			}else if(eval(using)==false){
				span.innerHTML="존재하지 않는 회원입니다.";
			}
		}
	}
</script>
</head>
<body>
<a href="${pageContext.request.contextPath }/message">쪽지함 메인</a>
<h2>쪽지 보내기</h2>
<form method="post" action="${pageContext.request.contextPath }/message">
	수신자 <input type="text" name="toUserName" id="nickName" onkeyup="checkNickName()" required="required"><div id="nickNameCheck" style="font-size: 0.8em;color:red;"></div><br>
	<input type="text" name="messTitle" placeholder="제목" required="required"><br>
	<textarea rows="7" style="resize:none;" name="messContent" placeholder="메시지를 입력하세요." required="required"></textarea><br>
	<input type="submit" value="보내기">
</form>
</body>
</html>