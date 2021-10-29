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
          <el-menu-item index="1"
            ><template slot="title">
              <i class="el-icon-location"></i> <span>首页</span></template
            >
          </el-menu-item>
          <el-submenu index="2"
            ><template slot="title">
              <i class="el-icon-location"></i> <span>信息查询</span></template
            >
            <el-menu-item-group
              ><template slot="title">用户信息查询</template>
              <el-menu-item index="2-1">学生信息</el-menu-item>
              <el-menu-item index="2-2">老师信息</el-menu-item></el-menu-item-group
            >
            <el-menu-item-group
              ><template slot="title">所需教材汇总</template>
              <el-menu-item index="2-3">学生订购</el-menu-item>
              <el-menu-item index="2-4">老师订购</el-menu-item></el-menu-item-group
            >
          </el-submenu>
          <el-menu-item index="3"
            ><template slot="title">
              <i class="el-icon-location"></i> <span>系统管理</span></template
            >
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container
        ><el-header>Header</el-header>
        <el-main><router-view></router-view></el-main
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
  created () {},
  methods: {
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
