// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// import Vuelidate from 'vuelidate'
import App from './App'
import BootstrapVue from 'bootstrap-vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

import router from './router'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.config.devtools = true

Vue.router = router

axios.defaults.baseURL = 'http://localhost:9000/'

// Vue.use(Vuelidate)
Vue.use(BootstrapVue)
Vue.use(VueAxios, axios)

window.axios = axios
window.axios.defaults.headers.common = {
  'X-Requested-With': 'XMLHttpRequest',
  'Content-Type': 'application/json'
}

Vue.use(require('@websanova/vue-auth'), {
  auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
  http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
  router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
  refreshData: { enabled: false },
  fetchData: { enabled: false },
  loginData: {
    url: '/auth/login',
    method: 'POST',
    fetchUser: null
  },
  registerData: {
    url: '/auth/signup',
    method: 'PUT',
    redirect: '/login'
  },
  authRedirect: {
    path: '/login'
  },
  logoutData: {
    redirect: '/login'
  }
})

App.router = Vue.router

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
