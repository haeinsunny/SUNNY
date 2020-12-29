<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />
<!DOCTYPE html>
<html>

<head>
    <title>로그인</title>
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
    <script src="https://rawgithub.com/tomyun/crypto-js/seed-3.1.2/build/rollups/seed.js"></script>

<body>
    <!-- Masthead-->
    <header class="masthead" style="padding-top: 12rem; padding-bottom:1rem;"></header>
    <br />
    <br />
    <div align="center" class="container">
        <div class="row">
            <div class="col-sm-4" align="center"> </div>

            <div class="col-sm-4" align="center">
                <br />
                <br />
                <div>
                    <h3>Login</h3>
                </div>
                <br />

                <div>
                    <h5 style='color:#e6b301;'>${msg }</h5>
                </div>
                <br />

                <div class="form-group">
                    <form action="/Edu/Login.do">
                        <div class="row">
                            <div class="col">
                                <input type="text" class="form-control" placeholder="아이디 입력" id="id" name="id"
                                    width="100">
                            </div>
                            <div class="col">
                                <input type="password" class="form-control" placeholder="패스워드 입력" name="pw" width="100">
                            </div>
                            <br />
                            <br />
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">로그인</button>
                    </form>
                </div>
                <div class="col-sm-4" align="center"> </div>
            </div>
        </div>
    </div>
</body>

</html>