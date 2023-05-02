<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>공지작성</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
          rel="stylesheet"/>

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
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
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
            글수정</h2>
    </div>
    <form id="form-modify" method="POST" action="">
        <input type="hidden" name="noticeNo" value="${notice.noticeNo}">

        <div style="margin-bottom: 10px">

            <label for="subject">제목:</label> <input type="text"
                                                    class="form-control" id="subject"
                                                    name="noticeTitle"
                                                    style="margin-right: 5px"
                                                    value="${notice.noticeTitle}"/>
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea class="form-control" id="content" name="noticeContent"
                      placeholder="내용입력..." style="height: 254px">${notice.noticeContent}</textarea>
        </div>
        <div style="display: flex; flex-direction: row; padding-top: 10px">
            <button type="button" class="btn btn-primary" id="btn-modify"
                    style="margin-right: 10px">글수정
            </button>
            <button type="button" id="btn-list" class="btn btn-danger">목록</button>
        </div>
    </form>

</div>
<!-- footer -->

<!-- footer -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script>
  document.querySelector("#btn-modify").addEventListener("click",
      function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-modify");
          form.setAttribute("action", "${root}/notice/modify");
          form.submit();
        }
      });
  document
  .querySelector("#btn-list")
  .addEventListener(
      "click",
      function () {
        location.href = "${root}/notice?pgno=1&key&word=";
      });
</script>
</body>
</html>
