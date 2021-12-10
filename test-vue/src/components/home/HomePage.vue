<template>
  <div>
    <div>
      <el-row :gutter="40"
              class="panel-group">
        <el-col :span="6"
                class="card-panel-col">
          <div class="grid-content bg-purple card-panel">
            <div class="card-panel-icon-wrapper">
              <div class="card-panel-icon"><i class="el-icon-user-solid"></i></div>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">用户数</div>
              <div class="card-panel-num">{{summary[0].user}}</div>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple card-panel">
            <div class="card-panel-icon-wrapper">
              <div class="card-panel-icon"><i class="el-icon-reading"></i></div>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">图书库存</div>
              <div class="card-panel-num">{{summary[0].book}}</div>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple card-panel">
            <div class="card-panel-icon-wrapper">
              <div class="card-panel-icon"><i class="el-icon-warning-outline"></i></div>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">未完成订单数</div>
              <div class="card-panel-num">{{summary[0].order}}</div>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple card-panel">
            <div class="card-panel-icon-wrapper">
              <div class="card-panel-icon"><i class="el-icon-collection"></i></div>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">总订书量</div>
              <div class="card-panel-num">{{summary[0].booktotal}}</div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-table :data="paginationData"
                border
                style="width: 100%">
        <el-table-column prop="id"
                         label="id"></el-table-column>
        <el-table-column prop="userid"
                         label="用户"
                         :formatter="userFormatter"></el-table-column>
        <el-table-column prop="bookid"
                         label="书籍"
                         :formatter="bookFormatter"></el-table-column>
        <el-table-column prop="number"
                         label="数量"></el-table-column>
        <el-table-column prop="situation"
                         label="情况"><template slot-scope="scope">
            <el-tag :type="scope.row.situation === '完成' ? 'primary' : 'success'"
                    disable-transitions>{{scope.row.situation}}</el-tag>
          </template></el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[5, 10, 15, 20]"
                     :page-size="pagesize"
                     layout="total, sizes, prev, pager, next"
                     :total="total"
                     style="margin:10px 0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data () {
    return {
      summary: [{ user: '', book: '', order: '', booktotal: '' }],
      tableData: [],
      total: 0,
      currentPage: 1,
      paginationData: [],
      pagesize: 5,
      userOptions: [],
      bookOptions: []
    }
  },
  created () {
    this.getTable()
    this.total = this.tableData.length
  },
  methods: {
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
    getTable () {
      this.$http.get('/bookorder/bookorderAll').then(response => {
        this.tableData = response.data
        this.handleCurrentChange(this.currentPage)
        this.stashList = this.tableData
        console.log(response.data)
        let ordercount = 0
        let bookcount = 0
        for (let i = 0; i < response.data.length; i++) {
          if (response.data[i].situation === '未完成') {
            ordercount++
          }
          bookcount += response.data[i].number
        }
        this.summary[0].order = ordercount
        this.summary[0].booktotal = bookcount
      })
      this.$http.get('/user/userAll').then(response => {
        this.userOptions = response.data
        this.summary[0].user = this.userOptions.length
      })
      this.$http.get('/book/bookAll').then(response => {
        this.bookOptions = response.data
        let count = 0
        for (let i = 0; i < response.data.length; i++) {
          count += response.data[i].reserve
        }
        this.summary[0].book = count
      })
    }
  }
}
</script>

<style scoped>
.bg-purple {
  background: #d3dce6;
}

.panel-group {
  margin-top: 20px;
}
.panel-group .card-panel-col {
  margin-bottom: 20px;
}
.panel-group .card-panel-col .card-panel {
  height: 120px;
  font-size: 12px;
  position: relative;
}
.card-panel-icon-wrapper {
  float: left;
  height: 110px;
  padding: 30px 30px 0 30px;
}
.card-panel-icon-wrapper .card-panel-icon {
  font-size: 50px;
}
.card-panel-description {
  font-weight: bold;
  justify-content: center;
  align-items: center;
}
.card-panel-description .card-panel-text {
  font-size: 20px;
}
.card-panel-description .card-panel-num {
  font-size: 20px;
}
</style>
