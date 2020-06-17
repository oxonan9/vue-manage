<template>
  <div>
    <el-card>
      <el-row :gutter="20">
        <el-col :span="4">
          <!-- <el-input placeholder="输入部门关键字" v-model="filterText"></el-input> -->
          <el-tree
            class="filter-tree"
            :data="orgData"
            :props="elTreeProps"
            default-expand-all
            :expand-on-click-node="false"
            :filter-node-method="filterOrg"
            @node-click="orgNodeClick"
            ref="orgQueryTree"
          ></el-tree>
        </el-col>

        <el-col :span="20">
          <el-form :inline="true" :model="search" class="demo-form-inline" label-width="80px">
            <el-row :gutter="20" style="width:100%">
              <el-col :span="8">
                <el-form-item label="用户姓名">
                  <el-input
                    v-model="userQueryform.username"
                    placeholder="用户姓名"
                    @keyup.enter.native="getRoleData"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="联系电话">
                  <el-input
                    v-model="userQueryform.phone"
                    placeholder="联系电话"
                    @keyup.enter.native="getRoleData"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="用户邮箱">
                  <el-input
                    v-model="userQueryform.email"
                    placeholder="用户邮箱"
                    @keyup.enter.native="getRoleData"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="创建时间" prop="timeRange">
                  <el-date-picker
                    v-model="userQueryform.timeRange"
                    type="daterange"
                    value-format="yyyy-MM-dd hh:mm:ss"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                  ></el-date-picker>
                </el-form-item>
              </el-col>

              <el-col :span="6">
                <el-form-item>
                  <el-button type="primary" @click="getUserData">查询</el-button>
                </el-form-item>

                <el-form-item>
                  <el-button type="success" @click="handleAdd">添加</el-button>
                </el-form-item>
              </el-col>
            </el-row>
            <!-- <el-form-item label="用户状态">
          <el-input v-model="search.roleName" placeholder="用户状态" @keyup.enter.native="getRoleData"></el-input>
            </el-form-item>-->
          </el-form>
          <el-table
            v-loading="loading"
            :data="tableData"
            border
            default-expand-all
            stripe
            highlight-current-row
            style="width: 100%;margin-bottom: 20px;"
          >
            <el-table-column align="center" prop="id" label="用户编号"></el-table-column>
            <el-table-column align="center" prop="username" label="用户姓名"></el-table-column>
            <el-table-column align="center" prop="phone" label="联系电话"></el-table-column>
            <el-table-column align="center" prop="email" label="用户邮箱"></el-table-column>
            <el-table-column align="center" prop="orgName" label="所在组织"></el-table-column>
            <el-table-column align="center" prop="createTime" label="创建日期"></el-table-column>
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
                  @click="handleRole(scope.row)"
                >分配权限</el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.pageNum"
            :page-sizes="[10, 50, 100, 200]"
            :page-size="pagination.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total"
            style="float: right;margin-bottom: 10px"
          ></el-pagination>
        </el-col>
      </el-row>
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
            <el-form-item label="用户姓名" prop="username" required>
              <el-input v-model="dialogForm.username"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="用户邮箱" prop="email">
              <el-input v-model="dialogForm.email"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="dialogForm.phone"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="上级组织" prop="orgId">
              <el-tree-select
                :elTreeProps="elTreeProps"
                :elTreeData="orgData"
                :defaultSelectedId="dialogForm.orgId"
                :disabled="elTreeDisabled"
                @handleTreeSelected="handleTreeSelected($event)"
                @validateSelectTree="validateSelectTree"
              />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="save">保存</el-button>
        <el-button @click="handleCloseDialog">取消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="roleDialogTitle" :visible.sync="roleDialogVisible">
      <el-button type="primary" size="small" style="float: right" @click="saveCheckedRoles">保存角色</el-button>
      <el-transfer
        v-model="checkedRoleIds"
        :props="roleDataProp"
        :data="roleDatas"
        :titles="['备选角色', '已选角色']"
      ></el-transfer>
    </el-dialog>
  </div>
</template>

