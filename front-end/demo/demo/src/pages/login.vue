<template>
  <div>
    <el-header>
    <div style="height:100px">
      <div id="header">
        <img id="markpng" src="../assets/mark.png" width="70px" height="70px">
        <p id="header_p"><span id="header_span">{{schoolName}}</span>教务管理系统</p>
      </div>
    </div>
    </el-header>
    <div style="margin: 180px 0;"></div>
    <el-input v-model="userAccount"  placeholder="请输入用户账号" class="user"></el-input>
    <div style="margin: 25px 0;"></div>
    <el-input v-model="userPassword"  placeholder="请输入用户密码" class="user"></el-input>
    <div style="margin: 25px 0;"></div>
    <el-select v-model="value" placeholder="请选择用户身份" class="roles" v-on:change="indexSelect($event)">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>

    <div style="margin: 35px 0;"></div>
    <el-button type="primary" icon="el-icon-sunny" @click="submit()">登录</el-button>
    <el-button type="primary" icon="el-icon-sunny">注册</el-button>
  </div>
</template>

<script>


  import header from '../components/Header.vue'
  import axios from 'axios'
  export default {
    name: "login",
    components:{
      'v-header':header
    },
    data() {
      return {
        schoolName: "北京科技大学",
        input1: '',
        input2: '',
        options: [{
          value: '1',
          label: '学生'
        }, {
          value: '2',
          label: '教师'
        }, {
          value: '3',
          label: '学院教务'
        }, {
          value: '4',
          label: '学校教务'
        }],
        value: '',

        userAccount: '',
        userPassword: '',
        kind:'1'

      };
    },
    methods: {
      indexSelect(e){

      },


      submit() {
        if(this.value=='4') {
          localStorage.setItem("adminId",1);
          console.log(localStorage.getItem("adminId"));
          this.$router.push("/university_administration_index");
        }
        else{

        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/checkAccount',
          params: {
            'userid':this.userAccount,
            'kind':this.value,
            'userpass':this.userPassword
          },
        }).then((res)=>{
            console.log(res.data.msg);
            if(res.data.msg=="密码错误") {
            }
            else{
              if(this.value=='1') {

                localStorage.setItem("studentId",res.data.stuId );
                localStorage.setItem("username",res.data.username);
                console.log(localStorage.getItem("studentId"));
                this.$router.push("/student_index");
              }
              if(this.value=='2') {

                localStorage.setItem("teacherId",res.data.teaId );
                localStorage.setItem("username",res.data.username);
                console.log(localStorage.getItem("teacherId"));
                this.$router.push("/teacher_index");
              }
              if(this.value=='3') {
                localStorage.setItem("adminId",res.data.adminId );
                console.log(localStorage.getItem("adminId"));
                this.$router.push("/school_administration_index");
              }
              if(this.value=='4') {
                localStorage.setItem("adminId",res.data.adminId );
                console.log(localStorage.getItem("adminId"));
                this.$router.push("/university_administration_index");
              }
            }
        })}
      },
    },
  }
</script>

<style scoped>


  #header{

    height: 60px;
    background-color: #547387;
    box-shadow: rgb(117, 117, 117) 0px 0px 0px 2px;
  }
  #markpng{
    position: absolute;
    left: 65px;
    top: 0px;
  }
  #header_p{
    text-align: center;
    left: 135px;
    top:-50px;
    font-size: 25px;
    font-weight: bold;
    color: white;
  }
  #header_span{
    font-size: 40px;
    font-weight: bold
  }
  .user
  {
    text-align: center;
    width: 220px;
  }
  .roles
  {
    text-align: center;
    width: 220px;
  }
  #markpng{
    position: absolute;
    left: 360px;
    top: 20px;
  }
</style>
