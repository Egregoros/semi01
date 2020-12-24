<%@page import="vo.FileTableVo"%>
<%@page import="dao.filedao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>글등록하기</h1>

<form style="margin-top: 10px;" action="${pageContext.request.contextPath }/postInsert">
	<input type="hidden" name="cafeNum" value="${param.cafeNum }">
	<span style="margin-top: 10px; display: block;">카테고리 <select
		name="boardNum">
			<c:forEach items="${cafeNavList }" var="list">
				<c:forEach items="${list['board'] }" var="list2">

					<c:if test="${list2['cafeBoardNum']!=0 }">
						<option value="${list2['boardNum']}">${list2['boardName']}</option>
					</c:if>
				</c:forEach>
			</c:forEach>
	</select>
	</span> <span style="margin-top: 10px; display: block;">제목 <input
		type="text" name="postTitle"> <c:choose>
			<c:when test="${userInfo['userGradeNum'] == 0}">
				<select name="postCatNum">
					<c:forEach items="${postCatList }" var="postCat">
						<option value="${postCat.key }">${postCat.value }</option>
					</c:forEach>
				</select>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="postCatNum" value="0">
			</c:otherwise>
		</c:choose>
	</span>
	<span style="margin-top: 10px; display: block;">
		<span style="vertical-align: top;">내용</span> 
		<textarea name="postContent" rows="5" cols="50"></textarea>
	</span>
	<input type="submit" value="등록">
</form>
<form method="post" action="${pageContext.request.contextPath }/controller/upcon.do"   enctype="multipart/form-data">
<table border="1" width="500">
    <tr>
        <td>첨부파일</td>
        <td><input type="file" name="file"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center">
            <input type="submit" value="전송"/>
            <input type="reset" value="취소"/>
        </td>
    </tr>
    </table>
</form>
<table border="1" width="500">
   <tr>
    	<%
		filedao dao=new filedao();
		ArrayList<FileTableVo> list=dao.getList();
		for(FileTableVo m:list){
	%>
    <td align="center"><a href="../download?filenum=<%=m.getFileNum()%>">파일 다운로드</a></td>
    </tr>
    <%
		}
	%>
</table>