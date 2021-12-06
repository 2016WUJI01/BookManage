<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-menu router
                 default-active="2"
                 class="el-menu-vertical-demo"
                 @open="handleOpen"
                 @close="handleClose"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b">
          <el-menu-item index="/index"><template slot="title">
              <i class="el-icon-location"></i> <span>首页</span></template>
          </el-menu-item>
          <el-submenu index="2"><template slot="title">
              <i class="el-icon-location"></i> <span>教材管理</span></template>
            <el-menu-item-group><template slot="title"></template>
              <el-menu-item index="/book">查询教材</el-menu-item>
              <el-menu-item index="/bookorder">订购教材</el-menu-item>
              <el-menu-item index="/bookback">退教材</el-menu-item>
            </el-menu-item-group>

          </el-submenu>
          <el-menu-item index="/user"><template slot="title">
              <i class="el-icon-location"></i> <span>用户管理</span></template>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
        <el-footer>Footer</el-footer>
      </el-container>
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
  line-height: 60px;
}
body > .el-container {
  margin-bottom: 40px;
}
</style>
