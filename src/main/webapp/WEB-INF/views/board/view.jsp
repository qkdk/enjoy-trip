<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ssafy.board.model.BoardDto, com.ssafy.member.model.dto.MemberDto, java.util.List"%>
<%
String root = request.getContextPath();
BoardDto article = (BoardDto) request.getAttribute("article");
MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>공지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/main.css" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<style>
* {
	margin: 0;
}

body {
	font-family: "Jua", sans-serif;
}
</style>
</head>
<body>
	<!-- header 시작 -->
		<jsp:include page="/common/header.jsp"></jsp:include>
	<!-- header 끝 -->
	<!-- 공지사항 컨테이너 -->
	<div class="container-fluid p-5" style="margin-top: 15vh">
		<!-- 제목 컨테이너 -->
		<div class="container-fluid">
			<h2 style="text-align: center">공지사항</h2>
		</div>
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
						제목 : <%=article.getSubject()%>
				</h2>
			</div>
			<div class="col-lg-8 col-md-10 col-sm-12">
				<div class="row my-2">
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="clearfix align-content-center">
							<img class="avatar me-2 float-md-start bg-light p-2"
								src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
							<p>
								<span class="fw-bold">작성자 : <%=article.getUser_id()%></span> <br />
								<span class="text-secondary fw-light"> 작성일 : <%=article.getRegisterTime()%>
								</span>
								<span class="text-secondary fw-light"> 
									조회 : <%=article.getHit()%>
								</span>
							</p>
						</div>
					</div>
					<div class="divider mb-3"></div>
					<div class="text-secondary">
						<%=article.getContent()%>
					</div>
					<div class="divider mt-3 mb-3"></div>
					<div class="d-flex justify-content-end">
						<button type="button" id="btn-list-return"
							class="btn btn-outline-primary mb-3">글목록</button>
						<%
						if (memberDto.getUserId().equals(article.getUser_id())) {
						%>
						<button type="button" id="btn-mv-modify"
							class="btn btn-outline-success mb-3 ms-1">글수정</button>
						<button type="button" id="btn-delete"
							class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
						<%
						}
						%>

					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- footer -->
	<jsp:include page="/common/footer.jsp"></jsp:include>
	<script>
      document.querySelector("#btn-list-return").addEventListener("click", function () {
    	  
        location.href = "<%=root%>/board?action=list&pgno=1&key=&word=";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
          
          location.href = "<%=root%>/board?action=mvmodify&articleno=<%=article.getArticle_no()%>";
        });
      document.querySelector("#btn-delete").addEventListener("click", function () {
    	  
        location.href = "<%=root%>/board?action=delete&articleno=<%=article.getArticle_no()%>";
      });
      
	</script>
</body>
</html>