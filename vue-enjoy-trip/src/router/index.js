<<<<<<< HEAD
import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import AppBoard from '@/views/AppBoard'
import BoardList from '@/components/board/BoardList'
import BoardView from '@/components/board/BoardView'
import BoardModify from '@/components/board/BoardModify'

=======
import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
>>>>>>> eb1832a12f41a3d96fdcbc67fb6c53a2ac4aa9a4

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
<<<<<<< HEAD
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
      }
    ]
  }
]
=======
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
    // component: ...
  },
  {
    path: "/plan",
    name: "plan",
    // component: ...
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
];
>>>>>>> eb1832a12f41a3d96fdcbc67fb6c53a2ac4aa9a4

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
