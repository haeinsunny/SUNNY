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
    <header class="masthead" style="padding-top: 12rem; padding-bottom:1rem;"></header>
    <!-- List -->
    <div class="container">
        <div class="row">
            <div class="col-sm-12" align="center">
                <br />
                <br />
                <br />
                <div align="center">
                    <h4>${vo.name }</h4>
                </div>
                <br />
                <br />
                <div align="center">
                    <table border="1">
                        <tr align="center">
                            <th width="100" height="40">번호</th>
                            <td width="100">${vo.e_no }</td>
                            <th width="100">등록일자</th>
                            <td width="100">${vo.e_date }</td>
                            <th width="100">유형</th>
                            <td width="100">${vo.sort }</td>
                        </tr>
                        <tr align="center">
                            <th width="100" height="40">학원명</th>
                            <td colspan="5">${vo.name }</td>
                        </tr>
                        <tr align="center">
                            <th width="100" height="40">주소</th>
                            <td colspan="3">${vo.addr }구
                            </td>
                            <th width="100">전화번호</th>
                            <td colspan="2">${vo.tel }
                            </td>
                        </tr>
                    </table>
                    <br />
                    <br />
                    <c:if test="${type eq 'user' }">
                        <div align="center">
                            <h5>상담 버튼을 누르면 신청이 완료됩니다.</h4>
                        </div>
                    </c:if>
                    <br />
                    <div>
                        <form id="frm" name="frm" action="" method="post">
                            <c:if test="${type eq 'user' }">
                                <input type="hidden" id="name" name="name" value="${name }">
                                <input type="hidden" id="cname" name="cname" value="${vo.name }">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-success"
                                    onclick="consultReq()">상담신청</button>&nbsp;&nbsp;&nbsp;
                            </c:if>
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
<script type="text/javascript">

function consultReq() {
		var name = document.getElementById('name').value;
		var cname = document.getElementById('cname').value;
		$.ajax({
			url : '/Edu/EduConsult.do',
			data : {
				name:name,
				cname:cname
			},
			//dataType : 'json',
			success : function(data) {
				console.log('hhh');
                alert('상담신청 완료 했습니다.');
			},
			error : function() {
				alert('상담신청 실패 했습니다.');
			}
		})
	}
	
</script>
</html>