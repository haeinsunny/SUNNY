<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />
<!DOCTYPE html>
<html>

<head>
  <title>seoul</title>
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
  <header class="masthead" style="padding-top: 8rem; padding-bottom:1rem;"></header>
  <!-- List -->
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-4"></div>
      <div class="col-sm-3" align="right">
        <br>
        <br>
        <br>
        <h4>서울</h4>
      </div>
      <div class="col-sm-3"></div>
      <div class="col-sm-1">
        <br>
        <br>
        <br>
        <br>
        <br>
        <form action="/Edu/SearchList.do">
          <table class="sort">
            <tr>
              <td>
                <select name="sort" id="sort" style="width:80px; height:30px">
                  <option value="A">대형</option>
                  <option value="B">입시</option>
                  <option value="C">단과</option>
                </select>
              </td>
              <td>
                <input type="button" class="btn btn-outline-info btn-sm" value="분류">
              </td>
            </tr>
          </table>
        </form>
      </div>
    </div>
    <br>
    <div class="col-sm-1"></div>

    <div class="row">
      <div class="col-sm-3" align="center">
        <br />
        <h3>Edu List</h3>
        <p>학원을 찾아보세요:)</p>
        <br />
        <c:if test="${type eq 'client' }">
          <button type="button" class="btn btn-outline-info btn-sm"
            onclick="location.href='/Sunny/jsp/eduList/eduInput.jsp'">학원 등록</button>
        </c:if>

        <form action="/Edu/SearchList.do">
          <table class="search">
            <tr>
              <td>
                학원명: <input type="text" name="word" id="word" value="" style="width:120px;">
              </td>
              <td>
                <input type="submit" class="btn btn-outline-info btn-sm" value="검색">
              </td>
            </tr>
          </table>
        </form>

      </div>

      <div class="col-sm-8">
        <table class="table table-hover" align="center">
          <tr align="center">
            <th width="100">등록번호</th>
            <th width="250">학원명</th>
            <th width="100">분류</th>
            <th width="100">주소</th>
            <th width="100">등록일자</th>
          </tr>
          <c:forEach var="vo" items="${elist }">
            <tr class="record" align="center" onclick="location.href='/Edu/EduRead.do?no=${vo.e_no}'">
              <td width="100" align="center">${vo.e_no }</td>
              <td width="250">${vo.name }</td>
              <td width="100" align="center">${vo.sort }</td>
              <td width="100" align="center">${vo.addr }구</td>
              <td width="100" align="center">${vo.e_date }</td>
            </tr>
          </c:forEach>
          <c:forEach var="no" items="${list }">
            <tr class="record" align="center" onclick="location.href='/Edu/EduRead.do?no=${no.e_no}'">
              <td width="100" align="center">${no.e_no }</td>
              <td width="250">${no.name }</td>
              <td width="100" align="center">${no.sort }</td>
              <td width="100" align="center">${no.addr }구</td>
              <td width="100" align="center">${no.e_date }</td>
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
      <div class="col-sm-1"></div>
    </div>
</body>

<script>
  function goPage(page) {
    location.href = "/Edu/EduList.do?pageNum=" + page;
  }
</script>

</body>

</html>