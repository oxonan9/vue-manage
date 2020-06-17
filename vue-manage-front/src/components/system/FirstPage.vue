<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="8">
          <div>
            <img :src="circleUrl" width="120px" height="120px" style="border-radius:50%" />

            <div class="user-info-cont">
              <div class="user-info-name">admin</div>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      circleUrl:
        "https://tva1.sinaimg.cn/large/007S8ZIlly1gfmxky0q1ej30hs0blgna.jpg"
    };
  },
  components: {}
};
</script>

<style>
.user-info-cont {
  padding-left: 50px;
  flex: 1;
  font-size: 14px;
  color: #999;
}
</style>
