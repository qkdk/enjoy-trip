<template>
  <div>
    <div style="padding-top: 70px">
      <h2 style="text-align: center; margin: 3%">지역별 관광정보</h2>
    </div>
    <div
      class="container"
      style="display: flex; flex-direction: row; margin-bottom: 10px"
    >
      <select
        class="form-select"
        aria-label="Default select example"
        style="margin-right: 10px"
        id="city_sel"
        @change="sidoChangeListener"
      ></select>
      <select
        class="form-select"
        aria-label="Default select example"
        style="margin-right: 10px"
        id="district_sel"
        @change="gugunChangeListener"
      >
        <option value="0">구군</option>
      </select>
      <select
        class="form-select"
        aria-label="Default select example"
        id="content_sel"
        @change="contentChangeListener"
      >
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
  </div>
</template>

<script>
import axios from "axios";

const baseUrl = `http://localhost:8080/enjoytrip`;

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      mapContainer: null,
      markers: [],
      city_code: 1,
      district_code: 0,
      content_code: 0,
    };
  },
  mounted() {
    this.getCityCode();
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      this.initScript();
    }
  },

  methods: {
    initScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=b7c6907abe12849970bf0d5a19f6717e&autoload=false";
      script.onload = () => window.kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    },

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.mapContainer = document.getElementById("map");
      this.loadMaker();
    },

    loadMaker() {
      const markerPosition = new window.kakao.maps.LatLng(
        33.450701,
        126.570667
      );
      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },

    getCityCode() {
      const city_sel = document.querySelector("#city_sel");
      axios({
        methods: "get",
        url: `${baseUrl}/attraction/getcode`,
      }).then(({ data }) =>
        data.forEach(
          (item) =>
            (city_sel.innerHTML += `<option value=${item.sidoCode}>${item.sidoName}</option>`)
        )
      );
    },

    sidoChangeListener() {
      {
        const district_sel = document.querySelector("#district_sel");
        district_sel.innerHTML = `<option value=0>전체</option>`;
        this.city_code =
          document.querySelector("#city_sel").options[
            document.querySelector("#city_sel").selectedIndex
          ].value;
        axios({
          methods: `get`,
          url: `${baseUrl}/attraction/getcode/${this.city_code}`,
        }).then(({ data }) =>
          data.forEach(
            (item) =>
              (district_sel.innerHTML += `<option value=${item.gugunCode}>${item.gugunName}</option>`)
          )
        );
        this.updateMap();
      }
    },
    gugunChangeListener() {
      this.district_code =
        document.querySelector("#district_sel").options[
          document.querySelector("#district_sel").selectedIndex
        ].value;
      this.updateMap();
    },
    contentChangeListener() {
      this.content_code =
        document.querySelector("#content_sel").options[
          document.querySelector("#content_sel").selectedIndex
        ].value;
      this.updateMap();
    },
    updateMap() {
      const url = `${baseUrl}/attraction/${this.city_code}/${this.district_code}/${this.content_code}`;
      var positions = [];

      this.setMarkers(null);
      axios({ methods: "get", url: `${url}` })
        .then(({ data }) => {
          this.mapContainer = document.getElementById("map"); // 지도를 표시할 div
          this.mapOption = {
            center: new window.kakao.maps.LatLng(
              data[0].latitude,
              data[0].longitude
            ), // 지도의 중심좌표
            level: 9, // 지도의 확대 레벨
          };
          this.map = new window.kakao.maps.Map(
            this.mapContainer,
            this.mapOption
          ); // 지도를 생성합니다

          data.forEach((element) => {
            positions.push(this.makeMark(element));
          });
        })
        .then(() => this.showMark(positions));
    },
    setMarkers(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
      }
    },
    makeMark(element) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", // 마커이미지의 주소입니다
        imageSize = new window.kakao.maps.Size(24, 24), // 마커이미지의 크기입니다
        imageOption = {}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      if (element.contentTypeId == 12) {
        imageSrc = `${baseUrl}/img/marker/marker_photo.png`;
      } else if (element.contentTypeId == 14) {
        imageSrc = `${baseUrl}/img/marker/marker_his.png`;
      } else if (element.contentTypeId == 15) {
        imageSrc = `${baseUrl}/img/marker/marker_party.png`;
      } else if (element.contentTypeId == 25) {
        imageSrc = `${baseUrl}/img/marker/marker_trip.png`;
      } else if (element.contentTypeId == 28) {
        imageSrc = `${baseUrl}/img/marker/marker_sports.png`;
      } else if (element.contentTypeId == 32) {
        imageSrc = `${baseUrl}/img/marker/marker_bed.png`;
      } else if (element.contentTypeId == 38) {
        imageSrc = `${baseUrl}/img/marker/marker_shop.png`;
      } else if (element.contentTypeId == 39) {
        imageSrc = `${baseUrl}/img/marker/marker_food.png`;
      }
      const coordX = element.latitude;
      const coordY = element.longitude;
      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new window.kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        ),
        markerPosition = new window.kakao.maps.LatLng(coordX, coordY), // 마커가 표시될 위치입니다
        content = `<div style="height: 100px;width: 350px ">
                    <div style="background-color: lightgrey">${element.title}</div>
                        <div style="display: flex; flex-direction: row">
                            <img style="width: 75px; height: 75px" src=${element.firstImage}></img>
                            <div>
                                <span>${element.addr1}</span>
                                <br/>
                                <span>${element.zipCode}</span>
                                <br/>
                                <span>${element.tel}</span>
                            </div>
                        </div>
                    </div>
                </div>`;
      return { markerPosition, markerImage, content };
    },
    showMark(positions) {
      for (var i = 0; i < positions.length; i++) {
        // 마커를 생성합니다
        var marker = new window.kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].markerPosition, // 마커의 위치
          image: positions[i].markerImage,
        });

        // 마커에 표시할 인포윈도우를 생성합니다
        var infowindow = new window.kakao.maps.InfoWindow({
          content: positions[i].content, // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        window.kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow)
        );
        window.kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow)
        );
      }
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
  },
};
</script>

<style></style>
