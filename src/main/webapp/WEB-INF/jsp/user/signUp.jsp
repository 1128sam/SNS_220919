<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- jquery : bootstrap, datepicker -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  

	<!-- bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h2 class="text-center pt-3 pb-3">회원가입</h2>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<div class="d-flex justify-content-center">
				<div class="col-4">
					<div class="my-2 font-weight-bold">아이디</div>
					<input type="text" class="form-control" name="userId" id="userId">
					<button type="button" id="loginIdCheckBtn" class="btn btn-warning">중복확인</button>
						<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
						<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
						<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
					<div class="my-2 font-weight-bold">비밀번호</div>
					<input type="password" class="form-control" name="password"
						id="password">
					<div class="my-2 font-weight-bold">비밀번호 확인</div>
					<input type="password" class="form-control" name="passwordCheck"
						id="passwordCheck">
					<div class="my-2 font-weight-bold">이메일</div>
					<input type="text" class="form-control" name="email" id="email">
					<div class="my-2 font-weight-bold">이름</div>
					<input type="text" class="form-control" name="name" id="name">
					<button type="button" id="signUpBtn" class="btn btn-danger w-100 mt-3">가입하기</button>
					<div class="d-flex justify-content-end my-2">
						<a href="/user/sign_in_view">로그인하기</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<script>
		$(document).ready(function() {
			$('#loginIdCheckBtn').on('click', function() {
				$('#idCheckLength').addClass('d-none');
				$('#idCheckDuplicated').addClass('d-none');
				$('#idCheckOk').addClass('d-none');
				let loginId = $('input[name=userId]').val().trim();
				if (loginId.length < 4) {
					$('#idCheckLength').removeClass('d-none');
					return;
				}
				
				$.ajax({
					// request
					url:"/user/is_duplicated_id"
					, data:{"loginId":loginId}
					
					// response
					, success:function(data) {
						// 성공
						if (data.result) {
							// 중복
							$('#idCheckDuplicated').removeClass('d-none');
						} else {
							// 사용 가능
							$('#idCheckOk').removeClass('d-none');
						}
					}
					, error:function(e) {
						alert("중복확인에 실패했습니다.");
					}
				});
			});
			
			$('#signUpForm').on('submit', function() {
				let loginId = $('input[name=userId]').val().trim();
				let password = $('input[name=password]').val().trim();
				let passwordCheck = $('input[name=passwordCheck]').val().trim();
				let email = $('input[name=email]').val().trim();
				let name = $('input[name=name]').val().trim();
				
				if (loginId.length < 1) {
					alert("아이디를 입력하세요.");
					return false;
				}
				if (password.length < 1) {
					alert("비밀번호를 입력하세요.");
					return false;
				}
				if (password != passwordCheck) {
					alert("비밀번호가 일치하지 않습니다.");
					return false;
				}
				if (name.length < 1) {
					alert("이름을 입력하세요.");
					return false;
				}
				if (email.length < 1) {
					alert("이메일 주소를 입력하세요.");
					return false;
				}
				
				// 아이디 중복확인 완료 됐는지 확인 -> idCheckOk d-none을 가지고 있으면 확인하라는 얼럿 띄워야함
				if ($('#idCheckOk').hasClass('d-none')) {
					alert("아이디 중복확인을 다시 해주세요.");
					return false;
				}
				
				let url = $(this).attr('action');
				let params = $(this).serialize(); // form태그에 있는 name으로 파라미터들 구성
				
				$.post(url, params)
				.done(function(data) {
					if (data.code == 1) {
						alert("가입을 환영합니다! 로그인해주세요.")
						location.href = "/user/sign_in_view";
					} else {
						alert(data.errorMessage);
					}
				});
			});
		});
	</script>
</body>
</html>