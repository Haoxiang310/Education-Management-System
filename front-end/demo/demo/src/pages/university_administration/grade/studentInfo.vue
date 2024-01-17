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
          :data="tableData"
          style="width: 80%"
          max-height="800"
          class="table">
          <el-table-column
            label="学号"
            prop="sId"
            width="120">
          </el-table-column>
          <el-table-column
            label="学生姓名"
            prop="name"
            width="120">
          </el-table-column>
          <el-table-column
            label="班级"
            prop="class"
            width="120">
          </el-table-column>
          <el-table-column
            label="年级"
            prop="grade"
            width="120">
          </el-table-column>
          <el-table-column
            label="所属学院"
            prop="school"
            width="120">
          </el-table-column>
          <el-table-column
            label="所修专业"
            prop="major"
            width="120">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="120">
            <template slot-scope="scope">
              <el-button
                @click="getGrade(scope.$index, scope.row)"
                size="small"
                type="danger"

              >
                查看成绩
              </el-button>
            </template>
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
      getGrade(index,row){
        this.$router.push({
          path:'/university_administration_index/university_administration_student_grade',
          query:{
            StudentId:row.sId,
          }
        })
      },
      getStudents(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryAllStuInfo',
          params: {
            'adminId':localStorage.getItem("adminId"),
          },
        }).then((res)=>{
          console.log(res.data);
          let i;
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              sId: '',
              name: '',
              class: '',
              major:'',
              school:'',
              grade: '',
            };
            temp.sId=res.data[i].sId;
            temp.name=res.data[i].name;
            temp.class=res.data[i].class_;
            temp.major=res.data[i].major;
            temp.school=res.data[i].sSchool;
            temp.grade=res.data[i].grade;
            this.tableData.push(temp);
          }
        })
      }
    }
    ,
    mounted() {
      this.getStudents();
    }


  }
</script>

<style scoped>

</style>
