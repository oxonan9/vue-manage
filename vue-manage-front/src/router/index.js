import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import SysUser from '@/components/system/SysUser'
import SysRole from '@/components/system/SysRole'
import SysApi from '@/components/system/SysApi'
import SysOrg from '@/components/system/SysOrg'
import FirstPage from '@/components/system/FirstPage'
import SysMenu from '@/components/system/SysMenu'
import store from '@/store/index'
import { refreshToken } from '@/api/system'
import { setJwtToken } from "@/lib/utils";


Vue.use(Router)

const router = new Router({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', name: "login", component: Login },
    {
      path: '/home',
      name: "home",
      component: Home,
      children: [
        { path: '', redirect: 'firstPage' },
        { path: 'firstPage', component: FirstPage },
        { path: "sysuser", component: SysUser },
        { path: "sysapi", component: SysApi },
        { path: "sysorg", component: SysOrg },
        { path: "sysrole", component: SysRole },
        { path: "sysmenu", component: SysMenu },
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.name !== 'login') {
    refreshToken().then(res => {
      //没有获得新的token==null，
      // 表示旧的token已经失效，需要重新登录
      if (res.data == null) {
        next({ name: 'login' }) //去登录界面
        setJwtToken('') //清空token
      } else {//否则去你想去的界面，并把新的token保存起来
        console.log("456" + res.data);
        // 把全局配置加载完成再去你想去的页面
        next()
        setJwtToken(res.data)
      }
    })
  } else {//每次去到登录页面都刷新一下，清除vuex状态
    next()
    setJwtToken('') //清空token
  }

  if (to.name === 'firstpage') {
    store.dispatch('addTab', to.path)
  }
})

router.afterEach((to, from) => {
  //store.commit('addTab',to.path)
  store.dispatch('addTab', to.path)
})
export default router
