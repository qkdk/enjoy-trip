<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top pe-3">
      <div class="container">
        <router-link class="navbar-brand text-primary fw-bold" to="/">
          <img src="@/assets/img/logo.png" style="width: 5vw" />
        </router-link>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-lg-0">
            <li class="nav-item">
              <router-link
                class="nav-link"
                aria-current="page"
                style="font-size: x-large"
                to="/attraction"
              >
                <i class="fa-solid fa-map-location-dot"></i>
                지역별여행지
              </router-link>
            </li>
            <li class="nav-item">
              <router-link
                class="nav-link"
                aria-current="page"
                to="/plan"
                style="font-size: x-large"
                ><i class="fa-solid fa-camera"></i> 나의여행계획</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                class="nav-link"
                aria-current="page"
                to="/hotplace"
                style="font-size: x-large"
                ><i class="fa-solid fa-camera"></i> 핫플자랑하기</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                class="nav-link"
                aria-current="page"
                to="/notice"
                style="font-size: x-large"
                ><i class="fa-solid fa-camera"></i> 공지사항</router-link
              >
            </li>
          </ul>

          <!-- 로그인 전 상태-->
          <!-- 로그인 하면 드롭박스 메뉴 변경 혹은 로그인 인지 -->
          <c:if test="${userDto == null}">
            <ul class="navbar-nav mb-2 me-2 mb-lg-0">
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <i class="fa-solid fa-user-group fa-2x"></i>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li>
                    <a
                      class="dropdown-item"
                      href="#"
                      data-bs-toggle="modal"
                      data-bs-target="#signModal"
                      >회원 가입</a
                    >
                  </li>
                  <li>
                    <a
                      class="dropdown-item"
                      href="#"
                      data-bs-toggle="modal"
                      data-bs-target="#loginModal"
                    >
                      로그인
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </c:if>
          <!-- 로그인 후 -->
          <c:if test="${userDto != null}">
            <ul class="navbar-nav mb-2 me-2 mb-lg-0">
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  id="navbarDropdown"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <i class="fa-solid fa-user-group fa-2x"></i>
                </a>
                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                  <li>
                    <a class="dropdown-item" href="${root}/user/logout" id="logout"> 로그아웃 </a>
                  </li>
                  <li><a class="dropdown-item" href="${root}/user/mypage"> 마이페이지 </a></li>
                </ul>
              </li>
            </ul>
          </c:if>
        </div>
      </div>
    </nav>

    <!-- 회원가입 modal start -->
    <div
      class="modal fade"
      id="signModal"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      tabindex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">회원가입</h4>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <form id="form-join" method="post">
              <div class="row">
                <div class="col-md-3" style="padding: 5px; text-align: center">
                  <label for="name" class="form-label text-center">이름</label>
                </div>
                <div class="col-md-9">
                  <input
                    type="text"
                    class="form-control"
                    id="name"
                    name="name"
                    placeholder="이름"
                    v-model="joinDto.userName"
                  />
                </div>

                <!-- 아이디 -->
                <div class="col-md-3" style="padding: 5px; text-align: center">
                  <label for="name" class="form-label text-center">아이디</label>
                </div>
                <div class="col-md-9">
                  <input
                    type="text"
                    class="form-control"
                    id="id"
                    name="id"
                    placeholder="아이디"
                    v-model="joinDto.userId"
                  />
                </div>
                <div class="row" id="idcheck-result" style="display: none">
                  <div class="col-md-3" style=""></div>
                  <div class="col-md-9" id="idcheck-text" style="">
                    아이디는 6자 이상 16자 이하 입니다.
                  </div>
                </div>
                <!-- 비밀번호 -->
                <div class="col-md-3" style="padding: 5px; text-align: center">
                  <label for="name" class="form-label text-center">비밀번호</label>
                </div>
                <div class="col-md-9">
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    placeholder="비밀번호"
                    v-model="joinDto.userPw"
                  />
                </div>

                <!-- 비밀번호 확인 -->
                <div class="col-md-3" style="padding: 5px; text-align: center">
                  <label for="name" class="form-label text-center">비밀번호확인</label>
                </div>
                <div class="col-md-9">
                  <input
                    type="password"
                    class="form-control"
                    id="passwordCheck"
                    name="passwordCheck"
                    placeholder="비밀번호확인"
                    v-model="joinDto.userPwCheck"
                  />
                </div>
                <!-- 이메일 -->
                <div class="col-md-3" style="padding: 5px; text-align: center">
                  <label for="name" class="form-label text-center">이메일</label>
                </div>
                <div class="col-md-3">
                  <input
                    type="text"
                    class="form-control"
                    id="email"
                    name="email"
                    placeholder="이메일"
                    v-model="joinDto.userEmail"
                  />
                </div>
                <div class="col-md-5">
                  @
                  <button
                    class="btn bg-light dropdown-toggle"
                    type="button"
                    id="dropdownMenuButton1"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    {{ emailSelected }}
                  </button>
                  <input type="hidden" name="email_domain" id="email_domain" />
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li>
                      <a class="dropdown-item" href="#" @click="setEmailDomain('naver.com')"
                        >naver.com</a
                      >
                    </li>
                    <li>
                      <a class="dropdown-item" href="#" @click="setEmailDomain('gmail.com')"
                        >gmail.com</a
                      >
                    </li>
                    <li>
                      <a class="dropdown-item" href="#" @click="setEmailDomain('kakao.com')"
                        >kakao.com</a
                      >
                    </li>
                  </ul>
                </div>
              </div>
              <!-- Modal footer -->
              <div class="modal-footer">
                <button type="button" id="btn-join" class="btn btn-warning btn-sm">확인</button>
                <button type="button" class="btn btn-outline-danger btn-sm" data-bs-dismiss="modal">
                  취소
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- 로그인 modal start -->
    <div
      class="modal fade"
      id="loginModal"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      tabindex="-1"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
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
                  <input type="text" class="form-control" name="id" placeholder="아이디" />
                </div>

                <!-- 비밀번호 -->
                <div class="col-md-3" style="padding: 5px; text-align: center">
                  <label for="name" class="form-label text-center">비밀번호</label>
                </div>
                <div class="col-md-9">
                  <input type="password" class="form-control" name="pw" placeholder="비밀번호" />
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="btn-login" class="btn btn-warning btn-sm">로그인</button>
            <button type="button" class="btn btn-outline-danger btn-sm" data-bs-dismiss="modal">
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "TripHeader",
  data() {
    return {
      emailSelected: "이메일을 선택하세요",
      joinDto: {
        userId: "",
        userPw: "",
        userPwCheck: "",
        userName: "",
        userEmail: "",
        userDomain: "",
        userRole: "",
      },
    };
  },
  methods: {
    // 도메인 선택
    setEmailDomain(domain) {
      this.joinDto.userDomain = domain;
      this.emailSelected = domain;
    },
    // 회원가입
    join() {
      delete this.joinDto.userPwCheck;
      axios({
        methods: "post",
        url: "http://localhost:8080/enjoytrip/user/api/join",
        data: this.joinDto,
      });
    },
    // 로그인
  },
};
</script>
