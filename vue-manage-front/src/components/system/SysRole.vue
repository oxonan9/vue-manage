<template>
  <div>
    <el-card>
      <el-form
        style="float:left"
        :inline="true"
        :model="search"
        class="demo-form-inline"
        label-width="80px"
      >
        <el-form-item label="角色名称">
          <el-input v-model="search.roleName" placeholder="角色名称" @keyup.enter.native="getRoleData"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="getRoleData">查询</el-button>
        </el-form-item>

        <el-form-item>
          <el-button type="success" @click="handleAdd">添加</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="tableData"
        border
        default-expand-all
        stripe
        highlight-current-row
        style="width: 100%;margin-bottom: 20px;"
      >
        <el-table-column align="center" prop="id" label="角色ID"></el-table-column>
        <el-table-column align="center" prop="roleName" label="角色名称"></el-table-column>
        <el-table-column align="center" prop="roleCode" label="角色编号"></el-table-column>
        <el-table-column align="center" prop="roleDesc" label="角色描述"></el-table-column>
        <el-table-column align="center" prop="sort" label="排序"></el-table-column>
        <el-table-column label="操作" width="300" fixed="right">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>

            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除</el-button>

            <el-button
              size="mini"
              type="success"
              icon="el-icon-edit-outline"
              @click="handlePerm(scope.row)"
            >修改权限</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogFormVisible"
      width="50%"
      :before-close="handleCloseDialog"
      :close-on-click-modal="false"
    >
      <el-form :model="dialogForm" ref="dialogForm" :rules="dialogFormRules" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="角色名称" prop="roleName" required>
              <el-input v-model="dialogForm.roleName"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="角色编码" prop="roleCode" required>
              <el-input v-model="dialogForm.roleCode"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="角色描述" prop="roleDesc" required>
              <el-input v-model="dialogForm.roleDesc"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="排序序号" required>
              <el-input-number v-model="dialogForm.sort" :min="1" :max="100" label="排序"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="save">保存</el-button>
        <el-button @click="handleCloseDialog">取消</el-button>
      </div>
    </el-dialog>

    <el-dialog
      :title="dialogTitlePerm"
      :visible.sync="dialogFormVisiblePerm"
      width="50%"
      :before-close="handleCloseDialogPerm"
      :close-on-click-modal="false"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <multi-tree
            :data="menuData"
            :labelPropName="menuLabelPropName"
            :buttonName="menuButtonName"
            :defaultExpandedKeys="menuDefaultExpandedKeys"
            :defaultCheckedKeys="menuDefaultCheckedKeys"
            @handleCheckedKeys="handleMenuCheckedKeys"
          />
        </el-col>

        <el-col :span="12">
          <multi-tree
            :data="apiData"
            :labelPropName="apiLabelPropName"
            :buttonName="apiButtonName"
            :defaultExpandedKeys="apiDefaultExpandedKeys"
            :defaultCheckedKeys="apiDefaultCheckedKeys"
            @handleCheckedKeys="handleApiCheckedKeys"
          ></multi-tree>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import {
  getRoles,
  saveRole,
  delRole,
  checkMenuTree,
  checkApiTree,
  saveMenuPerm,
  saveApiPerm
} from "@/api/system.js";
import axios from "axios";
import MultiTree from "@/components/layout/MultiTree";
export default {
  name: "",
  components: { MultiTree },
  data() {
    return {
      search: {
        roleName: ""
      },
      tableData: [],
      dialogForm: {
        id: null,
        roleName: "",
        roleCode: "",
        roleDesc: "",
        sort: null
      },

      dialogFormVisible: false,
      dialogFormVisiblePerm: false,
      dialogTitle: "",
      dialogTitlePerm: "分配权限",

      //分配权限相关
      permRoleId: null,
      menuData: [],
      menuLabelPropName: "menuName",
      menuButtonName: "保存菜单权限",
      menuDefaultExpandedKeys: [],
      menuDefaultCheckedKeys: [],
      apiData: [],
      apiLabelPropName: "apiName",
      apiButtonName: "保存接口访问权限",
      apiDefaultExpandedKeys: [],
      apiDefaultCheckedKeys: [],

      //表单数据校验   item必须要有prop属性对应
      dialogFormRules: {
        roleName: [
          { required: true, message: "请选择角色名称", trigger: "blur" }
        ],
        roleCode: [
          { required: true, message: "请输入角色编码", trigger: "blur" }
        ],
        roleDesc: [
          { required: true, message: "请输入角色描述", trigger: "blur" }
        ],
        sort: [
          {
            required: true,
            message: "请输入当前菜单在同级菜单内的排序序号",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getRoleData();
  },
  methods: {
    getRoleData() {
      getRoles(this.search.roleName).then(res => {
        console.log(res.data);
        this.tableData = res.data;
      });
    },
    handleDelete(row) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          delRole(row.id)
            .then(res => {
              this.getRoleData(); //删除成功 刷新表格
              this.$message({ message: res.data, type: "success" });
              this.handleCloseDialog();
            })
            .catch(err => {
              this.$message({ message: err.message, type: "error" });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //重置dialog框信息
    resetDialogForm() {
      this.dialogForm.id = null;
      this.dialogForm.menuPid = null;
      this.dialogForm.menuName = "";
      this.dialogForm.url = "";
      this.dialogForm.icon = "";
      this.dialogForm.sort = "";
    },
    //弹出添加角色的对话框
    handleAdd() {
      this.dialogFormVisible = true;
      this.resetDialogForm();
      this.dialogTitle = "新增角色";
    },
    //弹出修改角色的对话框
    handleEdit(index, row) {
      this.dialogFormVisible = true;
      this.resetDialogForm();
      this.dialogTitle = "修改角色";
      this.dialogForm = { ...row }; //用于被选行的数据赋给表单数据
    },
    //关闭角色对话框
    handleCloseDialog() {
      this.dialogFormVisible = false;
    },
    handleCloseDialogPerm() {
      this.dialogFormVisiblePerm = false;
    },
    //弹出分配权限对话框
    handlePerm(row) {
      this.permRoleId = row.id;
      const _this = this;

      //axios 执行多个并发请求 官网：https://www.kancloud.cn/yunye/axios/234845
      axios
        .all([checkMenuTree(row.id), checkApiTree(row.id)])
        .then(
          axios.spread(function(menuRes, apiRes) {
            // 只有两个请求都成功才会执行
            _this.menuData = menuRes.data.treeData;
            _this.menuDefaultExpandedKeys = menuRes.data.expandedKeys;
            _this.menuDefaultCheckedKeys = menuRes.data.checkedKeys;

            _this.apiData = apiRes.data.treeData;
            _this.apiDefaultExpandedKeys = apiRes.data.expandedKeys;
            _this.apiDefaultCheckedKeys = apiRes.data.checkedKeys;

            _this.dialogFormVisiblePerm = true;
            _this.dialogTitlePerm = row.roleName + ":分配权限";
          })
        )
        .catch(err => {
          this.$message({ message: err.message, type: "error" });
        });
    },
    //点击分配菜单权限
    handleMenuCheckedKeys(checkedKeys) {
      saveMenuPerm(this.permRoleId, checkedKeys)
        .then(res => {
          this.$message({ message: res.data, type: "success" });
        })
        .catch(err => {
          this.$message({ message: err.message, type: "error" });
        });
    },
    //点击分配接口权限
    handleApiCheckedKeys(checkedKeys) {
      saveApiPerm(this.permRoleId, checkedKeys)
        .then(res => {
          this.$message({ message: res.data, type: "success" });
        })
        .catch(err => {
          this.$message({ message: err.message, type: "error" });
        });
    },

    //保存角色信息 新增/修改 用一个接口，用id是否为null区分
    save() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.$confirm("确定提交数据么?")
            .then(_ => {
              saveRole(this.dialogForm)
                .then(res => {
                  this.$message({ message: res.data, type: "success" });
                  this.getRoleData(); //修改之后，重新查询table
                  this.handleCloseDialog();
                })
                .catch(err => {
                  this.$message({ message: err.message, type: "error" });
                });
            })
            .catch(err => {
              this.handleCloseDialog();
              this.$message({ message: err.message, type: "error" });
            });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>
</style>
