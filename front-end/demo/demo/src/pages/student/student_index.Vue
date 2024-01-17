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
      <el-menu-item index="1" @click="class_manage()">选课管理</el-menu-item>
      <el-menu-item index="2" @click="class_table()">查看课表</el-menu-item>
      <el-menu-item index="3" @click="mark_manage()">成绩管理</el-menu-item>
      <el-menu-item index="4" class="back" @click="back()">退出</el-menu-item>
    </el-menu>
<router-view/>


  </div>
</template>
<script>
  // import __ from '__' // __是需要手动引入的文件
  export default {
    name: '',
    components: {},
    created()
    {
      this.get_data();
    },
    data() {
      return {msg: '学生界面',
              activeIndex: '1',
              temp1:'',
              temp2:''
      }
    },
    methods: {
      get_data()
      {
           this.temp1=this.$route.query.username;
           this.temp2=this.$route.query.stu_id
      },
      back()
      {
        this.$router.push("/login")

      },
      class_table()
      {
        this.$router.push("/student_index/student_schedule");
      },
      class_manage()
      {
        this.$router.push("/student_index/student_class_manage")

      },
      mark_manage()
      {
        this.$router.push("/student_index/mark_manage")
      }
    }
  }
</script>
<style scoped>
  #back_ground {
    text-align: center;

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
    font-size: 40px;
    font-family: 楷体;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 10px;
    margin-bottom: 15px;
    white-space: normal;
    color: rgba(67, 129, 112, 0.98);
    letter-spacing: 30px;
  }
  .back{
    left: 850px;
  }
</style>
