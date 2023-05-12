<template>
  <div>
    <div class="container" style="margin-top: 15vh">
      <!-- 제목 컨테이너 -->
      <div class="container-fluid" style="margin-bottom: 10px">
        <h2 style="text-align: center">공지작성</h2>
      </div>
      <div>
        <h2
          style="
            text-align: center;
            background-color: #d1ecf1;
            border-radius: 5px;
            padding: 15px;
          "
        >
          글수정
        </h2>
      </div>
      <form id="form-modify" method="POST" action="">
        <div style="margin-bottom: 10px">
          <label for="subject">제목:</label>
          <input
            type="text"
            class="form-control"
            id="subject"
            name="noticeTitle"
            style="margin-right: 5px"
            v-model="notice.noticeTitle"
          />
        </div>
        <div>
          <label for="content">내용:</label>
          <textarea
            class="form-control"
            id="content"
            name="noticeContent"
            placeholder="내용입력..."
            style="height: 254px"
            v-model="notice.noticeContent"
          ></textarea>
        </div>
        <div style="display: flex; flex-direction: row; padding-top: 10px">
          <button
            type="button"
            class="btn btn-primary"
            id="btn-modify"
            style="margin-right: 10px"
            @click="modify"
          >
            글수정
          </button>
          <button type="button" id="btn-list" class="btn btn-danger">
            목록
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardModify",
  data() {
    return {
      notice: {
        noticeNo: 0,
        noticeTitle: "",
        noticeContent: "",
      },
    };
  },
  methods: {
    modify() {
      axios({
        method: "put",
        url: "http://localhost:8080/enjoytrip/notice/api/modify",
        data: this.notice,
      }).then(({ data }) => {
        if (data.result) {
          this.$router.push("/board/list");
        } else {
          alert(data.msg);
        }
      });
    },
  },
  created() {
    this.notice.noticeNo = this.$route.params.no;
  },
};
</script>

<style></style>
