// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// import Vuelidate from 'vuelidate'
import App from './App'
import BootstrapVue from 'bootstrap-vue'
// import axios from 'axios'
// import VueAxios from 'vue-axios'
// import VueAuth from '@websanova/vue-auth'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import router from './router'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.config.devtools = true
Vue.router = router
// axios.defaults.baseURL = 'http://localhost:9090/'

// Vue.use(Vuelidate)
Vue.use(BootstrapVue)
// Vue.use(VueAxios, axios)

// window.axios = axios
// window.axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
