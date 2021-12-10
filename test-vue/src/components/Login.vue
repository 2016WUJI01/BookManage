<template>
  <div>
    账号：<input type="text"
           v-model="loginForm.username"
           placeholder="请输入账号" /><br /><br />
    密码：<input type="text"
           v-model="loginForm.password"
           placeholder="请输入密码" />
    <br /><br />
    <button v-on:click="login">登陆账号</button>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      this.$http.post('/login', this.loginForm).then(res => {
        if (res.data === true) {
          this.$router.push({ path: '/index' })
        } else {
          this.$alert('账号或密码错误', '标题', {
            confirmButtonText: '确定'
          })
        }
      })
    }
  }
}
</script>

<style>
</style>
