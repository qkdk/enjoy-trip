<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script>
      function setEmailDomain(domain) {
        document.querySelector("#email_domain").setAttribute("value", domain);
        document.querySelector("#dropdownMenuButton1").innerText = domain;
      }</script>
</head>
<body>
<nav
        class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top pe-3">
    <div class="container">
        <a class="navbar-brand text-primary fw-bold"
           href="${root}/"> <img src="${root}/img/logo.png" alt=""
                                 style="width: 5vw"/>
        </a>
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-lg-0">
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="${root}/attraction/"
                                        style="font-size: x-large"><i
                        class="fa-solid fa-map-location-dot"></i> 지역별여행지</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="#" style="font-size: x-large"><i
                        class="fa-regular fa-calendar"></i> 나의여행계획</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
                                        href="${root}/place?pgno=1&key=&word=" style="font-size: x-large"><i
                        class="fa-solid fa-camera"></i> 핫플자랑하기</a></li>
                <li class="nav-item"><a class="nav-link" aria-current="page"
<%--                                        href="${root}/board?action=list&pgno=1&key=&word="--%>
                                        href="${root}/notice?pgno=1&key=&word="
                                        style="font-size: x-large"><i
                        class="fa-solid fa-comment-dots"></i>
                    공지사항</a></li>
            </ul>

            <!-- 로그인 전 상태-->
            <!-- 로그인 하면 드롭박스 메뉴 변경 혹은 로그인 인지 -->
            <c:if test="${userDto == null}">
                <ul class="navbar-nav mb-2 me-2 mb-lg-0">
                    <li class="nav-item dropdown"><a
                            class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                            role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa-solid fa-user-group fa-2x"></i>
                    </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal"
                                   data-bs-target="#signModal">회원 가입</a></li>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal"
                                   data-bs-target="#loginModal"> 로그인 </a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
            <c:if test="${userDto != null}">
                <ul class="navbar-nav mb-2 me-2 mb-lg-0">
                    <li class="nav-item dropdown"><a
                            class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                            role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fa-solid fa-user-group fa-2x"></i>
                    </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item"
                                   href="${root}/user/logout" id="logout"> 로그아웃 </a></li>
                            <li><a class="dropdown-item" href="${root}/user/mypage"> 마이페이지 </a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>
<!-- 회원가입 modal start -->
<div class="modal fade" id="signModal" data-bs-backdrop="static"
     data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">회원가입</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form id="form-join" method="post" action="${root}/user/join">
                    <div class="row">
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">이름</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="이름"/>
                        </div>

                        <!-- 아이디 -->
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">아이디</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="id" name="id"
                                   placeholder="아이디"/>

                        </div>
                        <div class="row" id="idcheck-result" style="display: none;">
                            <div class="col-md-3" style=""></div>
                            <div class="col-md-9" id="idcheck-text" style="">아이디는 6자
                                이상 16자 이하 입니다.
                            </div>
                        </div>
                        <!-- 비밀번호 -->
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">비밀번호</label>
                        </div>
                        <div class="col-md-9">
                            <input type="password" class="form-control" id="password"
                                   name="password" placeholder="비밀번호"/>
                        </div>

                        <!-- 비밀번호 확인 -->
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">비밀번호확인</label>
                        </div>
                        <div class="col-md-9">
                            <input type="password" class="form-control" id="passwordCheck"
                                   name="passwordCheck" placeholder="비밀번호확인"/>
                        </div>
                        <!-- 이메일 -->
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">이메일</label>
                        </div>
                        <div class="col-md-3">
                            <input type="text" class="form-control" id="email" name="email"
                                   placeholder="이메일"/>
                        </div>
                        <div class="col-md-5">
                            @
                            <button class="btn bg-light dropdown-toggle" type="button"
                                    id="dropdownMenuButton1" data-bs-toggle="dropdown"
                                    aria-expanded="false">이메일을 선택하세요
                            </button>
                            <input type="hidden" name="email_domain" id="email_domain"/>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                <li><a class="dropdown-item" href="#"
                                       onclick="setEmailDomain('naver.com')">naver.com</a></li>
                                <li><a class="dropdown-item" href="#"
                                       onclick="setEmailDomain('gmail.com')">gmail.com</a></li>
                                <li><a class="dropdown-item" href="#"
                                       onclick="setEmailDomain('kakao.com')">kakao.com</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" id="btn-join"
                                class="btn  btn-warning btn-sm">확인
                        </button>
                        <button type="button" class="btn btn-outline-danger btn-sm"
                                data-bs-dismiss="modal">취소
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 로그인 modal start -->
<div class="modal fade" id="loginModal" data-bs-backdrop="static"
     data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">로그인</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form id="form-login" action="${root }/user/login" method="post">
                    <div class="row">
                        <!-- 아이디 -->
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">아이디</label>
                        </div>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="id"
                                   placeholder="아이디"/>
                        </div>

                        <!-- 비밀번호 -->
                        <div class="col-md-3" style="padding: 5px; text-align: center">
                            <label for="name" class="form-label text-center">비밀번호</label>
                        </div>
                        <div class="col-md-9">
                            <input type="password" class="form-control"
                                   name="pw" placeholder="비밀번호"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="btn-login"
                        class="btn  btn-warning btn-sm">로그인
                </button>
                <button type="button" class="btn btn-outline-danger btn-sm"
                        data-bs-dismiss="modal">취소
                </button>
            </div>
        </div>
    </div>
</div>

<script>
  let isUseId = false;
  document.querySelector("#id").addEventListener("keyup", function () {
    let userid = this.value;
    console.log(userid);
    let resultDiv = document.querySelector("#idcheck-result");
    if (userid.length < 6 || userid.length > 16) {
      resultDiv.setAttribute("style", "display:flex;");
      isUseId = false;
    } else {
      fetch("${root}/user/check/" + userid)
      .then(response => response.text())
      .then(data => {
        console.log(data);
        if (data == 0) {
          document.getElementById("idcheck-text").textContent = userid + "는 사용할 수 있습니다.";
          document.getElementById("idcheck-text").setAttribute("class", "col-md-9 text-primary");
          isUseId = true;
        } else {
          document.getElementById("idcheck-text").textContent = userid + "는 사용할 수 없습니다.";
          document.getElementById("idcheck-text").setAttribute("class", "col-md-9 text-danger");
          isUseId = false;
        }
      });
    }
  });

  document.querySelector("#btn-login").addEventListener("click", function () {
    let form = document.querySelector("#form-login");
    form.submit();
  })

  document.querySelector("#btn-join").addEventListener("click", function () {
    if (!document.querySelector("#name").value) {
      alert("이름 입력!!");
      return;
    } else if (!document.querySelector("#id").value) {
      alert("아이디 입력!!");
      return;
    } else if (!document.querySelector("#password").value) {
      alert("비밀번호 입력!!");
      return;
    } else if (document.querySelector("#password").value != document.querySelector(
        "#passwordCheck").value) {
      alert("비밀번호 확인!!");
      return;
    } else {
      let form = document.querySelector("#form-join");
      form.submit();
    }
  });
</script>
</body>
</html>