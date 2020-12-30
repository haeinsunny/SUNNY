<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/header.jsp" />
<!DOCTYPE html>
<html>

<head>
	<title>회원가입</title>
	<meta charset="UTF-8">
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<link rel="icon" type="image/x-icon" href="/Edu/assets/img/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
		integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet"
		type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="/Edu/css/styles.css" rel="stylesheet" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="https://rawgithub.com/tomyun/crypto-js/seed-3.1.2/build/rollups/seed.js"></script>
</head>

<body>
	<!-- Masthead-->
	<header class="masthead" style="padding-top: 12rem; padding-bottom:1rem;"></header>
	<br />
	<br />
	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" align="center">
				<h4>가입 정보 입력</h4>
				<br />
				<form id="frm" name="frm" action="/Edu/Join.do" method="post">
					<table border="1">
						<tr align="center">
							<th height="60" width="200">아이디</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="text" class="username_input" id="id"
									name="id" value="" required>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="check" name="check" class="btn btn-light" onclick="id_check()"
									value="unchecked">중복체크
								</button>
								<i style='font-size: 24px; display: none;' class="fas fa-check" id="check_sucess"></i>
							</td>
						</tr>

						<tr align="center">
							<th height="60" width="200">비밀번호</th>
							<td width="350" align="left">&nbsp;&nbsp;
								<input type="password" id="pw" name="pw" value="" required>
								<input type="hidden" id="epw" name="epw" value="">
								<input type="hidden" id="depw" name="depw" value="">
							</td>
						</tr>

						<tr align="center">
							<th height="60" width="200">비밀번호 재입력</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="password" id="pw2" name="pw2" value=""
									required></td>
						</tr>

						<tr align="center">
							<th height="60" width="200">구분</th>
							<td width="350" align="left">&nbsp;
								<select name="type" id="type" style="width:120px; height:30px" onchange="select()">
									<option value="user">학생/학부모</option>
									<option value="client">학원</option>
								</select>&nbsp;&nbsp;&nbsp;&nbsp;

								<select name="sort" id="sort" style="width:100px; height:30px; display: none;">
									<option value="입시">입시</option>
									<option value="단과">단과</option>									
								</select>
							</td>
						</tr>

						<tr align="center" id="uname">
							<th height="60" width="200">학생이름</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="text" id="name" name="name" value=""
									required></td>
						</tr>

						<tr align="center" id="uage">
							<th height="60" width="200">학생나이</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="number" id="age" name="age" value=""
									style="width:80px; height:30px" required>&nbsp;세</td>
						</tr>

						<tr align="center" style="display: none;" id="cname">
							<th height="60" width="200">학원명</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="text" id="aname" name="aname" value="">
							</td>
						</tr>

						<tr align="center">
							<th height="60" width="200">전화번호</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="text" id="tel" name="tel" value=""
									required>&nbsp;&nbsp;'-' 제외하고 입력</td>
						</tr>

						<tr align="center">
							<th height="60" width="200">주소</th>
							<td width="350" align="left">&nbsp;&nbsp;<input type="text" id="addr" name="addr" value=""
									required>&nbsp;&nbsp;'구' 까지만 입력</td>
						</tr>
					</table>

					<br />
					<button type="submit" class="btn btn-warning" onclick="btn()">가입</button>
					&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-light" onclick="location.href='/Edu/EduList.do'">취소</button>
				</form>
			</div>
		</div>
		<div class="col-sm-3" align="center"></div>
	</div>
</body>

<script>
	//type에 따른 폼 구조 
	$("#type").change(function () {
		var selectVal = $(this).val;
		if (selectVal = 'client') {
			$("#sort").show();
			$("#cname").attr("required", true);
			$("#name").attr("required", false);
			$("#age").attr("required", false);
			$("#cname").show();

			$("#uname").hide();
			$("#uage").hide();
		}else{
			$("#sort").hide();
		}
	})

	//아이디 중복체크
	function id_check() {
		var id = document.getElementById('id').value;
		$.ajax({
			url: '/Edu/IdCheck.do',
			data: {
				id: id
			},
			dataType: 'json',
			success: function (data) {
				console.log(data);

				if (data == "") {
					$("#check").hide();
					$("#check_sucess").show();
					$("#check").attr('value', 'checked');
				} else {
					alert("이미 존재하는 아이디 입니다 :(");
				}

			},
			error: function (reject) {
				//alter("에러입니다.")
				console.log(reject);
			}
		})

	}

	function btn() {
		//required
		var pw = document.getElementById('pw').value;
		var pw2 = document.getElementById('pw2').value;
		var check = document.getElementById('check').value;

		if (pw2 != pw) {
			alert("비밀번호가 일치하지 않습니다.");
		} else if (check == "unchecked") {
			alert("아이디 중복체크를 해주세요.");
		} else {
			frm.action = "/Edu/Join.do";
		}
	}
</script>

</html>