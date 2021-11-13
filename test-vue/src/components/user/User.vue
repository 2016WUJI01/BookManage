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
              type="primary"
              @click="showUpdate(scope.$index)"
              v-permission="'user:userdate'"
            >修改</el-button>
            <el-button
              size="mini"
              type="danger"
              v-if="scope.row.id !== id"
              @click="removeUser(scope.$indexx)"
              v-permission="'user:userdate'"
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
      :title="textMap[dialogStatus]"
      :visible.sync="dialogFormVisible"
    >
      <el-form
        class="small-space"
        :model="tempUser"
        label-position="left"
        label-width="80px"
        style='width: 300px; margin-left:50px;'
      >
        <el-form-item
          label="Id"
          required
          v-if="dialogStatus==='create'"
        >
          <el-input
            type="text"
            v-model="tempUser.id"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="用户名"
          required
          v-if="dialogStatus==='create'"
        >
          <el-input
            type="text"
            v-model="tempUser.username"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          v-if="dialogStatus==='create'"
          required
        >
          <el-input
            type="password"
            v-model="tempUser.password"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="新密码"
          v-else
        >
          <el-input
            type="password"
            v-model="tempUser.password"
            placeholder="不填则表示不修改"
          >
          </el-input>
        </el-form-item>

        <el-form-item
          label="昵称"
          required
        >
          <el-input
            type="text"
            v-model="tempUser.nickname"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="手机号码"
          required
        >
          <el-input
            type="text"
            v-model="tempUser.phonenumber"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="班级"
          required
        >
          <el-input
            type="text"
            v-model="tempUser.stuclass"
          >
          </el-input>
        </el-form-item>
        <el-form-item
          label="职位"
          required
        >
          <el-input
            type="text"
            v-model="tempUser.position"
          >
          </el-input>
        </el-form-item>
      </el-form>
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button
          v-if="dialogStatus==='create'"
          type="success"
          @click="createUser"
        >创 建</el-button>
        <el-button
          type="primary"
          v-else
          @click="updateUser"
        >修 改</el-button>
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
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        create: '新增用户',
        update: '编辑用户'
      },
      tempUser: {
        id: '',
        username: '',
        password: '',
        nickname: '',
        phonenumber: '',
        stuclass: '',
        position: ''
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
    },
    showCreate () {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    showUpdate ($index) {
      let user = this.list[$index]
      this.tempUser.id = user.id
      this.tempUser.username = user.username
      this.tempUser.password = user.password
      this.tempUser.nickname = user.nickname
      this.tempUser.phonenumber = user.phonenumber
      this.tempUser.stuclass = user.stuclass
      this.tempUser.position = user.position
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    validate (isCreate) {
      let u = this.tempUser
      if (isCreate && u.username.trim().length === 0) {
        this.$message.warning('请填写用户名')
        return false
      }
      if (isCreate && u.password.length === 0) {
        this.$message.warning('请填写密码')
        return false
      }
      if (u.nickname.trim().length === 0) {
        this.$message.warning('请填写昵称')
        return false
      }
      if (u.roleIds.length === 0) {
        this.$message.warning('请选择角色')
        return false
      }
      return true
    },
    createUser () {
      if (!this.validate(true)) return
      // 添加新用户
      this.api({
        url: '/user/addUser',
        method: 'post',
        data: this.tempUser
      }).then(() => {
        this.$message.success('添加成功')
        this.getList()
        this.dialogFormVisible = false
      })
    },
    updateUser () {
      if (!this.validate(false)) return
      // 修改用户信息
      let _vue = this
      this.api({
        url: '/user/updateUser',
        method: 'post',
        data: this.tempUser
      }).then(() => {
        this.$message.success('修改成功')
        this.dialogFormVisible = false
        _vue.getList()
      })
    },
    removeUser ($index) {
      let _vue = this
      this.$confirm('确定删除此用户?', '提示', {
        confirmButtonText: '确定',
        showCancelButton: false,
        type: 'warning'
      }).then(() => {
        let user = _vue.list[$index]
        user.deleteStatus = '2'
        _vue
          .api({
            url: '/user/updateUser',
            method: 'post',
            data: user
          })
          .then(() => {
            this.$message.success('删除成功')
            _vue.getList()
          })
          .catch(() => {
            _vue.$message.error('删除失败')
          })
      })
    }
  }
}
</script>

<style scoped></style>
