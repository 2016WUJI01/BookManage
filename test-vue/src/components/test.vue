<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-submenu index="1"
            ><template slot="title">
              <i class="el-icon-location"></i> <span>导航一</span></template
            >
            <el-menu-item-group
              ><template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item></el-menu-item-group
            >
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container
        ><el-header>Header</el-header>
        <el-main
          ><el-table :data="tableData" style="width: 100%"
            ><el-table-column
              prop="id"
              label="id"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="username"
              label="姓名"
              width="180"
            ></el-table-column
            ><el-table-column
              prop="password"
              label="密码"
              width="180"
            ></el-table-column></el-table></el-main
        ><el-footer>Footer</el-footer></el-container
      >
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'test',
  data () {
    return {
      tableData: []
    }
  },
  created () {
    this.getTable()
  },
  methods: {
    getTable () {
      this.$http.get('/user/userAll').then((response) => {
        this.tableData = response.data
        console.log(response.data)
      })
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
body > .el-container {
  margin-bottom: 40px;
}
</style>
