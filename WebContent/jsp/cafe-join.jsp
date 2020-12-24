<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
   var xhr=null;
   function nickNameCheck(){
      var cafeNickName=document.getElementById("cafeNickName").value;
      if(cafeNickName.trim()==""){
         document.getElementById("cafeNickName").innerHTML="";
         return;
      }
      xhr=new XMLHttpRequest();
      xhr.onreadystatechange=callback;
      xhr.open('get','cafenicknamecheck.jsp?cafeNickName='+cafeNickName+'&cafeNum=${param.cafeNum}',true);
      xhr.send();
   }
   function callback(){
      if(xhr.readyState==4 && xhr.status==200){
         var data=xhr.responseXML;
         var using=data.getElementsByTagName("using")[0].firstChild.nodeValue;
         var nickNameCheck=document.getElementById("nickNameCheck");
         var sbtn=document.getElementById("sbtn");
                  
         if(eval(using)==true){
        	 nickNameCheck.innerHTML="이미 사용 중인 아이디입니다.";
        	 sbtn.setAttribute('disabled','disabled');
         }else if(eval(using)==false){
        	 nickNameCheck.innerHTML="사용 가능한 아이디입니다.";
        	 sbtn.removeAttribute('disabled');
         }
      }
   }
</script>
<h1>${cafeInfo['cafeName'] } 가입</h1>

<form style="margin-top: 10px;" action="${pageContext.request.contextPath }/joinCafe">
	<input type="hidden" name="cafeNum" value="${param.cafeNum }">
	<p style="font-size: 0.8em; font-weight: bold;margin:10px 0px;">우리카페</p>
	<div style="font-size: 0.8em; color: gray; background-color: #e3e3e3;margin:10px 0px; line-height: 30px;padding-left:5px;">${cafeInfo['cafeContent'] }</div>
	<p style="font-size: 0.8em; font-weight: bold;margin:10px 0px 0px 0px;">별명<p>
	<input type="text" id="cafeNickName" name="cafeNickName"style="margin:10px 0px;" onkeyup="nickNameCheck()"> <p style="font-size: 0.8em; color:red;" id="nickNameCheck"></p>
	<input type="submit" value="가입" style="margin:10px 0px;" id="sbtn">
</form>