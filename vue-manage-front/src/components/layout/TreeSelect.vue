<template>
  <el-select ref="tsSelect" :value="selectedTitle"
             :clearable="clearable" :disabled="disabled"
              @clear="clearSelected" @change="validateSelectTree"
              :placeholder="placeholder" style="width: 100%">
    <el-option :value="selectedTitle" :label="selectedTitle">
      <el-tree
        id="tree-option"
        ref="selectTree"
        :accordion="accordion"
        :data="elTreeData"
        :props="elTreeProps"
        :node-key="elTreeProps.value"
        :default-expanded-keys="defaultExpandedKey"
        @node-click="handleNodeClick">
      </el-tree>
    </el-option>
  </el-select>
</template>

<script>
  export default {
    name: "el-tree-select",
    props:{//props中所有属性都由父组件传过来，不传就使用默认值
      elTreeProps:{
        type: Object,
        default: () => ({
          value:'id',             // elTreeData中数据记录ID字段名
          label: 'title',         // elTreeData中数据记录名称字段（这个经常变化）
          children: 'children'    // elTreeData中数据记录子级字段名
        })
      },
      // 选项列表数据(树形结构的对象数组)
      elTreeData:{ type: Array, default:() => [] },
      // 初始下拉项选择值（可选，新增功能null，修改功能填写具体值）
      defaultSelectedId:{ type: Number, default: null },
      // 可清空选项，配合el-select使用（可选）
      clearable:{ type:Boolean, default: true },
      // 展开正在选择的父节点，同时自动收起其他失去焦点的节点（可选）
      accordion:{ type:Boolean, default: true },
      //是否禁止当前下拉树使用，只做显示，不能下拉选
      disabled:{type:Boolean, default: false},
      //placeholder
      placeholder:{type:String, default: ""}
    },
    data() {
      return {
        selectedId: this.defaultSelectedId,  //当前选择项目的value
        selectedTitle:'',   //当前选择项的title
        defaultExpandedKey:[this.defaultSelectedId]  //树形默认展开的节点
      }
    },
    /*mounted(){
      this.initHandle()
    },*/
    methods: {
      // 默认值初始化值
      initHandle(){
        if(this.selectedId && this.elTreeData.length > 0){
          this.defaultExpandedKey = [this.selectedId]      // 设置默认展开
          this.$nextTick(()=>{ //待组件渲染完成，执行代码
            this.$refs.selectTree.setCurrentKey(this.selectedId)       // 设置默认选中
          })
        }
        this.initScroll()
      },
      // 初始化滚动条
      initScroll(){
        this.$nextTick(()=>{
          let scrollWrap = document.querySelectorAll('.el-scrollbar .el-select-dropdown__wrap')[0]
          let scrollBar = document.querySelectorAll('.el-scrollbar .el-scrollbar__bar')
          scrollWrap.style.cssText = 'margin: 0px; max-height: none; overflow: hidden;'
          scrollBar.forEach(ele => ele.style.width = 0)
        })
      },
      // 点击切换选项，参数为当前点击节点
      handleNodeClick(node){
        this.selectedTitle = node[this.elTreeProps.label]
        this.selectedId = node[this.elTreeProps.value]
        //清空默认展开节点，由点击动作决定展开节点
        this.defaultExpandedKey = []
        //点击选中项目之后，下拉树菜单收回
        this.$refs.tsSelect.blur()
        //将点击选择项的id传给父组件
        this.$emit('handleTreeSelected',this.selectedId)
      },
      // 清除选中
      clearSelected(){
        this.selectedTitle = ''
        this.selectedId = null
        this.defaultExpandedKey = []
        //清除选中项的样式
        let allNode = document.querySelectorAll('#tree-option .el-tree-node')
        allNode.forEach((element)=>element.classList.remove('is-current'))
        //通知父节点，选中项清除id:null
        this.$emit('handleTreeSelected',null)
      },
      validateSelectTree(){
        this.$emit('validateSelectTree')
      },
      //树形结构递归查找，根据selectedId查找对应的selectedTitle
      findValueTitle: function (treeData,result = "") {
        for(let i in treeData){
          if(treeData[i].id === this.selectedId){
            result = treeData[i][this.elTreeProps.label]
            break;
          }
          if(treeData[i].children && treeData[i].children.length){
            result = this.findValueTitle(treeData[i].children)
            if(result){break;}
          }
        }
        return result
      }
    },
    watch: {//监听父节点传过来的参数props发生变化
      defaultSelectedId:{
        immediate: true,
        handler (newVal,oldVal) {
          this.selectedId = newVal
          this.selectedTitle = this.findValueTitle(this.elTreeData);
          this.initHandle()
        }
      } /*,
      elTreeData: function (newVal,oldVal) {
        this.selectedTitle = this.findValueTitle(newVal);
        this.initHandle()
      }*/
    }
  }
</script>

<style>
  .el-scrollbar .el-scrollbar__view .el-select-dropdown__item{
    height: auto;
    max-height: 274px;
    padding: 0;
    overflow: hidden;
    overflow-y: auto;
  }
  .el-select-dropdown__item.selected{
    font-weight: normal;
  }
  ul li >>>.el-tree .el-tree-node__content{
    height:auto;
    padding: 0 20px;
  }
  .el-tree-node__label{
    font-weight: normal;
  }
  .el-tree >>>.is-current .el-tree-node__label{
    color: #409EFF;
    font-weight: 700;
  }
  .el-tree >>>.is-current .el-tree-node__children .el-tree-node__label{
    color:#606266;
    font-weight: normal;
  }
</style>