<script>
import {
  getUsers,
  saveUser,
  delUser,
  orgTree,
  getCheckedRoles,
  saveRoles
} from "@/api/system.js";
import ElTreeSelect from "@/components/layout/TreeSelect";
import axios from "axios";
export default {
  name: "",
  components: {
    ElTreeSelect
  },
  data() {
    return {
      tableData: [],
      orgData: [],
      search: {
        username: "",
        phone: "",
        email: ""
      },

      loading: true,
      dialogFormVisible: false,
      dialogTitle: "",
      elTreeDisabled: false,

      roleDialogTitle: "分配角色",
      roleDialogVisible: false,

      checkedRoleIds: [],
      roleDataProp: {
        //参考上文props解释
        key: "id",
        label: "roleName"
      },
      roleDatas: [], //系统内所有角色列表
      userQueryform: {
        username: "",
        phone: "",
        enabled: "",
        email: "",
        orgId: null,
        timeRange: ["", ""]
      },
      pagination: {
        pageNum: 1,
        pageSize: 10,
        total: null
      },
      dialogForm: {
        id: null,
        orgId: null,
        username: "",
        phone: "",
        email: "",
        orgName: "",
        sort: null
      },
      elTreeProps: {
        // el-tree-select配置项（必选）
        value: "id",
        label: "orgName",
        children: "children"
      },
      handlingUserId: null,
      //表单数据校验   item必须要有prop属性对应
      dialogFormRules: {
        username: [
          { required: true, message: "请输入用户姓名", trigger: "blur" }
        ],
        email: [
          { type: "email", message: "请输入正确的邮箱地址", trigger: "blur" }
        ],
        orgId: [{ required: true, message: "请选择所在组织", trigger: "blur" }],
        phone: [
          {
            pattern: /^1[34578]\d{9}$/,
            message: "目前只支持中国大陆的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getUserData();
  },
  methods: {
    getUserData() {
      const _this = this;
      //日期选择器清除，默认清除会将timeRange为null，会报读取错误
      if (this.userQueryform.timeRange == null) {
        this.userQueryform.timeRange = ["", ""];
      }
      axios
        .all([getUsers(this.userQueryform, this.pagination), orgTree("")])
        .then(
          axios.spread(function(userRes, orgRes) {
            _this.pagination.pageNum = userRes.data.pageNum;
            _this.pagination.pageSize = userRes.data.pageSize;
            _this.pagination.total = userRes.data.total;

            _this.loading = false;
            _this.tableData = userRes.data.list;
            _this.orgData = orgRes.data;
          })
        )
        .catch(err => {
          _this.loading = false;
          _this.$message({ message: err.message, type: "error" });
        });
    },
    handleAdd() {
      this.dialogFormVisible = true;
      this.resetDialogForm();
      this.dialogTitle = "新增用户信息";
    },
    handleEdit(index, row) {
      this.dialogFormVisible = true;
      this.resetDialogForm();
      this.dialogTitle = "修改用户";
      this.dialogForm = { ...row }; //用于被选行的数据赋给表单数据
    },
    validateSelectTree() {
      this.$refs.dialogForm.validateField("orgId");
    },
    handleTreeSelected(value) {
      this.dialogForm.orgId = value;
      this.$refs.dialogForm.validateField("orgId");
    },
    handleDelete(row) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          delUser(row.id)
            .then(res => {
              this.getUserData(); //删除成功 刷新表格
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
    handleCloseDialog() {
      this.dialogFormVisible = false;
    },
    //重置dialog框信息
    resetDialogForm() {
      this.dialogForm.id = null;
      this.dialogForm.orgId = null;
      this.dialogForm.username = "";
      this.dialogForm.phone = "";
      this.dialogForm.email = "";
      this.dialogForm.orgName = "";
      this.dialogForm.sort = null;
    },
    filterOrg(value, data) {
      if (!value) return true;
      return data.orgName.indexOf(value) !== -1;
    },
    //当树形节点被点击，触发表单重新查询
    orgNodeClick(node) {
      this.userQueryform.orgId = node.id;
      this.getUserData();
    },
    //监听每页显示多少条数据
    handleSizeChange(val) {
      this.$set(this.pagination, "pageSize", val);
      this.getUserData();
    },
    //监听当前是第几页
    handleCurrentChange(val) {
      this.$set(this.pagination, "pageNum", val);
      this.getUserData();
    },
    handleRole(row) {
      this.handlingUserId = row.id;
      getCheckedRoles(row.id)
        .then(res => {
          console.log(res);
          this.roleDialogTitle = row.username + ":分配权限";
          this.roleDialogVisible = true;
          this.roleDatas = res.data.roleDatas;
          this.checkedRoleIds = res.data.checkedRoleIds;
        })
        .catch(_ => {
          this.$message({ message: err.message, type: "error" });
        });
    },
    saveCheckedRoles() {
      saveRoles(this.handlingUserId, this.checkedRoleIds)
        .then(res => {
          this.$message({ message: res.data, type: "success" });
          this.roleDialogVisible = false;
          this.getUserData();
        })
        .catch(_ => {
          this.handleCloseDialog();
          this.$message({ message: err.message, type: "error" });
        });
    },
    save() {
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.$confirm("确定提交数据么?")
            .then(_ => {
              saveUser(this.dialogForm).then(res => {
                this.$message({ message: res.data, type: "success" });
                this.getUserData(); //修改之后，重新查询table
                this.handleCloseDialog();
              });
            })
            .catch(_ => {
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
