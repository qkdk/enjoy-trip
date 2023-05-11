
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AppBoard from '@/views/AppBoard'
import BoardList from '@/components/board/BoardList'
import BoardView from '@/components/board/BoardView'
import BoardModify from '@/components/board/BoardModify'
import BoardWrite from '@/components/board/BoardWrite'



Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: '/board',
    name: 'board',
    component: AppBoard,
    redirect: '/board/list',
    children: [
      {
        path: 'list',
        name: 'boardlist',
        component: BoardList,
      },
      {
        path: 'view/:no',
        name: 'view',
        component: BoardView
      },
      {
        path: 'modify/:no',
        name: 'modify',
        component: BoardModify
      },
      {
        path: 'write',
        name: 'write',
        component: BoardWrite
      }
    ]
  },
  {
    path: "/attraction",
    name: "attraction",
    // component: ...
  },
  {
    path: "/hotplace",
    name: "hotplace",
    // component: ...
  },
  {
    path: "/notice",
    name: "notice",
    redirect: '/board'
  },
  {
    path: "/plan",
    name: "plan",
    // component: ...
  },
]

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
