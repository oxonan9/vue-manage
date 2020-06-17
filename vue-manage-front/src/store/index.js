import Vuex from 'vuex'
import Vue from 'vue'
import { getMenuTreeByUsername } from '@/api/system'


Vue.use(Vuex)

//创建store对象  
export default new Vuex.Store({
    //state 存储全局共享数据
    state: {
        //存放{ route: 路由路径, name: tab显示名称}对象数组
        //存放{ route: 路由路径, name: tab显示名称}对象数组
        maintabs: [{ route: "/home/firstpage", name: "系统首页", closable: false }],
        //当前被激活显示的那个Tab内容对应的route
        activeRoute: "/home/firstPage",
        menuList: [
        ],
        addTabName: ""
    },
    mutations: {
        setActiveRoute(state, route) {
            state.activeRoute = route;
        },
        addTabMutation(state, route) {
            let isAlreadyIn =
                state.maintabs.some(item => item.route === route)
            this.commit("findMenuNameByRoute", route);
            if (!isAlreadyIn && state.addTabName !== "") {
                state.maintabs.push({ route: route, name: state.addTabName });
            }
        },
        removeTab(state, route) {
            if (route !== "/home/firstpage") {
                state.maintabs = state.maintabs.filter(
                    item => item.route !== route
                )
                state.activeRoute = state.maintabs[state.maintabs.length - 1].route
            }
        },
        findMenuNameByRoute(state, route) {
            let findOne;
            for (let i in state.menuList) {
                let tmpArr = state.menuList[i].children.filter(
                    item => item.path === route
                )
                if (tmpArr.length > 0) {
                    findOne = tmpArr[0]
                    break;
                }
            }
            state.addTabName = findOne ? findOne.name : "";
        }
    },
    actions: {
        addTab({ state, commit }, route) {
            //因为menuList里面有一项非菜单路由
            //大于等于一表示菜单已经加载过了，并且页面没刷新，
            //只是切换路由组件,不重新加载菜单
            commit("setActiveRoute", route);
            if (state.menuList.length <= 1) {
                getMenuTreeByUsername()
                    .then(res => {
                        console.log(res.data)
                        state.menuList = [...res.data]
                        commit("addTabMutation", route);  //菜单加载完成之后将Tab导航项添加
                    })
            } else {
                commit("addTabMutation", route);//菜单加载完成之后将Tab导航项添加
            }
        }
    }
})  