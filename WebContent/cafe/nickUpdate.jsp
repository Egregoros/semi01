<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nickUpdate.jsp</title>
<script type="text/javascript">
	var xhr=null;
	   function checkcafememnick(){
		      var cafeMemNick=document.getElementById("cafeMemNick").value;
		      if(cafeMemNick.trim()==""){
		         document.getElementById("cafeMemNickCheck").innerHTML="";
		         return;
		      }
		      xhr=new XMLHttpRequest();
		      xhr.onreadystatechange=callback;
		      xhr.open('get','cafeMemNickCheck.jsp?cafeMemNick=' + cafeMemNick,true);
		      xhr.send();
		   }
		   function callback(){
		      if(xhr.readyState==4 && xhr.status==200){
		         var data=xhr.responseXML;
		         var using=data.getElementsByTagName("using")[0].firstChild.nodeValue;
		         var span=document.getElementById("cafeMemNickCheck");
		         var cafeMemNick=document.getElementById("cafeMemNick").value;
		         
		         if(cafeMemNick.length<2 && cafeMemNick.length>0 || cafeMemNick.length>10) {
		            span.innerHTML="닉네임은 2글자 이상 또는 10글자 이하로 입력해주세요.";
		            return;
		           }
		         
		         if(eval(using)==true){
		            span.innerHTML="이미 사용 중인 닉네임입니다.";
		         }else if(eval(using)==false){
		            span.innerHTML="사용 가능한 닉네임입니다.";
		         }
		      }
		   }
		   function buttonClick(){
			   alert('회원정보 수정 성공!');
		   }
		   
		   function goBack(){
			   history.go(-1);
		   }
</script>
<style>
	#cafeMemNickCheck{color: red;}
	a{ text-decoration: none;}
</style>
</head>
<body>
<h2>닉네임 수정</h2>
   <form method="post" action="/semi_project/cafe/nickUpdate">
   <input type="hidden" name="userNum" value="${vo.userNum }">
   <input type="hidden" name="cafeNum" value="${vo.cafeNum }">
   <input type="text" name="cafeMemNick" id="cafeMemNick" value="${vo.cafeMemNick }" onkeyup="checkcafememnick()" required="required">
    <input type="submit" value="확인" onclick="buttonClick()">
    <input type="button" value="◀ 뒤로가기" onclick="goBack()"><br>
    <span id="cafeMemNickCheck"></span><br>
   </form>
</body>
</html>