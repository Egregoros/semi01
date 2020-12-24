<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/CafeUpdateMain.jsp</title>
<style type="text/css">
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
</head>
<body>
<c:set var = "cp" value = "${pageContext.request.contextPath }" />
<div id = "wrapper">
	<div id = "header">
	</div>
	
	<div id = "body">
		<ul>
			<li><a href = "${cp }/cafe/cafeUpdate?cafeNum=${param.cafeNum }">카페수정</a></li>
			<li><a href = "${cp }/cafe/cafeBoardUpdateList?cafeNum=${param.cafeNum }">카페게시판 수정</a></li>
			<li><a href = "${cp }/cafe/cafeUserInfoList?cafeNum=${param.cafeNum }">카페 회원 수정</a></li>
			<li><button id = "myBtn">삭제</button></li>
		</ul>
	</div>
	
	<div id = "footer">
	</div>

</div>

<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
   	<form method = "post" action ="${cp }/cafe/cafeDelete?cafeNum=${param.cafeNum }">
   		삭제를 원하시면 카페 이름을 다시 적어주세요.
    	<input type = "text" name = "confirm"><br>
    	<input type = "submit" value = "게시판 삭제">
    </form>
  </div>

</div>

</div>

<script type="text/javascript">
//Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>
</body>
</html>