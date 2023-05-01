<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div id="footer" class="section-content p-4 mt-3 container-fluid"
		style="background-color: #f9f8f8">
		<div class="row">
			<div class="mb-4 text-center col-2">
				<img src="${root}/img/ssafy-logo-black.b301994e.png" alt="SSAFY"
					class="img-ssafy-logo" style="width: 100px" />
			</div>
			<div class="mb-4 ml-auto text-right col-10">
				<div class="row">
					<div
						style="display: flex; justify-content: end; align-items: center">
						<a href="https://www.ssafy.com/" title="SSAFY"><img
							src="${root}/img/ssafy-logo-small.bd603755.png" alt="SSAFY"
							class="img-link" style="width: 100px" /></a><a
							href="http://api.visitkorea.or.kr/" title="TourAPI 4.0"><img
							src="${root}/img/tour-api-logo.ebcb1140.png" alt="TourAPI 4.0"
							class="img-link" style="width: 100px" /></a><a
							href="http://kto.visitkorea.or.kr/kor.kto" title="한국관광공사"><img
							src="${root}/img/visit-korea-logo.858325ef.png" alt="한국관광공사"
							class="img-link" style="width: 100px" /></a><a
							href="http://www.mcst.go.kr/" title="문화체육관광부"><img
							src="${root}/img/mcst-logo.86700cdd.png" alt="문화체육관광부" class="img-link"
							style="width: 100px" /></a>
					</div>
				</div>
				<div class="row mt-3">
					<div class="text-center company-info col">
						<i class="fas fa-user"></i>대표 : Hi SSAM <i
							class="fas fa-map-marked-alt"></i>주소: 212, Teheran-ro,
						Gangnam-gu, Seoul, Republic of Korea <br /> <i
							class="fas fa-phone-square-al"></i>대표번호: 1588-3357 <i
							class="fas fa-hospital"></i>사업자번호 : 123-4567-987
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://kit.fontawesome.com/64df31442a.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous">
	</script>
</body>
</html>