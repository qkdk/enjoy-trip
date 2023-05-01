<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<%-- <%
String root = request.getContextPath();
%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Project</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css" />
<link rel="stylesheet" href="css/main.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet" />
<style>
body {
	font-family: "Jua", sans-serif;
}
</style>
<script>
	function setEmailDomain2(domain) {
		console.log(domain);
		document.querySelector("#email_domain2").setAttribute("value", domain);
		document.querySelector("#dropdownMenuButton2").innerText = domain;
	}
</script>
</head>
<body>
	<!-- 상단 navbar start -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="container"
		style="margin-top: 15vh; text-align: center; width: 50vw;">
		<h1>마이 페이지</h1>
		<div class="main">
			<input type="radio" id="tab-1" name="show" checked /> <input
				type="radio" id="tab-2" name="show" /> <input type="radio"
				id="tab-3" name="show" />
			<div class="tab">
				<label for="tab-1">회원정보 수정</label> <label for="tab-2">탈퇴하기</label>
			</div>
			<div class="content">
				<div class="content-dis">
					<div class="modal-body">
						<form id="form-modify" method="post" action="${root}/user/modify">
							<div class="row">
								<!-- 이름 -->
								<div style="display: flex; align-items: baseline">
									<label for="name" class="form-label text-center col-md-2">이름</label>
									<div class="p-3 ">
										<input type="text" class="form-control" id="name" name="userName"
											placeholder="이름" value="${userDto.userName}"/>
									</div>
								</div>
								<div style="display: flex; align-items: baseline">
									<label for="name" class="form-label text-center col-md-2 ">아이디</label>
									<div class="p-3">
										<input type="text" class="form-control" id="id" name="userId"
											value="${userDto.userId}" readonly="readonly"/>
									</div>
								</div>
								<div style="display: flex; align-items: baseline">
									<label for="name" class="form-label text-center col-md-2 ">비밀번호</label>
									<div class="p-3">
										<input type="password" class="form-control" id="password"
											name="userPw" placeholder="비밀번호" value="${userDto.userPw}" />
									</div>
								</div>
								<div style="display: flex; align-items: baseline">
									<label for="name" class="form-label text-center col-md-2">이메일</label>
									<div class="p-3" style="padding-right: 0px !important">
										<input type="text" class="form-control" id="email"
											name="userEmail" placeholder="이메일" value="${userDto.userEmail}"/>
									</div>
									<div class="" style="display: inline;">
										@
										<button class="btn bg-light dropdown-toggle" type="button"
											id="dropdownMenuButton2" data-bs-toggle="dropdown"
											aria-expanded="false">이메일을 선택하세요</button>
										<input type="hidden" name="userDomain" id="email_domain2" />
										<ul class="dropdown-menu"
											aria-labelledby="dropdownMenuButton2">
											<li><a class="dropdown-item" href="#"
												onclick="setEmailDomain2('naver.com')">naver.com</a></li>
											<li><a class="dropdown-item" href="#"
												onclick="setEmailDomain2('gmail.com')">gmail.com</a></li>
											<li><a class="dropdown-item" href="#"
												onclick="setEmailDomain2('kakao.com')">kakao.com</a></li>
										</ul>
									</div>
								</div>

							</div>
						</form>
						<div class="btn-class">
							<button class="btn btn-outline-info" id="btn-modify">수정하기
							</button>
						</div>
					</div>
				</div>
				<div class="content-dis" style="padding: 50px;">
					<!-- 탈퇴 -->
					<h4>탈퇴를 진행하려면 비밀번호를 입력해주세요.</h4>
					<div
						style="display: flex; align-items: baseline; justify-content: center;">
						<label for="name" class="form-label text-center  ">비밀번호</label>
						<div class="p-3 col-md-9">
							<form id="form-leave" method="post" action="${root}/user/delete">
								<input type="hidden" name="action" value="leave" /> <input
									type="text" class="form-control" id="password" name="userPw"
									placeholder="비밀번호" />
							</form>
						</div>

					</div>
					<button class="btn btn-outline-danger" id="btn-leave">탈퇴하기</button>
				</div>
				<div></div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	document.querySelector("#btn-modify").addEventListener("click", function() {
		let form = document.querySelector("#form-modify");
		/* form.setAttribute("action", "${root}/user"); */
		form.submit();
	})

	document.querySelector("#btn-leave").addEventListener("click", function() {
		let form = document.querySelector("#form-leave");
		/* form.setAttribute("action", "${root}/user"); */
		form.submit();
	})
</script>
</html>
