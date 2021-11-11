<template>
  <div>
    <div style="float: right">
      <el-button @click="showCreate">新增</el-button>
    </div>

    <div>
      <el-table
        :data="paginationData"
        border
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="id"
          width="120
      "
        ></el-table-column>
        <el-table-column
          prop="username"
          label="姓名"
          width="120"
        ></el-table-column>
        <el-table-column
          prop="nickname"
          label="用户名"
          width="120"
        ></el-table-column>

        <el-table-column
          prop="phonenumber"
          label="手机号码"
          width="120"
        ></el-table-column>
        <el-table-column
          prop="stuclass"
          label="班级"
          width="120"
        ></el-table-column>
        <el-table-column
          prop="position"
          label="职位"
          width="120"
        ></el-table-column>
        <el-table-column align="right">
          <template
            slot="header"
            slot-scope="scope"
          >
            <el-input
              v-model="keyword"
              size="mini"
              placeholder="输入关键字搜索"
            />
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        style="margin: 10px 0"
      >
      </el-pagination>
    </div>
    <el-dialog
      title="新增用户"
      :visible.sync="addFormVisible"
    >
      <el-form :model="addform">
        <el-form-item
          label="id"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addform.id"
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="姓名"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addform.username"
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="用户名"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addform.nickname"
            auto-complete="off"
          ></el-input>
        </el-form-item>

        <el-form-item
          label="手机号码"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addform.phonenumber"
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="班级"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addform.stuclass"
            auto-complete="off"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="职位"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="addform.position"
            auto-complete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="addFormVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addFormVisible = false"
        >确 定</el-button>
      </div>
    </el-dialog>
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
      addFormVisible: false,
      addform: {
        id: '',
        username: '',
        password: '',
        nickname: '',
        phonenumber: '',
        stuclass: '',
        position: ''
      },
      formLabelWidth: '120px'
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
          self.tableData = self.stashList.filter((item) => {
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
      this.$http.get('/user/userAll').then((response) => {
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
    }
  }
}
</script>

<style scoped></style>
