<template>
  <div>
    <div>
      <el-table :data="paginationData"
                border
                style="width: 100%">
        <el-table-column prop="id"
                         label="id"
                         width="120
      "></el-table-column>
        <el-table-column prop="username"
                         label="用户名"
                         width="120"></el-table-column>
        <el-table-column prop="nickname"
                         label="昵称"
                         width="120"></el-table-column>

        <el-table-column prop="phonenumber"
                         label="手机号码"
                         width="120"></el-table-column>
        <el-table-column prop="stuclass"
                         label="班级"
                         width="120"
                         :formatter="classFormatter"></el-table-column>
        <el-table-column prop="position"
                         label="职位"
                         width="120"></el-table-column>
        <el-table-column align="right">
          <template slot="header"
                    slot-scope="scope">
            <el-input v-model="keyword"
                      size="mini"
                      placeholder="输入关键字搜索" />
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[5, 10, 15, 20]"
                     :page-size="pagesize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="total"
                     style="margin: 10px 0">
      </el-pagination>
    </div>

  </div>
</template>

<script>
export default {
  name: 'User',
  data () {
    return {
      tableData: [],
      total: 0,
      currentPage: 1,
      paginationData: [],
      pagesize: 5,
      keyword: '',
      stashList: [],
      classOptions: []
    }
  },
  created () {
    this.getTable()
    this.total = this.tableData.length
  },
  watch: {
    keyword: {
      handler (newValue, oldValue) {
        let self = this
        if (newValue) {
          // 这里要从暂存的所有数据中过滤 放到展示的数组中
          self.tableData = self.stashList.filter(item => {
            return item.nickname.includes(newValue)
          })
        } else {
          self.tableData = self.stashList
        }
        self.tableList()
      },
      deep: true
    }
  },
  methods: {
    getTable () {
      this.$http.get('/user/userAll').then(response => {
        this.tableData = response.data
        this.handleCurrentChange(this.currentPage)
        this.stashList = this.tableData
        console.log(response.data)
      })
      this.$http.get('/department/departmentAll').then(response => {
        this.classOptions = response.data
      })
    },
    handleSizeChange (val) {
      this.pagesize = val
      this.tableList()
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.tableList()
    },
    tableList () {
      this.paginationData = [] // 分页数组  tableData 所有的数据
      for (
        var j = this.pagesize * (this.currentPage - 1);
        j < this.pagesize * this.currentPage;
        j++
      ) {
        if (this.tableData[j]) {
          this.paginationData.push(this.tableData[j])
        }
      }
      this.total = this.tableData.length
    },
    classFormatter (row, column) {
      let tempname = ''
      this.classOptions.forEach(function (item, index) {
        if (row.stuclass === item.stuclass) {
          tempname = item.classname
        }
      })
      return tempname
    }
  }
}
</script>

<style scoped></style>
