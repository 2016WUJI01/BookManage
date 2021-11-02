<template>
  <div>
    <el-table :data="tableData" style="width: 100%"
      ><el-table-column prop="id" label="id" width="180"></el-table-column>
      <el-table-column
        prop="username"
        label="姓名"
        width="180"
      ></el-table-column
      ><el-table-column
        prop="password"
        label="密码"
        width="180"
      ></el-table-column
      ><el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="userName"
            size="mini"
            placeholder="输入关键字搜索"
          />
          <el-button size="mini" @click="search(userName)">搜索</el-button>
        </template>
        <template slot-scope="scope"
          ><el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          ></template
        >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'User',
  data () {
    return {
      tableData: [],
      userName: ''
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
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
    },
    search () {
      if (this.userName.length > 0) {
        this.$http.get('/user/findByUsername', this.userName).then((res) => {
          this.tableData = res.data
          console.log(res.data)
        })
      }
    }
  }
}
</script>

<style scoped>
</style>
