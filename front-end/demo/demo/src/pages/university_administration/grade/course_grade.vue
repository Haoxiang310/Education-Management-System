<template>
  <div>

    <el-container class="home-container">
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
          :data="tableData"
          style="width: 100%"
          class="table"
          max-height="800">
          <el-table-column
            label="课程编号"
            prop="cId"
            width="150">
          </el-table-column>
          <el-table-column
            label="学生学号"
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
          courseName:'',
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
        getGrade(){
          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryAllCourseScore',
            params: {
              'cName':this.CourseName
            },
          }).then((res)=>{
            console.log(res.data);
            let i;
            for(i=0;i<res.data.length;i++)
            {
              let temp={
                cId: '',
                sId:'',
                name: '',
                grade:'',
              };
              temp.cId=res.data[i].courseId;
              temp.sId=res.data[i].stuId;
              temp.name=res.data[i].stuName;
              temp.grade=res.data[i].grade;
              this.tableData.push(temp);
            }
          })
        },
        getCourseName(){
          this.CourseName=this.$route.query.CourseName;
        }

      }
    ,
      mounted() {
        this.getGrade();

      },
      created() {
        this.getCourseName();
      }


    }
</script>

<style scoped>

  .home-container{
      height: 100%;
  }
</style>
