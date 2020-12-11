<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>post.jsp</title>
<style>
	#temporary{margin-left: 518px;}
	#submit{margin-left: 10px;}
	#select1{width: 425px;}
	#select2{width: 160px;}
	#select3{width: 140px;}
	#line{margin-left: 0px;}
	#fieldset{font-size: 3px; width: 130px;margin-left: 597px;margin-top:10px;position: absolute;}
</style>
</head>
<body>
카페 글쓰기
<input type="button" value="임시등록" id="temporary">
<input type="button" value="등록" id="submit">
<hr size="2" width="735" color="black" id=line>
<form method="post">
	<select id="select1">
		<option>게시판을 선택해 주세요.</option>
	</select>
	<select id="select2">
		<option>말머리 선택</option>
	</select>
	<select id="select3">
		<option>공개설정</option>
	</select>
	<br>
	<fieldset id="fieldset">
		<input type="checkbox" value="댓글 허용" checked="checked">댓글허용<br>
		<input type="checkbox" value="블로그, 카페 공유 허용" checked="checked">블로그, 카페 공유 허용<br>
		<input type="checkbox" value="외부 공유 허용" checked="checked">외부 공유 허용<br>
		<input type="checkbox" value="복사, 저장 허용">복사, 저장 허용<br>
		<input type="checkbox" value="자동출처 사용">자동출처 사용<br>
		<input type="checkbox" value="CCL 사용">CCL 사용
	</fieldset>
	<br>
	<textarea rows="1" cols="80" placeholder="제목을 입력해 주세요."></textarea><br>
	<p>
	<textarea rows="25" cols="80" placeholder="내용을 입력하세요."></textarea>
</form>
</body>
</html>