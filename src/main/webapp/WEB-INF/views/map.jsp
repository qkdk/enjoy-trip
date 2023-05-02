<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

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
            crossorigin="anonymous"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"
    />
    <link rel="stylesheet" href="${root}/css/main.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet"/>
    <style>
      body {
        font-family: "Jua", sans-serif;
      }
    </style>
    <script
            type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7c6907abe12849970bf0d5a19f6717e"
    ></script>
</head>
<body>
<!-- 상단 navbar start -->
<jsp:include page="common/header.jsp"></jsp:include>
<!-- 상단 navbar end  -->

<div style="padding-top: 70px">
    <h2 style="text-align: center; margin: 3%">지역별 관광정보</h2>
</div>
<div class="container" style="display: flex; flex-direction: row; margin-bottom: 10px">
    <select
            class="form-select"
            aria-label="Default select example"
            style="margin-right: 10px"
            id="city_sel"
    ></select>
    <select
            class="form-select"
            aria-label="Default select example"
            style="margin-right: 10px"
            id="district_sel"
    >
        <option value="0">구군</option>
    </select>

    <select class="form-select" aria-label="Default select example" id="content_sel">
        <option value="0">분류</option>
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
    </select>
</div>
<div class="container">
    <div id="map" style="width: 100%; height: 700px"></div>
</div>

<jsp:include page="common/footer.jsp"></jsp:include>
<script src="${root}/js/map.js"></script>
</body>
</html>
