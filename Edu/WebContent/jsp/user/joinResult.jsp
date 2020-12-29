<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />
<!DOCTYPE html>
<html>

<head>
  <title>회원가입 결과</title>
  <meta charset="UTF-8">
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <link rel="icon" type="image/x-icon" href="/Edu/assets/img/favicon.ico" />
  <!-- Font Awesome icons (free version)-->
  <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
  <!-- Google fonts-->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
  <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet"
    type="text/css" />
  <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="/Edu/css/styles.css" rel="stylesheet" />
</head>

<body>
  <!-- Masthead-->
  <header class="masthead" style="padding-top: 12rem; padding-bottom:1rem;"></header>
  <br />
  <br />
  <br />
  <div align="center">
    <h4>${msg }</h4>
    <br />
    <button class="btn btn-warning" onclick="location.href='/Edu/jsp/user/loginForm.jsp'">로그인하기</button>
  </div>
  <br />
</body>

</html>