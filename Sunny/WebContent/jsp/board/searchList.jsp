<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>검색결과페이지</title>

  <head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
      .record:hover {
        background: lavenderblush;
      }

      ul.pagination {
        background: none;
      }

      .search {
        background-color: white;
      }
    </style>
  </head>

<body>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-6"></div>
      <div class="col-sm-2" align="right">
        <br>
        <br>
        <h4>자유게시판</h4>
      </div>
      <div class="col-sm-3 align="">
      <br>
       <br>
        <br>
         <br>
      <form action="/Sunny/SearchList.do"> <table class="search" align="left">
        <tr>

          <td>
            <select name="search" id="search">
              <c:choose>
                <c:when test="${search eq 'writer' }">
                  <option value="writer" selected>작성자</option>
                </c:when>
                <c:otherwise>
                  <option value="writer">작성자</option>
                </c:otherwise>
              </c:choose>
              <c:choose>
                <c:when test="${search eq 'title' }">
                  <option value="title" selected>제목</option>
                </c:when>
                <c:otherwise>
                  <option value="title">제목</option>
                </c:otherwise>
              </c:choose>
              <c:choose>
                <c:when test="${search eq 'content' }">
                  <option value="content" selected>내용</option>
                </c:when>
                <c:otherwise>
                  <option value="content">내용</option>
                </c:otherwise>
              </c:choose>
            </select>
          </td>

          <td>
            <input type="text" name="word" id="word" value="${word }">
          </td>&nbsp;
          <td>
            <input type="submit" class="btn btn-outline-info btn-sm" value="검색"></td>
        </tr>
        </table>
        </form>
      </div>
      <div class="col-sm-1"></div>
    </div>
    <br>

    <div class="row">
      <div class="col-sm-1"></div>
      <div class="col-sm-3" align="center">
        <br />
        <h3>Board List</h3>
        <p>소소한 이야기를 나누세요:)</p>
        <br />
        <c:if test="${auth ne 'admin' }">
          <button type="button" class="btn btn-outline-info btn-sm"
            onclick="location.href='/Sunny/jsp/board/boardInput.jsp'">글 등록하기</button>
        </c:if>
      </div>
      <div class="col-sm-7">
        <table border="1" align="center">
          <tr align="center">
            <th width="100" style="background-color:lavender;">번호</th>
            <th width="250" style="background-color:lavenderblush;">제목</th>
            <th width="100" style="background-color:lavender;">작성시간</th>
            <th width="100" style="background-color:lavenderblush;">ID</th>
            <th width="100" style="background-color:lavender;">조회수</th>
          </tr>
          <c:forEach var="vo" items="${slist }">
            <tr class="record" onclick="location.href='/Sunny/BoardOne.do?no=${vo.bno}'">
              <td width="100" align="center">${vo.bno }</td>
              <td width="250">${vo.btitle }</td>
              <td width="100" align="center">${vo.bdate }</td>
              <td width="100" align="center">${vo.bid }</td>
              <td width="100" align="center">${vo.bhit }</td>
            </tr>
          </c:forEach>
        </table>
        <br />

        <ul class="pagination pagination-sm" align="right">
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
    let search = document.getElementById('search').value;
    let word = document.getElementById('word').value;
    location.href = "/Sunny/SearchList.do?pageNum=" + page + "&search=" + search + "&word=" + word;
  }
  
</script>

</html>