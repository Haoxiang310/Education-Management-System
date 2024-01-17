<template>
  <div id="back_ground" >
    <h1 class="title">{{msg}}</h1>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1" @click="class_manage()">停调课申请</el-menu-item>
      <el-menu-item index="2" @click="class_table()">查看课表</el-menu-item>
      <el-menu-item index="3" @click="mark_manage()">成绩登记</el-menu-item>
      <el-menu-item index="4" @click="student_manage()">选课名单</el-menu-item>
      <el-menu-item index="5" class="back" @click="back()">退出</el-menu-item>
    </el-menu>
    <router-view/>


  </div>
</template>
<script>
  // import __ from '__' // __是需要手动引入的文件
  export default {
    name: '',
    components: {},
    data() {
      return {msg: '教师界面',
        activeIndex: '1'
      }
    },
    methods: {
      back()
      {
        this.$router.push("/login")
      },
      class_table()
      {
        this.$router.push("/teacher_index/teacher_schedule")

      },
      class_manage()
      {
        this.$router.push("/teacher_index/teacher_class_manage")

      },
      mark_manage()
      {
        this.$router.push("/teacher_index/mark_class")
      },
      student_manage()
      {
        this.$router.push("/teacher_index/teacher_student_class")
      }
    }
  }
</script>
<style scoped>
  #back_ground {
    text-align: center;
    /*background: url("../../assets/student_homepage.jpg");*/
    background-size: 100% 100%;
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }
  .title
  {
    font-size: 50px;
    font-family:楷体;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 10px;
    margin-bottom: 15px;
    white-space: normal;
    color: rgb(110, 110, 110);
    letter-spacing: 30px;
  }
  .back{
    left: 850px;
  }
</style>
