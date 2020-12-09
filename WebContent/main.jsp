<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    *{
        margin:0px;
        padding:0px;
    }
    #content{
        margin:0px auto;
        width:1000px;
        background-color:green;
    }
    #header{
        width:100%;
        height:200px;
        background-color:red;
    }
    #session{
        width:100%;
        height:200px;
        background-color:blue;
    }
    #footer{
        width:100%;
        height:200px;
        background-color:green;
    }
</style>
</head>
<body>
    <div id="content">
        <header>
        <%-- 
			<jsp:include page="header.jsp"/>
		--%>
        </header>
        <section>
        <%-- 
			<jsp:include page="session.jsp"/>
		--%>
        </section>
        <footer>
        <%-- 
			<jsp:include page="footer.jsp"/>
		--%>
        </footer>
    </div>
</body>
</html>