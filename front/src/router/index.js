import Vue from 'vue'
import Router from 'vue-router'
import MyExample from '../components/MyExample'
import PicturesMenu from '../components/PicturesMenu'
import Face from '../components/Face'

const originalPush = Router.prototype.push
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'face',
      component: Face,
      meta: {
        title: '门面',
        requireAuth: true,
        index: 1
      }
    },
    {
      path: '/menu',
      name: 'menu',
      component: PicturesMenu,
      meta: {
        title: '菜单',
        requireAuth: true,
        index: 2
      }
    },
    {
      path: '/myexample',
      name: 'myexample',
      component: MyExample,
      meta: {
        title: '相册',
        requireAuth: true,
        index: 3
      }
    }
  ]
  // mode: 'history'
})
