<template>
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
        label="性别"
        :label-width="formLabelWidth"
      >
        <el-input
          v-model="addform.sex"
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
</template>

<script>
export default {
  name: 'User',
  data () {
    return {
      addFormVisible: false,
      addform: {
        id: '',
        username: '',
        password: '',
        nickname: '',
        sex: '',
        phonenumber: '',
        stuclass: '',
        position: ''
      },
      formLabelWidth: '120px'
    }
  },
  created () {},
  watch: {},
  methods: {}
}
</script>

<style>
</style>
