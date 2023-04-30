const govKey = `Kd4XIGo0UoYCRIi5tQmamAuqcbG%2FxATImc%2BsqeI%2BemwGFGVXiv8jSh6MP7kI8iVdifjWEmox%2BazGPdBA9grZ5Q%3D%3D`;

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

// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
function showMarkers() {
    setMarkers(map);
}

// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
    setMarkers(null);
}

let city_code = 1;
let district_code = 0;
let content_code = 0;

window.onload = () => {
    const city_sel = document.querySelector("#city_sel");
    fetch(
        `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${govKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`
    )
        .then((response) => response.json())
        .then((data) => {
            data.response.body.items.item.forEach((item) => {
                const str = `<option value=${item.code}>${item.name}</option>`;
                city_sel.innerHTML += str;
            });
        });
};

document.querySelector("#city_sel").addEventListener("change", () => {
    const district_sel = document.querySelector("#district_sel");
    district_sel.innerHTML = `<option value=0>전체</option>`;
    const code =
        document.querySelector("#city_sel").options[document.querySelector("#city_sel").selectedIndex]
            .value;

    city_code = code;
    fetch(
        `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${govKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${code}&_type=json`
    )
        .then((response) => response.json())
        .then((data) => {
            data.response.body.items.item.forEach((item) => {
                const str = `<option value=${item.code}>${item.name}</option>`;
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

function makeaa(positions) {
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
        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
    }
}

function updateMap() {
    let url;
    url = makeUrl(url);

    var positions = [];

    setMarkers(null);
    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            (mapContainer = document.getElementById("map")), // 지도를 표시할 div
                (mapOption = {
                    center: new kakao.maps.LatLng(
                        data.response.body.items.item[0].mapy,
                        data.response.body.items.item[0].mapx
                    ), // 지도의 중심좌표
                    level: 8, // 지도의 확대 레벨
                });
            map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

            data.response.body.items.item.forEach((element) => {
                positions.push(makeMark(element));
            });
        }).then(() => makeaa(positions));
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

function makeUrl(url) {
    if (district_code == 0 && content_code == 0) {
        url = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${govKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=${city_code}`;
    } else if (content_code == 0) {
        url = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${govKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=${city_code}&sigunguCode=${district_code}`;
    } else if (district_code == 0) {
        url = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${govKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=${content_code}&areaCode=${city_code}`;
    } else {
        url = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${govKey}&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=${content_code}&areaCode=${city_code}&sigunguCode=${district_code}`;
    }
    return url;
}

function makeMark(element) {
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(24, 24), // 마커이미지의 크기입니다
        imageOption = {}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    if (element.contenttypeid == 12) {
        imageSrc = "img/marker/marker_photo.png";
    } else if (element.contenttypeid == 14) {
        imageSrc = "img/marker/marker_his.png";
    } else if (element.contenttypeid == 15) {
        imageSrc = "img/marker/marker_party.png";
    } else if (element.contenttypeid == 25) {
        imageSrc = "img/marker/marker_trip.png";
    } else if (element.contenttypeid == 28) {
        imageSrc = "img/marker/marker_sports.png";
    } else if (element.contenttypeid == 32) {
        imageSrc = "img/marker/marker_bed.png";
    } else if (element.contenttypeid == 38) {
        imageSrc = "img/marker/marker_shop.png";
    } else if (element.contenttypeid == 39) {
        imageSrc = "img/marker/marker_food.png";
    }
    const coordX = element.mapx;
    const coordY = element.mapy;
    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(coordY, coordX), // 마커가 표시될 위치입니다
        content = `<div style="height: 100px;width: 300px ">
                    <div style="background-color: lightgrey">${element['title']}</div>
                        <div style="display: flex; flex-direction: row">
                            <img style="width: 75px; height: 75px" src=${element.firstimage}></img>
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
