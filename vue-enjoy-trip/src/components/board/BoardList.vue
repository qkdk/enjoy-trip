<template>
  <div class="about">
    
    <div class="container-fluid p-5" style="margin-top: 15vh">
    <!-- 제목 컨테이너 -->
    <div class="container-fluid">
        <h2 style="text-align: center">공지사항 페이지 입니다.</h2>
    </div>
    <!-- 검색 컨테이너 -->
    <div style="display: flex; justify-content: space-between">
        <div>
            <button type="button" class="btn btn-outline-warning"
                    id="btn-mv-register" @click="mvWrite">글쓰기
            </button>
            <button type="button" class="btn btn-outline-warning" id="btn-sort">조회순
                정렬하기
            </button>
        </div>

        <div style="display: flex; justify-content: end; flex-direction: row">
            <form id="form-search" action="" style="display: flex">
                <input type="hidden" name="action" value="list"/> <input
                    type="hidden" name="pgno" value="1"/> <select class="form-select"
                                                                  name="key" id="key"
                                                                  aria-label="Default select example"
                                                                  style="margin-right: 5px">
                <option selected>검색조건</option>
                <option value="notice_no">글번호</option>
                <option value="user_id">작성자</option>
                <option value="notice_title">제목</option>
                <option value="notice_content">내용</option>
            </select> <input type="text" name="word" id="word" class="form-control"
                             placeholder="검색어..." style="margin-right: 5px"/>
                <intput id="btn-search" type="button"
                        class="btn btn-outline-warning w-50">검색
                </intput>
            </form>
        </div>

    </div>
    <!-- 테이블 컨테이너 -->
    <div>
        <table class="table table-striped table-hover mt-2">
            <thead>
            <tr class="text-center">
                <th scope="col" style="width: 15%">글번호</th>
                <th scope="col" style="width: 40%">제목</th>
                <th scope="col" style="width: 15%">작성자</th>
                <th scope="col" style="width: 15%">조회수</th>
                <th scope="col" style="width: 15%">작성일</th>
            </tr>
            </thead>
            <tbody>


                <tr class="text-center" v-for="notice in noticelist" :key="notice.noticeNo">
                    <td aria-colindex="1" role="cell" class="tdClass">{{notice.noticeNo}}</td>
                    <td aria-colindex="2" role="cell" class="tdSubject"><a
                            href="" class="article-title" @click="mvView(`${notice.noticeNo}`)"
                            > {{notice.noticeTitle}} </a></td>
                    <td aria-colindex="3" role="cell" class="tdClass">{{notice.userId}}</td>
                    <td aria-colindex="4" role="cell" class="tdClass">{{notice.noticeHit}}</td>
                    <td aria-colindex="5" role="cell"
                        class="tdClass">{{notice.noticeCreateTime}}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- <div class="row">
        <ul class="pagination  justify-content-center">
            <li class="page-item" data-pg="1"><a
                    href="${root }/notice?pgno=1&key=${navigation.key}&word=${navigation.word}"
                    class="page-link">최신</a></li>
            <c:if test="${navigation.startRange}">
                <li class="page-item" data-pg="1"><a
                        href="${root}/notice?pgno=1&key=${navigation.key}&word=${navigation.word}"
                        class="page-link">이전</a></li>
            </c:if>
            <c:if test="${!navigation.endRange}">
                <li class="page-item" data-pg="${navigation.startPage - 1}"><a
                        href="${root}/notice&pgno=${navigation.startPage - 1}&key=${navigation.key}&word=${navigation.word}"
                        class="page-link">다음</a></li>
            </c:if>

            <c:forEach begin="${navigation.startPage}"
                       end="${navigation.endPage}" step="1" varStatus="status">
                <c:if test="${status.index == navigation.currentPage}">
                    <li class="page-item active" data-pg="${status.index}"><a
                            href="${root}/notice?pgno=${status.index}&key=${navigation.key}&word=${navigation.word}"
                            class="page-link">${status.index}</a></li>
                </c:if>
                <c:if test="${status.index != navigation.currentPage}">
                    <li class="page-item" data-pg="${status.index}"><a
                            href="${root}/notice?pgno=${status.index}&key=${navigation.key}&word=${navigation.word}"
                            class="page-link">${status.index}</a></li>
                </c:if>
            </c:forEach>

            <c:if test="${navigation.endRange}">
                <li class="page-item" data-pg="${navigation.endPage}"><a
                        href="${root}/notice?pgno=${navigation.endPage}&key=${navigation.key}&word=${navigation.word}"
                        class="page-link">다음</a></li>
            </c:if>
            <c:if test="${!navigation.endRange}">
                <li class="page-item" data-pg="${navigation.endPage + 1}"><a
                        href="${root}/notice?pgno=${navigation.endPage + 1}&key=${navigation.key}&word=${navigation.word}"
                        class="page-link">다음</a></li>
            </c:if>
            <li class="page-item" data-pg="${navigation.totalPageCount}"><a
                    href="${root}/notice?pgno=${navigation.totalPageCount}&key=${navigation.key}&word=${navigation.word}"
                    class="page-link">마지막</a></li>
        </ul>
    </div> -->
</div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
    name: "BoardList",
    mounted() {
      const script = document.createElement('script')
      script.src = 'https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js'
      document.head.appendChild(script)
    },
  data() {
    return {
      noticelist: [],
    }
  },
  methods: {
    mvView(no){
        this.$router.push('/board/view/'+no);
        console.log(no);
    },
    mvWrite(){
        this.$router.push('/board/write');
    }
  },
  created() {
    axios.get("http://localhost:8080/enjoytrip/notice/api?pgno=1&key=&word=")
      .then(Response=>{
        this.noticelist = Response.data.data;
        console.log(this.noticelist);
      })
  },
}
</script>
