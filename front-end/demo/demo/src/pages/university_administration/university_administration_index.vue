<template>
  <div id="back_ground" >
    <h1 class="title">{{msg}}</h1>
  <el-button
    class="return"
    icon="el-icon-caret-left"
    @click="backHistory">返回上一页</el-button>
    <el-menu
      :default-active="$route.path"
      @select="handleSelect"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item
        index="/university_administration_index/university_administration_class_adjustment_check"
        @click="class_change()">停调课审批</el-menu-item>
      <el-menu-item
        index="/university_administration_index/university_administration_courseInfo"
        @click="gradeCheck()">成绩查看</el-menu-item>
      <el-menu-item
        index="/university_administration_index/university_administration_class_manage"
        @click="class_manage()">排课管理</el-menu-item>
      <el-menu-item
        index="/university_administration_index/university_administration_training_program"
        @click="program()">培养方案管理</el-menu-item>


      <el-menu-item
        index="/login"
        class="back"
        @click="back()">退出</el-menu-item>
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
      return {msg: '学校教务界面',
        activeIndex: '1'
      }
    },
    methods: {
      backHistory(){
        this.$router.go(-1);//返回上一层
      },
      handleSelect(data){
        this.$router.push({ path: data })

      },
      back()
      {
        this.$router.push("/login")
      },
      gradeCheck(){
        this.$router.push("/university_administration_index/university_administration_courseInfo")
      },
      program()
      {
        this.$router.push("/university_administration_index/university_administration_training_program")

      },
      class_change()
      {
        this.$router.push("/university_administration_index/university_administration_class_adjustment_check")

      },
      class_manage()
      {
        this.$router.push("/university_administration_index/university_administration_class_manage")

      },
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
    font-family: 楷体;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 10px;
    margin-bottom: 15px;
    white-space: normal;
    color: rgba(126, 129, 124, 0.98);
    letter-spacing: 30px;
  }
  .back{
    left: 700px;
  }
</style>
