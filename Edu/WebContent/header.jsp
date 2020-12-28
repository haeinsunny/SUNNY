<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>header</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet"
        type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>

<body id="page-top">
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand js-scroll-trigger" href="/Edu/jsp/common/main.jsp">EduAll</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars ml-1"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav text-uppercase ml-auto">
                    <c:if test="${id ne null}">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#">${name }님</a></li>
                    </c:if>

                    <li class="nav-item"><a class="nav-link js-scroll-trigger"
                            href="/Edu/jsp/eduList/eduMain.jsp">학원조회</a>
                    </li>

                    <c:if test="${id ne null || type ne null}">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#">상담조회</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#about">마이페이지</a></li>
                    </c:if>

                    <c:if test="${id eq null }">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger"
                                href="/Edu/jsp/user/joinForm.jsp">회원가입</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger"
                                href="/Edu/jsp/user/loginForm.jsp">로그인</a></li>
                    </c:if>

                    <c:if test="${id ne null || type ne null}">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="/Edu/Logout.do">로그아웃</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Bootstrap core JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Third party plugin JS-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <!-- Contact form JS-->
    <script src="assets/mail/jqBootstrapValidation.js"></script>
    <script src="assets/mail/contact_me.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body>

</html>