import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import 'bulma/css/bulma.css'

Vue.config.productionTip = false

Vue.use({
    install (Vue) {
        Vue.prototype.$server = axios.create({
            baseURL: 'http://localhost:8123/'
        })
    }
})

new Vue({
  render: h => h(App),
}).$mount('#app')
