<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />
<!DOCTYPE html>
<html>

<head>
  <title>상담관리리스트</title>
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
  <style>
    .pagination {
      justify-content: center;
    }
  </style>
</head>

<body>
  <!-- Masthead-->
  <header class="masthead" style="padding-top: 12rem; padding-bottom:1rem;"></header>
  <!-- List -->
  <div class="container-fluid">
 
    <div class="row">
      <div class="col-sm-3" align="center">
        <br />
        <br />
        <br />
        <br />
        <h4>Consult List</h4>
        <br />
        <p>상담내역을 확인해 보세요:)</p>
      </div>

      <div class="col-sm-8">
      <br />
      <br />
        <br />
        <br />
        <table class="table table-hover" align="center">
          <tr align="center">
            <th width="80">신청번호</th>
            <th width="200">학원명</th>
            <th width="100">전화번호</th>
            <th width="100">주소</th>
            <th width="50">분류</th>
            <th width="80">신청일자</th>
            <th width="50">결과</th>
          </tr>
          <c:forEach var="vo" items="${rlist }">
            <tr class="record" align="center" onclick="location.href='/Edu/EduRead.do?no=${vo.r_no}'">
              <td width="100" align="center">${vo.r_no }</td>
              <td width="100" align="center">${vo.cname }</td>
              <td width="250">${vo.tel }</td>
              <td width="100" align="center">${vo.addr }</td>
              <td width="100" align="center">${vo.sort }</td>
              <td width="100" align="center">${vo.r_date }</td>
              <td width="100" align="center">${vo.result }</td>
            </tr>
          </c:forEach>
        </table>
        <br />

        <ul class="pagination pagination-sm" text-align="center">
          <li class="page-item"><a class="page-link" href="javascript:goPage(${params.firstPageNo})">처음 페이지</a>
          <li class="page-item"><a class="page-link" href="javascript:goPage(${params.prevPageNo})">이전 페이지</a>
            <span>
              <c:forEach var="i" begin="${params.startPageNo}" end="${params.finalPageNo}" step="1">
                <c:choose>
                  <c:when test="${i eq params.pageNo}">
          <li class="page-item active"><a class="page-link" href="javascript:goPage(${i})">${i}</a>
            </c:when>
            <c:otherwise>
          <li class="page-item"><a class="page-link" href="javascript:goPage(${i})">${i}</a></c:otherwise>
            </c:choose>
            </c:forEach>
            </span>
          <li class="page-item"><a class="page-link" href="javascript:goPage(${params.nextPageNo})" class="next">다음
              페이지</a>
          <li class="page-item"><a class="page-link" href="javascript:goPage(${params.finalPageNo})" class="next">마지막
              페이지</a>
        </ul>
      </div>
   
     <div class="col-sm-2" align="center"></div>
     </div>
    </div>
</body>

<script>
  function goPage(page) {
    location.href = "/Edu/ConReq.do?pageNum=" + page;
  }
</script>

</body>

</html>