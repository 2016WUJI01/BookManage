import Book from '@/components/book/Book'
import Bookback from '@/components/book/Bookback'
import Bookorder from '@/components/book/Bookorder'
import Homepage from '@/components/home/Homepage'
import Layout from '@/components/Layout'
import Login from '@/components/Login'
import User from '@/components/user/User'
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      component: Layout,
      children: [{
        path: '/',
        name: 'HomePage',
        component: Homepage
      },
      {
        path: '/user',
        name: 'User',
        component: User
      },
      {
        path: '/book',
        name: 'Book',
        component: Book
      },
      {
        path: '/bookback',
        name: 'BookBack',
        component: Bookback
      },
      {
        path: '/bookorder',
        name: 'BookOrder',
        component: Bookorder
      }
      ]
    }
  ]

})
