const govKey = `Kd4XIGo0UoYCRIi5tQmamAuqcbG%2FxATImc%2BsqeI%2BemwGFGVXiv8jSh6MP7kI8iVdifjWEmox%2BazGPdBA9grZ5Q%3D%3D`;

const contextPath = window.location.pathname.split("/")[1];
const protocol = window.location.protocol; // "https:"
const hostname = window.location.hostname; // "www.example.com"
const port = window.location.port;         // "8080"
const baseUrl = protocol + "//" + hostname + ":" + port + "/" + contextPath;

var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(36.3546, 127.2978), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var markers = [];

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

let city_code = 1;
let district_code = 0;
let content_code = 0;

window.onload = () => {
  const city_sel = document.querySelector("#city_sel");
  fetch(
      `${baseUrl}/attraction/getcode`
  )
  .then((response) => response.json())
  .then((data) => {
    data.forEach((item) => {
      const str = `<option value=${item.sidoCode}>${item.sidoName}</option>`;
      city_sel.innerHTML += str;
    });
  });
};

document.querySelector("#city_sel").addEventListener("change", () => {
  const district_sel = document.querySelector("#district_sel");
  district_sel.innerHTML = `<option value=0>전체</option>`;
  const code =
      document.querySelector("#city_sel").options[document.querySelector(
          "#city_sel").selectedIndex]
          .value;

  city_code = code;
  fetch(
      `${baseUrl}/attraction/getcode/${city_code}`
  )
  .then((response) => response.json())
  .then((data) => {
    data.forEach((item) => {
      const str = `<option value=${item.gugunCode}>${item.gugunName}</option>`;
      district_sel.innerHTML += str;
    });
  });
  updateMap();
});

document.querySelector("#district_sel").addEventListener("change", () => {
  district_code =
      document.querySelector("#district_sel").options[
          document.querySelector("#district_sel").selectedIndex
          ].value;
  updateMap();
});

document.querySelector("#content_sel").addEventListener("change", () => {
  content_code =
      document.querySelector("#content_sel").options[
          document.querySelector("#content_sel").selectedIndex
          ].value;
  updateMap();
});

function showMark(positions) {
  for (var i = 0; i < positions.length; i++) {
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].markerPosition, // 마커의 위치
      image: positions[i].markerImage
    });

    // 마커에 표시할 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
      content: positions[i].content // 인포윈도우에 표시할 내용
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(marker, 'mouseover',
        makeOverListener(map, marker, infowindow));
    kakao.maps.event.addListener(marker, 'mouseout',
        makeOutListener(infowindow));
  }
}

function updateMap() {
  const url = `${baseUrl}/attraction/${city_code}/${district_code}/${content_code}`
  var positions = [];

  setMarkers(null);
  fetch(url)
  .then((response) => response.json())
  .then((data) => {
    (mapContainer = document.getElementById("map")), // 지도를 표시할 div
        (mapOption = {
          center: new kakao.maps.LatLng(
              data[0].latitude,
              data[0].longitude
          ), // 지도의 중심좌표
          level: 9, // 지도의 확대 레벨
        });
    map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    data.forEach((element) => {
      positions.push(makeMark(element));
    });
  }).then(() =>
      showMark(positions)
  );
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map, marker, infowindow) {
  return function () {
    infowindow.open(map, marker);
  };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
  return function () {
    infowindow.close();
  };
}

function makeMark(element) {
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", // 마커이미지의 주소입니다
      imageSize = new kakao.maps.Size(24, 24), // 마커이미지의 크기입니다
      imageOption = {}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

  if (element.contentTypeId == 12) {
    imageSrc = `/${contextPath}/img/marker/marker_photo.png`;
  } else if (element.contentTypeId == 14) {
    imageSrc = `/${contextPath}/img/marker/marker_his.png`;
  } else if (element.contentTypeId == 15) {
    imageSrc = `/${contextPath}/img/marker/marker_party.png`;
  } else if (element.contentTypeId == 25) {
    imageSrc = `/${contextPath}/img/marker/marker_trip.png`;
  } else if (element.contentTypeId == 28) {
    imageSrc = `/${contextPath}/img/marker/marker_sports.png`;
  } else if (element.contentTypeId == 32) {
    imageSrc = `/${contextPath}/img/marker/marker_bed.png`;
  } else if (element.contentTypeId == 38) {
    imageSrc = `/${contextPath}/img/marker/marker_shop.png`;
  } else if (element.contentTypeId == 39) {
    imageSrc = `/${contextPath}/img/marker/marker_food.png`;
  }
  const coordX = element.latitude;
  const coordY = element.longitude;
  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
  var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
          imageOption),
      markerPosition = new kakao.maps.LatLng(coordX, coordY), // 마커가 표시될 위치입니다
      content = `<div style="height: 100px;width: 300px ">
                    <div style="background-color: lightgrey">${element.title}</div>
                        <div style="display: flex; flex-direction: row">
                            <img style="width: 75px; height: 75px" src=${element.firstImage}></img>
                            <div>
                                <span>${element.addr1}</span>
                                <br/>
                                <span>${element.zipcode}</span>
                                <br/>
                                <span>${element.tel}</span>
                            </div>
                        </div>
                    </div>
                </div>`;
  return {markerPosition, markerImage, content};
}
