<template>
<div>登陆成功
  <div><el-table :data="tableData" style="width: 100%"
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
            ></el-table-column></el-table></div>
</div>
</template>

<script>
export default {
  name: 'HomePage',
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

<style scoped>

</style>
