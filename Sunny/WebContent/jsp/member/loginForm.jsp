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
    <div align="center" class="container">
        <br />
        <div class="row">
            <div class="col-sm-12" align="center">
                <div>
                    <h1>로그인</h1>
                </div>
                <br />
                <div>
                    <form id="frm" name="frm" action="/Sunny/Login.do" method="post">
                        <table border="1">
                            <tr align="center">
                                <th width="100">아 이 디</th>
                                <td width="200" align="left">
                                    <input type="text" id="id" name="id">
                                </td>
                            </tr>
                            <tr align="center">
                                <th width="100">비 밀 번 호</th>
                                <td width="200" align="left">
                                    <input type="password" id="pw" name="pw">
                                </td>
                            </tr>
                        </table>
                        <br />
                        <input type="submit" class="btn btn-light" value="확인">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>