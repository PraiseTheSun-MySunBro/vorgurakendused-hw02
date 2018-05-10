import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import Signup from '@/components/Signup'
import NewPost from '@/components/NewPost'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      meta: {
        auth: true
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        auth: false
      }
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup,
      meta: {
        auth: false
      }
    },
    {
      path: '/post/add',
      name: 'NewPost',
      component: NewPost,
      meta: {
        auth: true
      }
    }
  ]
})
