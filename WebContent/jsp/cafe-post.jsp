<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h3>${postInfo['postTitle'] }</h3>
	<p>${postInfo['userName'] }</p>
	<p>${postInfo['postInviteCount'] }</p>
	<p>${postInfo['postDate'] }</p>
	<p>${postInfo['postContent'] }</p>
</div>