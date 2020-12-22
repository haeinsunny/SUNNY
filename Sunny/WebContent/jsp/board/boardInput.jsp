<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<jsp:include page="/jsp/common/header.jsp" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>게시판 작성</title>

</head>

<body>
    <br />
    <br />
    <div class="container">
        <div class="row">
            <div class="col-sm-12" align="center">
                <div>
                    <h4>글 작성</h4>
                    <br />
                </div>

                <div align="center">
                    <form id="frm" name="frm" action="/Sunny/BoardInput.do" method="post">
                        <table border="1">
                            <tr align="center">
                                <th width="100">작성자</th>
                                <td width="300" align="left"><input type="text" id="id" name="id" value="${id }" readonly></td>
                            </tr>
                            <tr align="center">
                                <th width="100">날짜</th>
                                <td width="300" align="left"><input type="text" id="date" name="date" readonly></td>
                            </tr>
                            <tr align="center">
                                <th width="100">제목</th>
                                <td width="300" align="left"><input type="text" id="title" name="title"></td>
                            </tr>
                            <tr align="center">
                                <th width="100">내용</th>
                                <td width="300" align="left"><textarea name="content" id="content" cols="50"
                                        rows="20"></textarea>
                                </td>
                            </tr>
                        </table>
                        <br />
                        <button type="submit" class="btn btn-warning">입력</button> &nbsp;&nbsp;&nbsp;
                        <button type="reset" class="btn btn-light">취소</button> &nbsp;&nbsp;&nbsp;
                        <button type="submit" class="btn btn-secondary"
                            onclick="location.href='/Sunny/BoardList.do'">목록</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <br />
    <br />
</body>
<script>

    //현재시각 구하기
    let today = new Date();
    let year = today.getFullYear(); // 년도
    let month = today.getMonth() + 1; // 월
    let dd = today.getDate(); // 날짜

    let d = year + '-' + month + '-' + dd;

    let date = document.getElementById('date');
    date.value = d;

</script>

</html>