<template>
  <div>

    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-row class="tac">
          <el-col >

            <el-menu
              :default-active="$route.path"
              @select="handleSelect">
              <el-menu-item
                index="/university_administration_index/university_administration_courseInfo"
                @click="GotoClassGrade()">
                <i class="el-icon-menu"></i>
                <span slot="title" >课程成绩</span>
              </el-menu-item>
              <el-menu-item
                index="/university_administration_index/university_administration_studentInfo"
                @click="GotoStudentGrade()">
                <i class="el-icon-menu"></i>
                <span slot="title" >学生成绩</span>
              </el-menu-item>
              <el-menu-item
                index="/university_administration_index/university_administration_grade_check"
                @click="GotoGradeCheck()">
                <i class="el-icon-document"></i>
                <span slot="title">成绩审核</span>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>


      </el-aside>


      <el-main>
        <el-table
          :data="tableData.filter(data => !search || data.type.toLowerCase().includes(search.toLowerCase())||data.id.toLowerCase().includes(search.toLowerCase())||data.name.toLowerCase().includes(search.toLowerCase()))"
          style="width: 80%"
          max-height="800"
          class="table">
          <el-table-column
            label="课程名称"
            prop="cName"
            width="150">
          </el-table-column>
          <el-table-column
            label="学号"
            prop="sId"
            width="150">
          </el-table-column>
          <el-table-column
            label="学生姓名"
            prop="name"
            width="150">
          </el-table-column>
          <el-table-column
            label="分数"
            prop="grade"
            width="150">
          </el-table-column>

        </el-table>
      </el-main>
    </el-container>




  </div>

</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        tableData: [],
        CourseId:'',
      }
    },
    methods: {
      handleSelect(data){
        this.$router.push({ path: data })

      },
      GotoClassGrade(){
        this.$router.push("/university_administration_index/university_administration_courseInfo");

      },
      GotoStudentGrade(){
        this.$router.push("/university_administration_index/university_administration_studentInfo");
      },
      GotoGradeCheck(){
        this.$router.push("/university_administration_index/university_administration_grade_check")
      },
      getCourseId(){
        this.CourseId=this.$route.query.CourseId;

      },
      getGrade(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/querySingleCourseScore',
          params: {

            'cId':this.CourseId
          },
        }).then((res)=>{
          let i;
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              sId: '',
              name: '',
              grade: '',
              cName:'',
            };
            temp.sId=res.data[i].stuId;
            temp.name=res.data[i].stuName;
            temp.cName=res.data[i].courseName;
            temp.grade=res.data[i].grade;
            this.tableData.push(temp);
          }
        })

      },

    }
    ,
    created() {
      this.getCourseId();
      this.getGrade();
    }


  }
</script>

<style scoped>

</style>
