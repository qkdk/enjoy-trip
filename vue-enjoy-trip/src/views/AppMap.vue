<template>
<div>



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
</div>
</template>

<script>
export default {
    name: "KakaoMap",
    data(){
        return{
            map: null
        }
    },
    mounted(){
        if(window.kakao && window.kakao.maps){
            console.log(1)
            this.loadMap();
        }else{
            console.log(2)
            this.loadScript();
        }
    },
    methods : {
        
        loadScript(){
            const script = document.createElement("script");
            script.src = 
            "//dapi.kakao.com/v2/maps/sdk.js?appkey=b7c6907abe12849970bf0d5a19f6717e&autoload=false";
            script.onload = () => window.kakao.maps.load(this.loadMap);

            document.head.appendChild(script);
        },

        loadMap(){
            const container = document.getElementById("map");
            const options = {
                center: new window.kakao.maps.LatLng(33.450701, 126.570667),
                level: 3
            };
            this.map = new window.kakao.maps.Map(container, options);
            this.loadMaker();
        },

        loadMaker(){
            const markerPosition = new window.kakao.maps.LatLng(
                33.450701,
                126.570667
            );
            const marker = new window.kakao.maps.Marker({
                position: markerPosition,
            });

            marker.setMap(this.map);
        }
    },
}
</script>

<style>

</style>