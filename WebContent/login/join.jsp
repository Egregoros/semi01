<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
<script type="text/javascript">
	var xhr=null;
	function checkid(){//아이디가 사용중일 때 회원가입 버튼 비활성화하는 기능 추가해야함.
		var id=document.getElementById("id").value;
		if(id.trim()==""){
			document.getElementById("idcheck").innerHTML="";
			return;
		}
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=callback;
		xhr.open('get','idcheck.jsp?id=' + id,true);
		xhr.send();
	}
	function callback(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseXML;
			var using=data.getElementsByTagName("using")[0].firstChild.nodeValue;
			var span=document.getElementById("idcheck");
			if(eval(using)==true){
				span.innerHTML="이미 사용 중인 아이디입니다.";
			}else if(eval(using)==false){
				span.innerHTML="사용 가능한 아이디입니다.";
			}
		}
	}
	function checkphone(){
		var phone=document.getElementById("phone").value;
		if(phone.trim()==""){
			document.getElementById("phonecheck").innerHTML="";
			return;
		}
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=backcall;
		xhr.open('get','phonecheck.jsp?phone=' + phone,true);
		xhr.send();
		}
	function backcall(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseXML;
			var using=data.getElementsByTagName("using")[0].firstChild.nodeValue;
			var span=document.getElementById("phonecheck");
			
			if(eval(using)==true){//휴대폰 번호에 하이픈(-) 제외, 11자리로 입력, 모두 숫자로 이루어지게 하는 기능 추가해야함.
				span.innerHTML="입력하신 번호는 이미 사용 중인 번호입니다. 다시 입력해주세요.";
			}else if(eval(using)==false){
				span.innerHTML="사용 가능한 휴대폰 번호입니다.";
			}
		}
	}
	function buttonClick(){//필수입력 항목이 모두 충족되지 않았을 때 alert창 띄우지 않게 하는 기능 추가해야함.
			alert('회원가입 성공!');
		}
</script>
<style>
	#fieldset{width: 275px;}
	.label1{width: 35px;display: inline-block;}
	.label2{width: 52px;display: inline-block;}
	.label3{width: 20px;display: inline-block;}
	.label4{width: 39px;display: inline-block;}
	.label5{width: 0px;display: inline-block;}
	#idcheck{color: red;}
	#phonecheck{color: red;}
	ul{list-style: none;display: inline-block;}
	a{ text-decoration: none;}
</style>
</head>
<body>
<h2 align="center">카페 회원가입</h2>
	<form method="post" action="/semi_project/login/join">
	<div>
		<div align="center">
			<fieldset id="fieldset" >
				<legend style="text-align: center;">회원정보 입력</legend>
				아이디 <label for="id" class="label1"></label>
					<input type="text" name="id" id="id" onkeyup="checkid()" required="required">
					<span id="idcheck"></span><br>
				비밀번호 <label for="pwd" class="label3"></label>
					<input type="password" name="pwd" id="pwd" required="required"><br>
				이름 <label for="name" class="label2"></label>
					<input type="text" name="name" required="required"><br>
				닉네임 <label for="nickname" class="label1"></label>
					<input type="text" name="nickname" required="required"><br>
				주소 <label for="addr" class="label2"></label>
					<input type="text" name="addr" required="required"><br>
				e-mail <label for="email" class="label4"></label>
					<input type="email" name="email" placeholder="cafe@jhtml.com" required="required"><br>
				생년월일 <label for="birth" class="label3"></label>
					<label for="birth"></label>
					<input type="date" name="birth" required="required"><br>
				휴대폰 번호 <label for="phone" class="label5"></label>
					<input type="text" name="phone" id="phone" placeholder="ex) 01012345678 (- 제외)" onkeyup="checkphone()" required="required">
					<span id="phonecheck"></span>
			</fieldset><br>
	<ul>
		<li><a href="${pageContext.request.contextPath }/login/login.jsp">◀이전화면</a>&nbsp;&nbsp;&nbsp;
		</li>
	</ul>
		<input type="submit" value="회원가입" onclick="buttonClick()">
		</div>
	</div>
	</form>
</body>
</html>