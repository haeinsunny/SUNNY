<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />
<!DOCTYPE html>
<html>

<head>
    <title>read</title>
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
    <header class="masthead" style="padding-top: 8rem; padding-bottom:1rem;"></header>
    <!-- List -->
    <div class="container">
        <div class="row">
            <div class="col-sm-12" align="center">
                <br />
                <br />
                <br />
                <div align="center">
                    <h4>${vo.name }학원이름</h4>
                </div>
                <br />
                <div align="center">
                    <table border="1">
                        <tr align="center">
                            <th width="100" height="40">번호</th>
                            <td width="100">${vo.e_no}</td>
                            <th width="100">등록일자</th>
                            <td width="100">${vo.e_date}</td>
                            <th width="100">유형</th>
                            <td width="100">${vo.sort}</td>
                        </tr>
                        <tr align="center">
                            <th width="100"  height="40">학원명</th>
                            <td colspan="5">${vo.name}</td>
                        </tr>
                        <tr align="center">
                            <th width="100"  height="40">주소</th>
                            <td colspan="3">${vo.bcontent}
                            </td>
                            <th width="100">전화번호</th>
                            <td colspan="2">${vo.bcontent}
                            </td>
                        </tr>
                    </table>                  
                    <br />
                    <br />
                    <div align="center">
                        <h5>상담 버튼을 누르면 신청이 완료됩니다.</h4>
                    </div>
                    <br />
                    <div>
                        <form id="frm" name="frm" action="" method="post">
                            <input type="hidden" id="no" name="no" value="${vo.e_no }">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="submit" class="btn btn-success"
                                onclick="location.href='/Edu/EduConsult.do'">상담신청</button>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-secondary"
                                onclick="location.href='/Edu/EduList.do'">목록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br />
</body>

</html>