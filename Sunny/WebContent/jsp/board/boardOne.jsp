<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/common/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세조회</title>
</head>
<body>
 <div class="container">
        <div class="row">
            <div class="col-sm-12" align="center">
                <br />
                <br />
                <div align="center">
                    <h4>글 상세보기</h4>
                </div>
                <br />
                <br />
                <div align="center">
                    <table border="1">
                        <tr align="center">
                            <th width="100">작성자</th>
                            <td width="100">${vo.bid}</td>
                            <th width="100" >작성일</th>
                            <td width="100">${vo.bdate}</td>
                            <th width="100" >조회수</th>
                            <td width="100">${vo.bhit}</td>
                        </tr>
                        <tr align="center">
                            <th width="100" >제목</th>
                            <td colspan="5">${vo.btitle}</td>
                        </tr>
                        <tr align="center">
                            <th width="100">내용</th>
                            <td colspan="5">
                                <textarea rows="10" cols="70" readonly>${vo.bcontent}</textarea>
                            </td>
                        </tr>
                    </table>
                    <br />
                    <div>
                        <form id="frm" name="frm" action="" method="post">
                            <input type="hidden" id="no" name="no" value="${vo.bno }">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="submit" class="btn btn-success" onclick='btn("edit")'>수정</button>&nbsp;&nbsp;&nbsp;
                            <button type="submit" class="btn btn-dark" onclick='btn("del")'>삭제</button>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-secondary" onclick="location.href='/Sunny/BoardList.do'">목록</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
</body>
<script>
    function btn(str) {
        if (str == "edit") {
            frm.action = "/Sunny/BoardEdit.do";
        } else if (str == "del") {
            frm.action = "/Sunny/BoardDelete.do";
        }
    }
</script>

</html>