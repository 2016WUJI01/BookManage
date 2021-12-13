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
              style="width: 100%"
              header-row-class-name="tableHead">
      <el-table-column prop="id"
                       label="id"
                       width="formLabelWidth
      "></el-table-column>
      <el-table-column prop="bookname"
                       label="书名"
                       width="formLabelWidth"></el-table-column>
      <el-table-column prop="supplier"
                       label="供应商"
                       width="formLabelWidth"></el-table-column>
      <el-table-column prop="price"
                       label="价格"
                       width="formLabelWidth"></el-table-column>
      <el-table-column prop="reserve"
                       label="库存"
                       width="formLabelWidth"></el-table-column>
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
                 :model="tempBook"
                 label-position="left"
                 label-width="formLabelWidth"
                 style="width:300px;margin-left:50px;">
          <el-form-item label="id"
                        :label-width="formLabelWidth"
                        v-if="dialogStatus==='create'">
            <el-input v-model="tempBook.id"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="id"
                        :label-width="formLabelWidth"
                        v-else>
            <el-input v-model="tempBook.id"
                      auto-complete="off"
                      disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="书名"
                        :label-width="formLabelWidth">
            <el-input v-model="tempBook.bookname"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="供应商"
                        :label-width="formLabelWidth">
            <el-input v-model="tempBook.supplier"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格"
                        :label-width="formLabelWidth">
            <el-input v-model="tempBook.price"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="库存"
                        :label-width="formLabelWidth">
            <el-input v-model="tempBook.reserve"
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
  name: 'Book',
  data () {
    return {
      tableData: [],
      total: 0,
      currentPage: 1,
      paginationData: [],
      pagesize: 5,
      keyword: '',
      stashList: [],
      formLabelWidth: '80px',
      errorDialogVisible: false,
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        create: '新增书籍',
        update: '编辑书籍',
        delete: '删除书籍'
      },
      tempBook: {
        id: '',
        bookname: '',
        supplier: '',
        price: '',
        reserve: ''
      }
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
            return item.bookname.includes(newValue)
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
    getTable () {
      this.$http.get('/book/bookAll').then(response => {
        this.tableData = response.data
        this.handleCurrentChange(this.currentPage)
        this.stashList = this.tableData
        console.log(response.data)
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
      this.tempBook.id = ''
      this.tempBook.bookname = ''
      this.tempBook.supplier = ''
      this.tempBook.price = ''
      this.tempBook.reserve = ''
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    showUpdate (index, row) {
      this.tempBook = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    showDelete (index, row) {
      this.tempBook = Object.assign({}, row)
      this.dialogStatus = 'delete'
      this.dialogFormVisible = true
    },
    createBook () {
      this.$http
        .post('/book/book', {
          id: this.tempBook.id,
          bookname: this.tempBook.bookname,
          supplier: this.tempBook.supplier,
          price: this.tempBook.price,
          reserve: this.tempBook.reserve
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
        .put('/book/book', {
          id: this.tempBook.id,
          bookname: this.tempBook.bookname,
          supplier: this.tempBook.supplier,
          price: this.tempBook.price,
          reserve: this.tempBook.reserve
        })
        .then(response => {
          console.log('response', response)
          this.editFormVisible = false
          this.reload()
        })
    },
    deleteBook () {
      this.$http
        .post('/book/bookDelete', {
          id: this.tempBook.id
        })
        .then(response => {
          this.deleteFormVisible = false
          console.log('response', response)
          this.reload()
        })
    }
  }
}
</script>

<style scoped>
/* .tableHead {
  font-weight: bold;
} */
</style>
