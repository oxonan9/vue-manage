<template>
  <div>
    <el-card>
      <div>
        <el-button size="mini" type="primary" @click="handleCheckedKeys">{{buttonName}}</el-button>
        <el-tree
          ref="tree"
          :data="data"
          show-checkbox
          :check-strictly="true"
          node-key="id"
          :default-expanded-keys="defaultExpandedKeys"
          :default-checked-keys="defaultCheckedKeys"
          :props="defaultProps"
        />
      </div>
    </el-card>
  </div>
</template>


<script>
export default {
  name: "MultiTree",
  props: {
    buttonName: { type: String, default: "获取" },
    data: { type: Array, default: () => [] },
    labelPropName: { type: String, default: "" },
    defaultExpandedKeys: { type: Array, default: () => [] },
    defaultCheckedKeys: { type: Array, default: () => [] }
  },
  data() {
    return {
      defaultProps: {
        children: "children",
        label: this.labelPropName
      }
    };
  },
  methods: {
    handleCheckedKeys() {
      this.$emit("handleCheckedKeys", this.$refs.tree.getCheckedKeys());
    }
  }
};
</script>