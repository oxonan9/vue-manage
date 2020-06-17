<template>
  <div class="div">
    <el-menu
      :default-active="$store.state.activeRoute"
      class="el-menu-vertical"
      :collapse="isMenuCollapse"
      background-color="#354155"
      text-color="#ffffff"
    >
      <template v-for="menu in this.$store.state.menuList">
        <el-submenu :index="menu.path" :key="menu.id">
          <template slot="title">
            <i :class="menu.icon"></i>
            <span slot="title">{{menu.name}}</span>
          </template>

          <!-- @click="showTab(item.name,item.path) -->
          <router-link v-for="item in menu.children" :to="item.path" :key="item.id">
            <el-menu-item :index="item.path" :key="item.id">
              <i :class="item.icon"></i>
              <span slot="title">{{item.name}}</span>
            </el-menu-item>
          </router-link>
        </el-submenu>
      </template>
    </el-menu>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import store from "@/store/index.js";

export default {
  name: "LayoutMenu",
  // 当页面刷新和路由改变时刷新导航 https://blog.csdn.net/qq_37674616/article/details/83576798
  props: ["isMenuCollapse"],
  methods: {}
};
</script>

<style>
.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
  /* background:#354155; */
}

.div {
  width: auto;
}

a {
  text-decoration: none;
}
.el-menu {
  border-right: 0;
}
</style>
