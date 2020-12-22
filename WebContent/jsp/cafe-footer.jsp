<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<span id="footer-cafe">${cafeInfo['cafeName'] } 카페</span>
<span class="slash">┃</span>
<span id="footer-cafe-link" class="underline"><a href="${pageContext.request.contextPath }/jsp/cafe-main.do?cafeNum=${cafeInfo['cafeNum'] }">https://${cafeInfo['cafeName'] }.com</a></span>
<span id="footer-cafe-home"><a href="${pageContext.request.contextPath }/cafeList">카페 홈</a></span>