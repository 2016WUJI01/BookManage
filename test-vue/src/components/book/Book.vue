<template>
  <div>
    <div>
      <div align="right">
        <el-button @click="addFormVisible = true">新增书籍
        </el-button>
      </div>
    </div>
    <el-table :data="paginationData"
              border
              style="width: 100%">
      <el-table-column prop="id"
                       label="id"
                       width="180
      "></el-table-column>
      <el-table-column prop="bookname"
                       label="书名"
                       width="180"></el-table-column>
      <el-table-column prop="supplier"
                       label="供应商"
                       width="180"></el-table-column>
      <el-table-column prop="price"
                       label="价格"
                       width="180"></el-table-column>

      <el-table-column prop="reserve"
                       label="库存"
                       width="180"></el-table-column>

      <el-table-column align="right">
        <template slot="header"
                  slot-scope="scope">
          <el-input v-model="keyword"
                    size="mini"
                    placeholder="输入关键字搜索" />
        </template>
        <template slot-scope="scope">
          <el-button size="mini"
                     @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
                     type="danger"
                     @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
      <el-dialog title="添加书籍"
                 :visible.sync="addFormVisible">
        <el-form :model="addform">
          <el-form-item></el-form-item>
          <el-form-item label="id"
                        :label-width="formLabelWidth">
            <el-input v-model="addform.id"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="书名"
                        :label-width="formLabelWidth">
            <el-input v-model="addform.bookname"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="供应商"
                        :label-width="formLabelWidth">
            <el-input v-model="addform.supplier"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格"
                        :label-width="formLabelWidth">
            <el-input v-model="addform.price"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="库存"
                        :label-width="formLabelWidth">
            <el-input v-model="addform.reserve"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-button @click="addFormVisible = false">取消</el-button>
          <el-button type="primary"
                     @click="confirmAdd">确定</el-button>
        </el-form>
      </el-dialog>
      <el-dialog title="修改书籍"
                 :visible.sync="editFormVisible">
        <el-form :model="editform">
          <el-form-item></el-form-item>
          <el-form-item label="id"
                        :label-width="formLabelWidth">
            <el-input v-model="editform.id"
                      auto-complete="off"
                      disabled="disabled"></el-input>
          </el-form-item>
          <el-form-item label="书名"
                        :label-width="formLabelWidth">
            <el-input v-model="editform.bookname"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="供应商"
                        :label-width="formLabelWidth">
            <el-input v-model="editform.supplier"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格"
                        :label-width="formLabelWidth">
            <el-input v-model="editform.price"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="库存"
                        :label-width="formLabelWidth">
            <el-input v-model="editform.reserve"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-button @click="editFormVisible = false">取消</el-button>
          <el-button type="primary"
                     @click="confirmEdit">确定</el-button>
        </el-form>
      </el-dialog>
      <el-dialog title="删除书籍"
                 :visible.sync="deleteFormVisible">
        <el-form :model="deleteform">
          <el-form-item></el-form-item>
          <el-form-item label="id"
                        :label-width="formLabelWidth">
            <el-input v-model="deleteform.id"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="书名"
                        :label-width="formLabelWidth">
            <el-input v-model="deleteform.bookname"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="供应商"
                        :label-width="formLabelWidth">
            <el-input v-model="deleteform.supplier"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格"
                        :label-width="formLabelWidth">
            <el-input v-model="deleteform.price"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="库存"
                        :label-width="formLabelWidth">
            <el-input v-model="deleteform.reserve"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-button @click="deleteFormVisible = false">取消</el-button>
          <el-button type="primary"
                     @click="confirmDelete">确定</el-button>
        </el-form>
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
      addFormVisible: false,
      addform: {
        id: '',
        bookname: '',
        supplier: '',
        price: '',
        reserve: ''
      },
      editFormVisible: false,
      editform: {
        id: '',
        bookname: '',
        supplier: '',
        price: '',
        reserve: ''
      },
      deleteFormVisible: false,
      deleteform: {
        id: '',
        bookname: '',
        supplier: '',
        price: '',
        reserve: ''
      },
      formLabelWidth: '100px'
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
    confirmAdd () {
      this.$http
        .post('/book/book', {
          id: this.addform.id,
          bookname: this.addform.bookname,
          supplier: this.addform.supplier,
          price: this.addform.price,
          reserve: this.addform.reserve
        })
        .then(response => {
          console.log('response', response)
          this.addFormVisible = false
          this.reload()
        })
    },
    handleEdit (index, row) {
      this.editFormVisible = true
      this.editform = Object.assign({}, row)
    },
    confirmEdit () {
      this.$http
        .put('/book/book', {
          id: this.editform.id,
          bookname: this.editform.bookname,
          supplier: this.editform.supplier,
          price: this.editform.price,
          reserve: this.editform.reserve
        })
        .then(response => {
          console.log('response', response)
          this.editFormVisible = false
          this.reload()
        })
    },
    handleDelete (index, row) {
      this.deleteFormVisible = true
      this.deleteform = Object.assign({}, row)
    },
    confirmDelete () {
      this.$http
        .post('/book/bookDelete', {
          id: this.deleteform.id
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
</style>
