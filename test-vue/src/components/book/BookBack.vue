<template>
  <div>
    <div>
      <div align="right">
        <el-button type="primary"
                   icon="plus"
                   @click="showCreate">添加</el-button>
      </div>
    </div>
    <el-table :data="paginationData"
              border
              style="width: 100%">
      <el-table-column prop="id"
                       label="id"
                       :width="formLabelWidth
      "></el-table-column>
      <el-table-column prop="adminid"
                       label="管理员"
                       :width="formLabelWidth"
                       :formatter="adminFormatter"></el-table-column>
      <el-table-column prop="userid"
                       label="用户"
                       :width="formLabelWidth"
                       :formatter="userFormatter"></el-table-column>
      <el-table-column prop="bookid"
                       label="书籍"
                       :width="formLabelWidth"
                       :formatter="bookFormatter"></el-table-column>
      <el-table-column prop="number"
                       label="数量"
                       :width="formLabelWidth"></el-table-column>
      <el-table-column prop="date"
                       :formatter="dateFormat"
                       label="日期"
                       :width="formLabelWidth"></el-table-column>
      <el-table-column prop="reason"
                       label="原因"
                       :width="formLabelWidth"></el-table-column>
      <el-table-column align="right">
        <template slot="header"
                  slot-scope="scope">
          <el-input v-model="keyword"
                    size="mini"
                    placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button type="primary"
                     icon="edit"
                     @click="showUpdate(scope.$index,scope.row)">修改</el-button>
          <el-button type="danger"
                     icon="delete"
                     @click="showDelete(scope.$index,scope.row)">删除</el-button>
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
                   style="margin:10px 0">
    </el-pagination>
    <div>
      <el-dialog :title="textMap[dialogStatus]"
                 :visible.sync="dialogFormVisible">
        <el-form class="small-space"
                 :model="tempOrder"
                 label-position="left"
                 label-width="formLabelWidth"
                 style="width:300px;margin-left:50px;">
          <el-form-item label="id"
                        :label-width="formLabelWidth"
                        v-if="dialogStatus==='create'">
            <el-input v-model="tempOrder.id"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="id"
                        :label-width="formLabelWidth"
                        v-else>
            <el-input v-model="tempOrder.id"
                      auto-complete="off"
                      disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="管理员"
                        :label-width="formLabelWidth">
            <el-input v-model="tempOrder.adminid"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户"
                        :label-width="formLabelWidth">
            <el-input v-model="tempOrder.userid"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="书籍"
                        :label-width="formLabelWidth">
            <el-input v-model="tempOrder.bookid"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="数量"
                        :label-width="formLabelWidth">
            <el-input v-model="tempOrder.number"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="日期"
                        :label-width="formLabelWidth">
            <el-date-picker v-model="tempOrder.date"
                            type="date"></el-date-picker>

          </el-form-item>
          <el-form-item label="原因"
                        :label-width="formLabelWidth">
            <el-input v-model="tempOrder.reason"
                      auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer"
             class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button v-if="dialogStatus==='create'"
                     type="success"
                     @click="createBook">创建</el-button>
          <el-button type="primary"
                     v-else-if="dialogStatus==='update'"
                     @click="updateBook">修改</el-button>
          <el-button type="primary"
                     v-else-if="dialogStatus==='delete'"
                     @click="deleteBook">删除</el-button>
        </div>
      </el-dialog>
      <el-dialog title="错误提示"
                 :visible.sync="errorDialogVisible">
        <span>请修改id</span>
        <span slot="footer">
          <el-button type="primary"
                     @click="errorDialogVisible = false">确定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BookBack',
  data () {
    return {
      tableData: [],
      total: 0,
      currentPage: 1,
      paginationData: [],
      pagesize: 5,
      keyword: '',
      stashList: [],
      formLabelWidth: '110px',
      errorDialogVisible: false,
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        create: '新增订单',
        update: '编辑订单',
        delete: '删除订单'
      },
      tempOrder: {
        id: '',
        adminid: '',
        userid: '',
        bookid: '',
        number: '',
        date: '',
        reason: ''
      },
      userOptions: [],
      bookOptions: []
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
            return item.userid.includes(newValue)
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
    reload () {
      window.location.reload()
    },
    dateFormat: function (row, column) {
      let date = row[column.property]
      if (date === undefined) {
        return ''
      }
      let moment = require('moment')
      return moment(date).format('YYYY-MM-DD')
    },
    getTable () {
      this.$http.get('/backorder/backorderAll').then(response => {
        this.tableData = response.data
        this.handleCurrentChange(this.currentPage)
        this.stashList = this.tableData
        console.log(response.data)
      })
      this.$http.get('/user/userAll').then(response => {
        this.userOptions = response.data
      })
      this.$http.get('/book/bookAll').then(response => {
        this.bookOptions = response.data
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
    showCreate () {
      // 显示新增弹出框
      this.tempOrder.id = ''
      this.tempOrder.adminid = ''
      this.tempOrder.userid = ''
      this.tempOrder.bookid = ''
      this.tempOrder.number = ''
      this.tempOrder.date = ''
      this.tempOrder.reason = ''
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    showUpdate (index, row) {
      this.tempOrder = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    showDelete (index, row) {
      this.tempOrder = Object.assign({}, row)
      this.dialogStatus = 'delete'
      this.dialogFormVisible = true
    },
    createBook () {
      this.$http
        .post('/backorder/backorder', {
          id: this.tempOrder.id,
          adminid: this.tempOrder.adminid,
          userid: this.tempOrder.userid,
          bookid: this.tempOrder.bookid,
          number: this.tempOrder.number,
          date: this.tempOrder.date,
          reason: this.tempOrder.reason
        })
        .then(response => {
          if (response.data === true) {
            console.log('response', response)
            this.addFormVisible = false
            this.reload()
          } else {
            this.errorDialogVisible = true
            console.log('数据有误重新输入')
          }
        })
    },
    updateBook () {
      this.$http
        .put('/backorder/backorder', {
          id: this.tempOrder.id,
          adminid: this.tempOrder.adminid,
          userid: this.tempOrder.userid,
          bookid: this.tempOrder.bookid,
          number: this.tempOrder.number,
          date: this.tempOrder.date,
          reason: this.tempOrder.reason
        })
        .then(response => {
          console.log('response', response)
          this.editFormVisible = false
          this.reload()
        })
    },
    deleteBook () {
      this.$http
        .post('/backorder/backorderDelete', {
          id: this.tempOrder.id
        })
        .then(response => {
          this.deleteFormVisible = false
          console.log('response', response)
          this.reload()
        })
    },
    adminFormatter (row, column) {
      let tempname = ''
      this.userOptions.forEach(function (item, index) {
        if (row.adminid === item.id) {
          tempname = item.nickname
        }
      })
      return tempname
    },
    userFormatter (row, column) {
      let tempname = ''
      this.userOptions.forEach(function (item, index) {
        if (row.userid === item.id) {
          tempname = item.nickname
        }
      })
      return tempname
    },
    bookFormatter (row, column) {
      let tempname = ''
      this.bookOptions.forEach(function (item, index) {
        if (row.bookid === item.id) {
          tempname = item.bookname
        }
      })
      return tempname
    }
  }
}
</script>

<style scoped>
</style>
