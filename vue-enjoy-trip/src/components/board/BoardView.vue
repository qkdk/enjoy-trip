<template>
<div>
    <div class="container-fluid p-5" style="margin-top: 15vh">
    <!-- 제목 컨테이너 -->
    <div class="container-fluid">
        <h2 style="text-align: center">공지사항</h2>
    </div>
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
            <h2 class="my-3 py-3 shadow-sm bg-light text-center">
                제목 : {{notice.noticeTitle}}
            </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
            <div class="row my-2">
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="clearfix align-content-center">
                        <img class="avatar me-2 float-md-start bg-light p-2"
                             src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"/>
                        <p>
                            <span class="fw-bold">작성자 : {{notice.userId}}</span> <br/>
                            <span class="text-secondary fw-light"> 작성일 : {{notice.noticeCreateTime}}
								</span>
                            <span class="text-secondary fw-light">
									조회 : {{notice.noticeHit}}
								</span>
                        </p>
                    </div>
                </div>
                <div class="divider mb-3"></div>
                <div class="text-secondary">
                    {{notice.noticeContent}}
                </div>
                <div class="divider mt-3 mb-3"></div>
                <div class="d-flex justify-content-end">
                    <button type="button" id="btn-list-return"
                            class="btn btn-outline-primary mb-3">글목록
                    </button>
                    <c:if test="${notice.userId == userDto.userId}">
                        <button type="button" id="btn-mv-modify"
                                class="btn btn-outline-success mb-3 ms-1" @click="mvModify">글수정
                        </button>
                        <button type="button" id="btn-delete"
                                class="btn btn-outline-danger mb-3 ms-1" @click="deleteBoard">글삭제
                        </button>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
</template>

<script>
import axios from "axios"
export default {
    name: 'BoardView',
    data() {
        return {
            notice: {},
            no : '',
        }
    },
    methods: {
        mvModify(){
            this.$router.push('/board/modify/'+this.notice.noticeNo);
        },
        deleteBoard(){
            // let no = this.$route.params.no;
            // const promise = axios.delete(`http://localhost:8080/enjoytrip/notice/api/delete?noticeNo=${no}`)
            // promise
            //     .then((Response)=>{
            //         console.log(Response);
            //         this.$router.push('/board');
            //     })
            var myHeaders = new Headers();
                myHeaders.append("Content-Type", "application/json");

                var requestOptions = {
                    method: 'DELETE',
                    headers: myHeaders,
                    redirect: 'follow'
                };
                console.log(this.no);
                fetch("http://localhost:8080/enjoytrip/notice/api/delete?noticeNo="+this.no, requestOptions)
                    .then(response => response.text())
                    .then(result => console.log(result))
                    .catch(error => console.log('error', error));
                this.$router.push('/board');
                window.location.reload();            
        }
    },
    created() {
        this.no = this.$route.params.no;
        var url = `http://localhost:8080/enjoytrip/notice/api/${this.no}?noticeNo=${this.no}`;
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