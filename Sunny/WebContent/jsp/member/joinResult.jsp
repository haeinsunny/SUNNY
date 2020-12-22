<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<jsp:include page="/jsp/common/header.jsp" />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>
<br/>
<br/>
	<div align="center">
		<h3>${name}님 회원가입 되었습니다.</h3>
		<br/>
		<button class="btn btn-warning" onclick="location.href='/Sunny/jsp/member/loginForm.jsp'">로그인하기</button>
	</div>
	<br/>
</body>
</html>