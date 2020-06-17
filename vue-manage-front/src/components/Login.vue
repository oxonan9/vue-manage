<template>
  <div class="login">
    <el-form class="login-form">
      <h5>用户名:admin 密码:123456</h5>

      <el-alert :title="loginForm.errorMsg" type="error" v-show="loginForm.errorVisible"></el-alert>

      <el-form-item>
        <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>

      <el-form-item style="width:100%;">
        <el-button size="medium" type="primary" style="width:100%;" @click="userLogin">
          <span>登 录</span>
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { setJwtToken } from "@/lib/utils.js";
import { login } from "@/api/system.js";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
        errorMsg: "",
        errorVisible: false
      }
    };
  },
  methods: {
    userLogin() {
      console.log(this.loginForm.username);
      login(this.loginForm.username, this.loginForm.password)
        .then(res => {
          setJwtToken(res.data);
          this.$router.push("home");
        })
        .catch(err => {
          this.loginForm.errorMsg = err.message;
          this.loginForm.errorVisible = true;
        });
    }
  }
};
</script>

<style>
.login {
  margin: 0px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("https://tva1.sinaimg.cn/large/007S8ZIlly1gfh75pwrolj30zk0gcdj4.jpg");
  background-size: cover;
}
.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
}
</style>
