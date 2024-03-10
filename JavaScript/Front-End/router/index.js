import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/films',
    name: 'filmsview',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('../views/FilmsView.vue')
  },
  {
    path: '/series',
    name: 'seriesview',
    component: () => import('../views/SeriesView.vue')
  },
  {
    path: '/details/:title',
    name: 'media.details',
    component: () => import('../views/VodDetailsView.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router