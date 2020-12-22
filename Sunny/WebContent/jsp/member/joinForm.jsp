<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<jsp:include page="/jsp/common/header.jsp" />      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<br />
	<br />
	<div class="container">
		<div class="row">
			<div class="col-sm-12" align="center">
				<div>
					<h4>가입 정보 입력</h4>
					<br />
				</div>
				<div align="center">
					<form id="frm" name="frm" action="" method="post">
						<table border="1">
							<tr align="center">
								<th height="60" width="200">아이디 입력</th>
								<td width="350" align="left">&nbsp;&nbsp;<input type="text"
									class="username_input" id="id" name="id" value="" required>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="check" name="check" class="btn btn-light"
										onclick="id_check()" value="unchecked">중복체크</button> <i
									style='font-size: 24px; display: none;' class='fas'
									id="check_sucess">&#xf00c;</i></td>
							</tr>
							<tr align="center">
								<th height="60" width="200">비밀번호 입력</th>
								<td width="350" align="left">&nbsp;&nbsp;<input type="text"
									id="pw" name="pw" value="" required></td>
							</tr>
							<tr align="center">
								<th height="60" width="200">비밀번호 재입력</th>
								<td width="350" align="left">&nbsp;&nbsp;<input type="text"
									id="pw2" name="pw2" value="" required></td>
							</tr>
							<tr align="center">
								<th height="60" width="200">이름</th>
								<td width="350" align="left">&nbsp;&nbsp;<input type="text"
									id="name" name="name" value="" required></td>
							</tr>
						</table>
						<br />
						<button type="submit" class="btn btn-warning" onclick="btn()">가입</button>
						&nbsp;&nbsp;&nbsp;
						<button type="reset" class="btn btn-light"
							onclick="location.href='/Sunny/BorderList.do'">취소</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br />
	<br />
</body>
<script>
	function id_check() {
		var id = document.getElementById('id').value;
		$.ajax({
			url : '/Member/MemberIdCheck.do',
			data : {id:id},
			dataType: 'json',
			success : function(data) {
				console.log(data);
					
				if(data == ""){
					$("#check").hide();	
					$("#check_sucess").show();	
					$("#check").attr('value', 'checked');									
				}else{
					alert("이미 존재하는 아이디 입니다 :(");
				}

			},
			error : function() {
				alter("에러입니다.")
			}
		})

	}

	function btn() {
		var pw = document.getElementById('pw').value;
		var pw2 = document.getElementById('pw2').value;
		var check = document.getElementById('check').value;

		if (pw2 != pw) {
			alert("비밀번호가 일치하지 않습니다.");
		} else if (check == "unchecked") {
			alert("아이디 중복체크를 해주세요.");
		} else {
			frm.action = "/Member/MemberJoin.do";
		}
	}
</script>