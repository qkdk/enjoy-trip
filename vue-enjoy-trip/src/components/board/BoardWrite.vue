<template>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>공지작성</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet" />
</head>
<body>
	<!-- header 시작 -->
	<!-- header 끝 -->
	<!-- 공지사항 컨테이너 -->
	<div class="container" style="margin-top: 15vh">
		<!-- 제목 컨테이너 -->
		<div class="container-fluid" style="margin-bottom: 10px">
			<h2 style="text-align: center">공지작성</h2>
		</div>
		<div>
			<h2
				style="text-align: center; background-color: #d1ecf1; border-radius: 5px; padding: 15px">
				글작성</h2>
		</div>
		<form id="form-register" method="POST" action="${root}/notice/write">
			<div style="margin-bottom: 10px">

				<label for="subject">제목:</label> <input type="text"
					class="form-control" id="subject" name="noticeTitle"
					style="margin-right: 5px" /> <label>제목을 입력하세요</label>
			</div>
			<div>
				<label for="content">내용:</label>
				<textarea class="form-control" id="content" name="noticeContent"
					placeholder="내용입력..." style="height: 254px"></textarea>
			</div>
			<div style="display: flex; flex-direction: row; padding-top: 10px">
				<button type="button" class="btn btn-primary" id="btn-register"
					style="margin-right: 10px">글작성</button>
				<button type="button" class="btn btn-danger" id="btn-list-return">목록</button>
			</div>
		</form>

	</div>
</body>
</html>
</template>

<script>
export default {

}
</script>

<style>

</style>