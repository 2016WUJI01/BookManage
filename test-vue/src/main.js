// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// 引入axios
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import moment from 'moment'
import Vue from 'vue'
import VueAxios from 'vue-axios'
import App from './App'
import router from './router'

Vue.use(ElementUI)
// 设置全局的axios
Vue.use(VueAxios, axios)
axios.defaults.baseURL = '/api'
Vue.config.productionTip = false
Vue.prototype.$moment = moment
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
