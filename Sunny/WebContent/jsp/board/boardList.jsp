<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/common/header.jsp" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>로그인 폼</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
            <br/>
                <h3>Board List</h3>
                <p>소소한 이야기를 나누세요:)</p>
                <br/>
                <c:if test="${auth ne 'admin' }">
                <button type="button" class="btn btn-outline-info btn-sm"
                    onclick="location.href='#'">등록하기</button>
                </c:if>
            </div>

            <div class="col-sm-9">
                <br/>
                <div align="center">
                    <h3>자유게시판</h3>
                </div>
                <br />
                <table class="table table-hover">
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성시간</th>
                        <th>ID</th>
                        <th>조회수</th>
                    </tr>
                    <c:forEach var="no" items="${list }">
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성시간</th>
                            <th>ID</th>
                            <th>조회수</th>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</body>

</html>