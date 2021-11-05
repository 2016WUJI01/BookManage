import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Homepage from '@/components/home/Homepage'
import Test from '@/components/Test'
import Layout from '@/components/Layout'
import User from '@/components/user/User'
import Book from '@/components/book/Book'
import UserAddDialog from '@/components/dialog/UserAddDialog'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    }, {
      path: '/test',
      name: 'Test',
      component: Test
    },
    {
      path: '/index',
      component: Layout,
      children: [ {
        path: '/',
        name: 'HomePage',
        component: Homepage
      }, {
        path: '/user',
        name: 'User',
        component: User,
        children: [{
          path: '/userAddDialog',
          name: 'UserAddDialog',
          component: UserAddDialog
        }]
      }, {
        path: '/book',
        name: 'Book',
        component: Book
      }]
    }
  ]
})
