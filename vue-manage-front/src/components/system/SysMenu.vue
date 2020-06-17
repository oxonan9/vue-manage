<template>
  <div>
    <el-card>
      <el-form
        style="float:left"
        :inline="true"
        :model="formInline"
        class="demo-form-inline"
        label-width="80px"
      >
        <el-form-item label="菜单名称">
          <el-input
            v-model="formInline.menuName"
            placeholder="菜单名称"
            @keyup.enter.native="getMenuData"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="getMenuData">查询</el-button>
        </el-form-item>

        <!-- <el-form-item>
          <el-button type="default" @click="resetForm">重置</el-button>
        </el-form-item>-->

        <el-form-item>
          <el-button type="success" @click="handleAdd">添加</el-button>
        </el-form-item>
      </el-form>

      <el-table
        v-loading="loading"
        :data="tableData"
        row-key="id"
        border
        default-expand-all
        stripe
        highlight-current-row
        style="width: 100%;margin-bottom: 20px;"
      >
        <el-table-column prop="menuName" label="菜单名称" fixed="left" />
        <el-table-column prop="url" label="访问路径" align="center" />
        <el-table-column prop="icon" label="菜单图标" align="center" />
        <el-table-column prop="sort" label="排序" align="center" />
        <!-- <el-table-column
          prop="status"
          label="是否禁用"
          width="200"
          align="center"
          :formatter="statusFormat"
        />-->
        <el-table-column label="操作" width="200" fixed="right">
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
          <el-col :span="24">
            <el-form-item label="上级组织" prop="menuPid">
              <el-tree-select
                :elTreeProps="elTreeProps"
                :elTreeData="tableData"
                :defaultSelectedId="dialogForm.menuPid"
                :disabled="elTreeDisabled"
                @handleTreeSelected="handleTreeSelected($event)"
                @validateSelectTree="validateSelectTree"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName" required>
              <el-input v-model="dialogForm.menuName"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="访问路径" prop="url" required>
              <el-input v-model="dialogForm.url"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="菜单图标">
              <el-input v-model="dialogForm.icon"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="菜单排序" required>
              <el-input-number v-model="dialogForm.sort" :min="1" :max="100" label="排序"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="saveMenu">保存</el-button>
        <el-button @click="handleCloseDialog">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { menuTree, menuSave, menuDel } from "@/api/system.js";

import ElTreeSelect from "@/components/layout/TreeSelect";

export default {
  name: "",
  data() {
    return {
      tableData: [],
      formInline: {
        menuName: "",
        region: ""
      },
      loading: true,
      sortnum: 1,
      elTreeDisabled: false,
      elTreeProps: {
        // el-tree-select配置项（必选）
        value: "id",
        label: "menuName",
        children: "children"
      },
      dialogFormVisible: false,
      dialogTitle: "",
      dialogForm: {
        id: null,
        menuPid: null, // el-tree-select初始ID（可选）
        menuName: "",
        url: "",
        icon: "",
        sort: 1
      },
      dialogFormRules: {
        menuPid: [
          { required: true, message: "请选择上级菜单", trigger: "blur" }
        ],
        menuName: [
          { required: true, message: "请输入菜单名称", trigger: "blur" }
        ],
        url: [{ required: true, message: "请输入访问路径", trigger: "blur" }],
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
    this.getMenuData();
  },
  components: { ElTreeSelect },

  methods: {
    getMenuData() {
      menuTree(this.formInline.menuName).then(res => {
        this.loading = false;
        this.tableData = res.data;
      });
    },
    resetForm() {
      this.formInline.menuName = "";
      this.getMenuData();
    },
    handleCloseDialog(done) {
      //   this.$confirm("确认关闭？")
      //     .then(_ => {
      //       done();
      //     })
      //     .catch(_ => {});
      this.dialogFormVisible = false;
    },
    handleAdd(index, row) {
      this.elTreeDisabled = false;
      this.dialogFormVisible = true;
      this.resetDialogForm();
      this.dialogTitle = "新增菜单";
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

    handleEdit(index, row) {
      this.elTreeDisabled = true; //上级组织禁止修改，因为修改错误会打乱树形结构
      this.dialogFormVisible = true;
      this.resetDialogForm();
      this.dialogTitle = "修改菜单";
      this.dialogForm = { ...row }; //用于被选行的数据赋给表单数据
      /**
       *  这里说下{...row}语法，它是ES6的展开运算符用法，一个简写的赋值操作
       * 例如：row:{a:1,b:1} xxxx = {...row} 即 xxxx.a=row.a xxxx.b =row
       * 这里如果直接使用this.dialogForm=row的话相当于将row的地址赋给了dialogForm，如果row为空，dialogForm也就为空了
       * 所以这里将地址和值都赋给了dialogForm
       */
    },
    validateSelectTree() {
      this.$refs.dialogForm.validateField("menuPid");
    },
    handleTreeSelected(value) {
      this.dialogForm.menuPid = value;
      this.$refs.dialogForm.validateField("menuPid");
    },
    saveMenu() {
      //新增/修改信息  共用一个接口，用id区分
      this.$refs.dialogForm.validate(valid => {
        if (valid) {
          this.$confirm("确定提交数据么?")
            .then(_ => {
              menuSave(this.dialogForm)
                .then(res => {
                  this.$message({ message: res.data, type: "success" });
                  this.getMenuData(); //修改之后，重新查询table
                  this.handleCloseDialog();
                })
                .catch(err => {
                  this.$message({ message: err.message, type: "error" });
                });
            })
            .catch(_ => {
              this.handleCloseDialog();
            });
        } else {
          return false;
        }
      });
    },
    handleDelete(row) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          menuDel(row.id, row.menuPid)
            .then(res => {
              this.getMenuData(); //删除成功 刷新表格
              this.$message({ message: res.data, type: "success" });
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
    }
  }
};
</script>

<style scoped>
</style>
