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
        index="/school_administration_index/school_administration_class_adjustment_check"
        @click="class_adjustment()">停调课审批</el-menu-item>
      <el-menu-item
        index="/school_administration_index/school_administration_courseInfo"
        @click="grade_manage()">成绩管理</el-menu-item>
      <el-menu-item
        index="/school_administration_index/school_administration_class_manage"
        @click="class_manage()">排课管理</el-menu-item>
      <el-menu-item index="/login" class="back" @click="back()">退出</el-menu-item>
    </el-menu>
    <router-view/>


  </div>
</template>
<script>

  export default {
    name: '',
    components: {},
    data() {
      return {msg: '学院教务界面',
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
      grade_manage()
      {
        this.$router.push("/school_administration_index/school_administration_courseInfo")

      },
      class_manage()
      {
        this.$router.push("/school_administration_index/school_administration_class_manage")

      },
      class_adjustment()
      {
        this.$router.push("/school_administration_index/school_administration_class_adjustment_check")

      },


    },
    created() {

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
    left: 850px;
  }
</style>

