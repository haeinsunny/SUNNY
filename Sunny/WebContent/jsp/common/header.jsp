<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>헤더</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<style>

</style>

<body>
  <div class="jumbotron text-center" style="margin-bottom:0">
    <h1>해인의 작업공간♡</h1>
    <p>함께 재밌고 행복한 시간 보내고 싶으신분들 환영!</p>
  </div>

  <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
      <!-- Brand -->
      <a class="navbar-brand" href="#">Blog</a>

      <!-- Links -->
      
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="#">자유게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">공지사항</a>
          </li>
        
      
      <!-- Dropdown -->
        
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
              회원관리
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="#">회원정보 관리</a>
              <a class="dropdown-item" href="#">A 관리</a>
              <a class="dropdown-item" href="#">B 관리</a>
            </div>
          </li>
      </ul>
    </div>

    <div class="mx-auto order-0">
      <a class="navbar-brand mx-auto" href="#">Sunny's Home</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
      <ul class="navbar-nav ml-auto">
        
          <li class="nav-item">
            <a class="nav-link" href="#">마이페이지</a>
          </li>
       
          <li class="nav-item">
            <a class="nav-link" href="#">로그아웃</a>
          </li>
       
          <li class="nav-item">
            <a class="nav-link" href="#">로그인</a>
          </li>

      </ul>
    </div>
  </nav>
  <br>
</body>

</html>