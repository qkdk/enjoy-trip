<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>


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
	<jsp:include page="../common/header.jsp"></jsp:include>
	<!-- header 끝 -->
	<!-- 공지사항 컨테이너 -->
	<div class="container-fluid p-5" style="margin-top: 15vh">
		<!-- 제목 컨테이너 -->
		<div class="container-fluid">
			<h2 style="text-align: center">핫플레이스 페이지 입니다.</h2>
		</div>
		<!-- 검색 컨테이너 -->
		<div style="display: flex; justify-content: space-between">
			<div>
				<button type="button" class="btn btn-outline-warning"
					id="btn-mv-register">글쓰기</button>
				<button type="button" class="btn btn-outline-warning" id="btn-sort">조회순
					정렬하기</button>
			</div>

			<div style="display: flex; justify-content: end; flex-direction: row">
				<form id="form-search" action="" style="display: flex">
					<input type="hidden" name="action" value="list" /> <input
						type="hidden" name="pgno" value="1" /> <select class="form-select"
						name="key" id="key" aria-label="Default select example"
						style="margin-right: 5px">
						<option selected>검색조건</option>
						<option value="notice_no">글번호</option>
						<option value="user_id">작성자</option>
						<option value="notice_title">제목</option>
						<option value="notice_content">내용</option>
					</select> <input type="text" name="word" id="word" class="form-control"
						placeholder="검색어..." style="margin-right: 5px" />
					<intput id="btn-search" type="button"
						class="btn btn-outline-warning w-50">검색 </intput>
				</form>
			</div>

		</div>
		<!-- 테이블 컨테이너 -->
		<div>
			<table class="table table-striped table-hover mt-2">
				<thead>
					<tr class="text-center">
						<th scope="col" style="width: 15%">글번호</th>
						<th scope="col" style="width: 40%">제목</th>
						<th scope="col" style="width: 15%">작성자</th>
						<th scope="col" style="width: 15%">조회수</th>
						<th scope="col" style="width: 15%">작성일</th>
					</tr>
				</thead>
				<tbody>


					<c:forEach items="${placeList}" var="place">
						<tr class="text-center">
							<td aria-colindex="1" role="cell" class="tdClass">${place.placeNo}</td>
							<td aria-colindex="2" role="cell" class="tdSubject"><a
								href="${root}/place/${place.placeNo}" class="article-title"
								data-no="${place.placeNo}"> ${place.placeTitle} </a></td>
							<td aria-colindex="3" role="cell" class="tdClass">${place.userId}</td>
							<td aria-colindex="4" role="cell" class="tdClass">${place.hit}</td>
							<td aria-colindex="5" role="cell" class="tdClass">${place.placeCreateTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row">
			<ul class="pagination  justify-content-center">
				<li class="page-item" data-pg="1"><a
					href="${root }/place?pgno=1&key=${navigation.key}&word=${navigation.word}"
					class="page-link">최신</a></li>
				<c:if test="${navigation.startRange}">
					<li class="page-item" data-pg="1"><a
						href="${root}/place?pgno=1&key=${navigation.key}&word=${navigation.word}"
						class="page-link">이전</a></li>
				</c:if>
				<c:if test="${!navigation.endRange}">
					<li class="page-item" data-pg="${navigation.startPage - 1}"><a
						href="${root}/place&pgno=${navigation.startPage - 1}&key=${navigation.key}&word=${navigation.word}"
						class="page-link">다음</a></li>
				</c:if>

				<c:forEach begin="${navigation.startPage}"
					end="${navigation.endPage}" step="1" varStatus="status">
					<c:if test="${status.index == navigation.currentPage}">
						<li class="page-item active" data-pg="${status.index}"><a
							href="${root}/place?pgno=${status.index}&key=${navigation.key}&word=${navigation.word}"
							class="page-link">${status.index}</a></li>
					</c:if>
					<c:if test="${status.index != navigation.currentPage}">
						<li class="page-item" data-pg="${status.index}"><a
							href="${root}/place?pgno=${status.index}&key=${navigation.key}&word=${navigation.word}"
							class="page-link">${status.index}</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${navigation.endRange}">
					<li class="page-item" data-pg="${navigation.endPage}"><a
						href="${root}/place?pgno=${navigation.endPage}&key=${navigation.key}&word=${navigation.word}"
						class="page-link">다음</a></li>
				</c:if>
				<c:if test="${!navigation.endRange}">
					<li class="page-item" data-pg="${navigation.endPage + 1}"><a
						href="${root}/place?pgno=${navigation.endPage + 1}&key=${navigation.key}&word=${navigation.word}"
						class="page-link">다음</a></li>
				</c:if>
				<li class="page-item" data-pg="${navigation.totalPageCount}"><a
					href="${root}/place?pgno=${navigation.totalPageCount}&key=${navigation.key}&word=${navigation.word}"
					class="page-link">마지막</a></li>
			</ul>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>

  <%-- 검색 처리 --%>
  <%-- 추후에 REST 통신시 사용할 변수 --%>
  let key;
  let word;

  const keySel = document.querySelector("#key")
  keySel.addEventListener("change", () => {
    key = keySel.options[keySel.selectedIndex].value;
  })

  const wordInput = document.querySelector("#word")
  wordInput.addEventListener("change", () => {
    word = wordInput.value;
  })

  document.querySelector("#btn-search").addEventListener("click", function () {
    location.href = `${root}/notice?pgno=1&key=` + key + `&word=` + word;
  });

  <%--  검색 처리 --%>

  let titles = document.querySelectorAll(".article-title");
  titles.forEach(function (title) {
    console.log(title);
    title.addEventListener("click", function () {
      console.log(this.getAttribute("data-no"));
      location.href = "${root}/board?action=view&articleno=" + this.getAttribute("data-no");
    });
  });

  document.querySelector("#btn-sort").addEventListener("click", function () {
    location.href = "${root}/notice/sort?pgno=1&key=&word=";
  });

  document.querySelector("#btn-mv-register").addEventListener("click", function () {
    location.href = "${root}/place/write"
  });

  let pages = document.querySelectorAll(".page-link");
  pages.forEach(function (page) {
    page.addEventListener("click", function () {
      console.log(this.parentNode.getAttribute("data-pg"));
      document.querySelector("#p-action").value = "list";
      document.querySelector("#p-pgno").value = this.parentNode
      .getAttribute("data-pg");
      document.querySelector("#p-key").value = "${param.key}";
      document.querySelector("#p-word").value = "${param.word}";
      document.querySelector("#form-param").submit();
    });
  });
</script>
</body>
</html>

