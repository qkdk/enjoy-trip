<template>
<div>
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
        <!-- <input type="hidden" name="noticeNo" value="{{notice.noticeNo}}"> -->

        <div style="margin-bottom: 10px">

            <label for="subject">제목:</label> <input type="text"
                                                    class="form-control" id="subject"
                                                    name="noticeTitle"
                                                    style="margin-right: 5px"
                                                    v-model="notice.noticeTitle"/>
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea class="form-control" id="content" name="noticeContent"
                      placeholder="내용입력..." style="height: 254px" v-model="notice.noticeContent"></textarea>
        </div>
        <div style="display: flex; flex-direction: row; padding-top: 10px">
            <button type="button" class="btn btn-primary" id="btn-modify"
                    style="margin-right: 10px" @click="modify">글수정
            </button>
            <button type="button" id="btn-list" class="btn btn-danger">목록</button>
        </div>
    </form>

</div>
</div>
</template>

<script>
import axios from "axios"
export default {
    name: 'BoardModify',
    data() {
        return {
            notice: {},
        }
    },
    methods: {
        modify(){
            // console.log("asdads");
            // var modUrl = `http://localhost:8080/enjoytrip/notice/api/modify?noticeNo=${this.notice.noticeNo}&noticeTitle=${this.notice.noticeTitle}&noticeContent=${this.notice.noticeContent}`;
            // axios({
            //     methods: 'pathch',
            //     url: modUrl,
            //     data:{
            //         noticeNo: this.notice.noticeNo,
            //         noticeTitle: this.notice.noticeTitle,
            //         noticeContent: this.notice.noticeContent
            //     }
            // })
            // const axios = require('axios');

            // let config = {
            //     method: 'patch',
            //     maxBodyLength: Infinity,
            //     url: `http://localhost:8080/enjoytrip/notice/api/modify?noticeNo=${this.notice.noticeNo}&noticeTitle=${this.notice.noticeTitle}&noticeContent=${this.notice.noticeContent}`,
            //     headers: { }
            // };

            // axios.request(config)
            // .then((response) => {
            // console.log(JSON.stringify(response.data));
            // })
            // .catch((error) => {
            // console.log(error);
            // });
            const axios = require('axios');
            let data = JSON.stringify({
            "noticeNo": 1,
            "noticeTitle": "수정",
            "noticeContent": "내용"
            });

            let config = {
            method: 'put',
            maxBodyLength: Infinity,
            url: `http://localhost:8080/enjoytrip/notice/api/modify?noticeNo=${this.notice.noticeNo}&noticeTitle=${this.notice.noticeTitle}&noticeContent=${this.notice.noticeContent}`,
            headers: { 
                'Content-Type': 'application/json'
            },
            data : data
            };

            axios.request(config)
            .then((response) => {
            console.log(JSON.stringify(response.data));
            this.$router.push('/board');
            })
            .catch((error) => {
            console.log(error);
});
            
        },
    },
    created() {
        var no = this.$route.params.no;
        var url = `http://localhost:8080/enjoytrip/notice/api/${no}?noticeNo=${no}`;
        axios.get(url)
            .then(Response=>{
            this.notice = Response.data;
            console.log(this.notice);
        })
    },

}
</script>

<style>

</style>