<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<%
    String root = request.getContextPath();
    String msg = (String) request.getAttribute("msg");

    boolean flag = false;
    if (msg == null) {
        flag = true;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Project</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"/>
    <link rel="stylesheet" href="css/main.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
          rel="stylesheet"/>
    <style>
      body {
        font-family: "Jua", sans-serif;
      }
    </style>
    <script>
      function alertMessage(msg) {
        if (msg != "null") {
          alert(msg);
        }
      }

      window.onload = alertMessage("<%=msg%>");

      function setEmailDomain(domain) {
        document.querySelector("#email_domain").setAttribute("value", domain);
        document.querySelector("#dropdownMenuButton1").innerText = domain;
      }
    </script>
</head>

<body>
<jsp:include page="common/header.jsp"></jsp:include>

<div id="carouselExampleCaptions" class="carousel slide"
     data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="0" class="active" aria-current="true"
                aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${root}/img/new7.jpg" class="d-block w-100" alt="..."
                 style="height: 600px"/>
            <div class="carousel-caption d-none d-md-block">
                <p style="margin-bottom: 10rem; font-size: 3rem">
                    지금 <span style="color: #FFB84C">Enjoy!!!Trip </span>과 함께 우리지역의
                    관광지를 알아보고 나만의 여행 계획을 만들어보세요!!!
                </p>
                <p style="font-size: 1.5rem;">신나는 여행 계획을 세우고 공유해 보세요.</p>
                <button type="button" class="btn btn-warning">일정 더보기</button>
            </div>
        </div>
        <div class="carousel-item">
            <img src="${root}/img/new8.jpg" class="d-block w-100" alt="..."
                 style="height: 600px"/>
            <div class="carousel-caption d-none d-md-block">
                <p style="margin-bottom: 10rem; font-size: 3rem">
                    지금 <span style="color: #ffdf64">Enjoy!!!Trip </span>과 함께 우리지역의
                    관광지를 알아보고 나만의 여행 계획을 만들어보세요!!!
                </p>
                <p style="font-size: 1.5rem">신나는 여행 계획을 세우고 공유해 보세요.</p>
                <button type="button" class="btn btn-warning">일정 더보기</button>
            </div>
        </div>
        <div class="carousel-item">
            <img src="${root}/img/top2.png" class="d-block 2-100" alt="..."
                 style="height: 600px; width: 100%"/>
            <div class="carousel-caption d-none d-md-block">
                <p style="margin-bottom: 10rem; font-size: 3rem">
                    지금 <span style="color: #ffdf64">Enjoy!!!Trip </span>과 함께 우리지역의
                    관광지를 알아보고 나만의 여행 계획을 만들어보세요!!!
                </p>
                <p style="font-size: 1.5rem">신나는 여행 계획을 세우고 공유해 보세요.</p>
                <button type="button" class="btn btn-warning">일정 더보기</button>
            </div>
        </div>
    </div>
</div>
<!-- 중간 content 밑에 지역사랑, 이미지 6개 나열 시작 -->

<!-- 중앙 content start -->
<div class="m-3 container-fluid">
    <div class="row" style="margin-top: 20px; height: 100%;">
        <!-- 중앙 left content  start -->
        <div class="col-md-3"
             style="padding-right: 0px; display: flex; flex-direction: column; justify-content: center;">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4"
                     style="height: 1vh; background-color: #ffdf64; margin-bottom: 50px;"></div>
                <div class="col-4"></div>
            </div>
            <div>
                <h4>지역사랑!</h4>
                <h1 style="color: #FFB84C">우리지역 관광지</h1>
                <div class="pl-3 fw-bold">우리지역의 숨어있는 아름다운 관광지를 알려드립니다.</div>
                <div class="pl-3 fw-bold">관광지 주변의 맛집, 숙박업소와 여행코스, 지역 축제등을
                    보실수도 있습니다.
                </div>
                <button type="button" class="btn btn-outline-warning mt-3">더보기...</button>
            </div>
        </div>
        <!-- 중앙 right content start -->
        <div class="col-md-9">
            <!-- 지역 사진 6 start -->
            <div class="m-1 hotplaceContent"
                 style="display: flex; justify-content: center;">
                <div class="card m-3 ourplace" style="width: 33%; height: 33vh">
                    <!-- 지도 이미지  -->
                    <img src="${root}/img/img10.jpeg" class="card-img-top h-100 w-100 "
                         alt="x"/> <a href="#" class="btn btn-sm textbox"
                                      style="color: darkorange; position: absolute; top: 50%; left: 50%;">제목1</a>
                </div>
                <div class="card m-3 ourplace" style="width: 33%; height: 33vh">
                    <!-- 지도 이미지  -->
                    <img src="${root}/img/img11.jpg" class="card-img-top h-100 w-100 " alt="x"/>
                    <a href="#" class="btn btn-sm textbox"
                       style="color: darkorange; position: absolute; top: 50%; left: 50%;">제목2</a>
                </div>
                <div class="card m-3 ourplace" style="width: 33%; height: 33vh">
                    <!-- 지도 이미지  -->
                    <img src="${root}/img/img2.jpg" class="card-img-top h-100 w-100 " alt="x"/>
                    <a href="#" class="btn btn-sm textbox"
                       style="color: darkorange; position: absolute; top: 50%; left: 50%;">제목3</a>
                </div>
            </div>

            <div class="m-1 hotplaceContent"
                 style="display: flex; justify-content: center;">
                <div class="card m-3 ourplace " style="width: 33%; height: 33vh">
                    <!-- 지도 이미지  -->
                    <img src="${root}/img/img1.jpg" class="card-img-top h-100 w-100 " alt="x"/>
                    <a href="#" class="btn btn-sm textbox"
                       style="color: darkorange; position: absolute; top: 50%; left: 50%;">제목4</a>
                </div>
                <div class="card m-3 ourplace" style="width: 33%; height: 33vh">
                    <!-- 지도 이미지  -->
                    <img src="${root}/img/img3.jpg" class="card-img-top h-100 w-100 " alt="x"/>
                    <a href="#" class="btn btn-sm textbox"
                       style="color: darkorange; position: absolute; top: 50%; left: 50%;">제목5</a>
                </div>
                <div class="card m-3 ourplace" style="width: 33%; height: 33vh">
                    <!-- 지도 이미지  -->
                    <img src="${root}/img/img4.jpg" class="card-img-top h-100 w-100 " alt="x"/>
                    <a href="#" class="btn btn-sm textbox"
                       style="color: darkorange; position: absolute; top: 50%; left: 50%;">제목6</a>
                </div>
            </div>
            <!-- 지역 사진 6 end -->

        </div>
    </div>
</div>
<!-- 중앙 content end -->

<div class="m-3 container-fluid"
     style="display: flex; justify-content: center; flex-direction: column">
    <div class="tripPlan" style="text-align: center">
        <div class="row">
            <div class="col-4"></div>
            <div class="col-4"
                 style="height: 1vh; background-color: #ffdf64; margin-bottom: 50px;"></div>
            <div class="col-4"></div>
        </div>
        <h1 class="display-5" style="color: #FFB84C">나만의 여행계획!!</h1>
        <h5>여행 경로, 숙박, 관광지, 예산금액 등 나만의 멋진 계획을 세워 공유해 주세요!</h5>
    </div>
    <div class="m-3 tripPlanContent"
         style="display: flex; justify-content: center">
        <div class="card m-3" style="width: 25%;">
            <!-- 지도 이미지  -->
            <img src="${root}/img/img5.jpeg" class="card-img-top" alt="..."
                 style="height: 50%;">
            <div class="card-body">
                <h3 class="card-title">
                    제목 1
                </h3>
                <p class="card-text">내용 1</p>
                <a href="#" class="btn btn-outline-warning fw-bold"><i
                        class="fa-solid fa-magnifying-glass"></i> 자세히보기 </a>
            </div>
        </div>
        <div class="card m-3" style="width: 25%;">
            <!-- 지도 이미지  -->
            <img src="${root}/img/img6.jpeg" class="card-img-top" alt="..."
                 style="height: 50%;">
            <div class="card-body">
                <h3 class="card-title">
                    제목2
                </h3>
                <p class="card-text">내용2</p>
                <a href="#" class="btn btn-outline-warning fw-bold"><i
                        class="fa-solid fa-magnifying-glass"></i> 자세히보기 </a>
            </div>
        </div>
        <div class="card m-3" style="width: 25%;">
            <!-- 지도 이미지  -->
            <img src="${root}/img/img7.jpg" class="card-img-top" alt="..."
                 style="height: 50%;">
            <div class="card-body">
                <h3 class="card-title">
                    제목3
                    </h5>
                    <p class="card-text">내용3</p>
                    <a href="#" class="btn btn-outline-warning fw-bold"><i
                            class="fa-solid fa-magnifying-glass"></i> 자세히보기</a>
            </div>
        </div>
        <div class="card m-3" style="width: 25%;">
            <!-- 지도 이미지  -->
            <img src="${root}/img/img8.jpg" class="card-img-top" alt="..."
                 style="height: 50%;">
            <div class="card-body">
                <h3 class="card-title">
                    제목4
                </h3>
                <p class="card-text">내용4</p>
                <a href="#" class="btn btn-outline-warning fw-bold"><i
                        class="fa-solid fa-magnifying-glass"></i> 자세히보기 </a>
            </div>
        </div>
    </div>
</div>

<div class="hotplace">
    <div align-self="center" style="" class="row">
        <div class="text-center col">
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4"
                     style="height: 1vh; background-color: #ffdf64; margin-bottom: 50px;"></div>
                <div class="col-4"></div>
            </div>
            <h1 class="display-5" style="color: #FFB84C">핫플 자랑하기</h1>
            <p class="lead text-dark font-weight-bold">나만 알고있는 핫플!!! 자랑해
                주세요.</p>
        </div>
    </div>
    <div class="container text-center">
        <div class="row " style="height: 20vh">
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img2.jpg');">
                <span class="textbox">핫플1</span>
            </div>
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img3.jpg');">
                <span class="textbox">핫플2</span>
            </div>
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img6.jpeg');">
                <span class="textbox">핫플3</span>
            </div>
        </div>
        <div class="row " style="height: 20vh">
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img5.jpeg');">
                <span class="textbox">핫플4</span>
            </div>
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img8.jpg');">
                <span class="textbox">핫플5</span>
            </div>
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img9.jpg');">
                <span class="textbox">핫플6</span>
            </div>
        </div>
        <div class="row " style="height: 20vh">
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img10.jpeg');">
                <span class="textbox">핫플7</span>
            </div>
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img11.jpg');">
                <span class="textbox">핫플8</span>
            </div>
            <div class="col m-2 hotple"
                 style="background-image: url('${root}/img/img1.jpg');">
                <span class="textbox">핫플9</span>
            </div>
        </div>
    </div>
</div>

<div class="carousel-inner mt-5">
    <div class="carousel-item active">
        <img src="${root}/img/top5.png" class="d-block w-100" alt="콘텐츠 이미지"
             style="height: 400px"/>
        <div class="carousel-caption d-md-block">
            <div style="margin: 0.5rem; font-size: 2rem;">언제든 연락주세요!</div>
            <div style="font-size: 4rem; color: white">
                <span style="color: #FFB84C">Enjoy!!! Trip</span>은 여러분의 많은 참여를<br>
                기다립니다.
            </div>
            <div style="font-size: 1.5rem">숨어있는 지역 명소와 함께 즐기고 싶은 여행일정이 있다면
                언제든지 환영입니다.
            </div>
            <div style="font-size: 2.5rem; color: #FFB84C;">02-1234-5678</div>
        </div>
    </div>
</div>
<!-- footer -->

<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>
