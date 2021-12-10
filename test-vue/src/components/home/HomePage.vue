<template>
  <div>
    登陆成功
    <div>
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="grid-content bg-purple card-panel">
            <div class="card-panel-icon-wrapper">
              <div class="card-panel-icon"><i class="el-icon-user"></i></div>
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
              <div class="card-panel-icon"><i class="el-icon-user"></i></div>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">图书库存</div>
              <div class="card-panel-num">{{summary[0]. book}}</div>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="grid-content bg-purple card-panel">
            <div class="card-panel-icon-wrapper">
              <div class="card-panel-icon"><i class="el-icon-user"></i></div>
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
              <div class="card-panel-icon"><i class="el-icon-user"></i></div>
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">总订书量</div>
              <div class="card-panel-num">{{summary[0].booktotal}}</div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage',
  data () {
    return {
      summary: [{ user: '111', book: '222', order: '333', booktotal: '444' }]
    }
  },
  created () {},
  methods: {}
}
</script>

<style scoped>
.bg-purple {
  background: #d3dce6;
}
.card-panel-icon-wrapper {
  float: left;
}
.card-panel-icon {
  float: left;
  font-size: 48px;
}
.card-panel-description {
  float: left;
}
</style>
