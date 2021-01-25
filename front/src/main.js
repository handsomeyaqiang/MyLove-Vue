// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'
import ElementUI from 'element-ui'
// import Vconsole from 'vconsole'
import preview from 'vue-photo-preview'
import 'vue-photo-preview/dist/skin.css'
import axiosInstance from './utils/axiosinterceptor'
import VueIntro from 'vue-introjs'
import 'intro.js/introjs.css'

Vue.use(VueIntro)
var option = {
  maxSpreadZoom: 2, // 控制预览图最大的倍数，默认是2倍，我这里改成了原图
  fullscreenEl: true, // 控制是否显示右上角全屏按钮
  closeEl: true, // 控制是否显示右上角关闭按钮
  tapToClose: true, // 点击滑动区域应关闭图库
  shareEl: true, // 控制是否显示分享按钮
  zoomEl: true, // 控制是否显示放大缩小按钮
  counterEl: true, // 控制是否显示左上角图片数量按钮
  arrowEl: true, // 控制如图的左右箭头（pc浏览器模拟手机时）
  tapToToggleControls: true, // 点击应切换控件的可见性
  clickToCloseNonZoomable: true // 点击图片应关闭图库，仅当图像小于视口的大小时
}
Vue.use(preview, option)

// const vConsole = new Vconsole()
// Vue.use(vConsole)

Vue.prototype.$axios = axiosInstance
// axiosInstance.defaults.baseURL = '/apis'
axiosInstance.defaults.baseURL = ''
Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
