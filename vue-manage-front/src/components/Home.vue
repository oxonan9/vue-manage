<template>
  <div class="outside">
    <el-container>
      <el-header>
        <div>
          <i class="tubiao" style="float: left;" :class="getMenuCollapse()" @click="handleCollapse"></i>
          <span class="title">后台管理系统</span>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="el-dropdown-link">
              <el-avatar class="avatar" :src="avatar"></el-avatar>
              <span class="user">{{username}}</span>
              <i style="color:#ffffff" class="el-icon-caret-bottom"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="center">用户中心</el-dropdown-item>
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="auto" class="scroll-view">
          <LayoutMenu :isMenuCollapse="isMenuCollapse"></LayoutMenu>
        </el-aside>
        <el-main class="scroll-view">
          <el-tabs
            v-model="$store.state.activeRoute"
            type="card"
            closable
            @tab-click="clickTab"
            @tab-remove="removeTab"
          >
            <el-tab-pane
              v-for="item in $store.state.maintabs"
              :key="item.route"
              :label="item.name"
              :name="item.route"
            ></el-tab-pane>
          </el-tabs>
          <router-view class="router"></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import LayoutMenu from "./layout/LayoutMenu.vue";
import { setJwtToken, getTokenUser } from "@/lib/utils";

export default {
  name: "",
  data() {
    return {
      isMenuCollapse: false,
      username: "",
      avatar:
        "https://tva1.sinaimg.cn/large/007S8ZIlly1gfmxky0q1ej30hs0blgna.jpg"
    };
  },
  components: {
    LayoutMenu: LayoutMenu
  },
  created() {
    this.username = getTokenUser();
  },
  methods: {
    handleCollapse() {
      this.isMenuCollapse = !this.isMenuCollapse;
    },
    getMenuCollapse() {
      return {
        "el-icon-s-fold": !this.isMenuCollapse,
        "el-icon-s-unfold": this.isMenuCollapse
      };
    },
    // 退出功能
    logout() {
      this.$router.push("/");
      window.location.reload()
      sessionStorage.clear();
    },
    center() {
      this.$router.push("/center");
    },
    removeTab(targetName) {
      if (targetName !== "/home/firstpage") {
        this.$store.commit("removeTab", targetName);
        this.$router.push(
          this.$store.state.maintabs[this.$store.state.maintabs.length - 1]
            .route
        );
      }
    },
    clickTab(tab) {
      this.$router.push(tab.$options.propsData.name);
    }
  }
};
</script>

<style>
.outside {
  height: 100%;
}
.el-container {
  direction: vertical;
}

.el-header {
  position: relative;
  width: 100%;
  height: 60px;

  background-color: #262f41;
}
.el-aside {
  width: 100%;
  height: 100%;
  background: #354155;
}

.el-main {
  background-color: #ffffff;
  padding: 0 10px 10px 0;
  left: 200px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
}
.el-icon-s-fold,
.el-icon-s-unfold {
  font-size: 32px;
  line-height: 60px;
}
#app,
.el-container,
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.title {
  font-size: 27px;
  float: left;
  margin: 10px 15px;
  color: #ffffff;
}

.tubiao {
  color: #ffffff;
}

.header-right {
  line-height: 55px;
  float: right;
}

.avatar {
  vertical-align: middle;
}

.user {
  color: #ffffff;
  margin-left: 10px;
  font-size: 15px;
}

.el-tabs {
  position: fixed;
  border: 0;
  width: 100%;
  z-index: 1000;
  margin-bottom: 10px;
}

.el-tabs--border-card > .el-tabs__content {
  padding: 0;
}

.scroll-view::-webkit-scrollbar {
  display: none;
}

.router {
  margin-top: 50px;
  margin-left: 10px;
}
</style>
