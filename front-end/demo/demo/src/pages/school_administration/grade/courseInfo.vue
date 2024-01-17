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
                index="/school_administration_index/school_administration_courseInfo"
                @click="GotoClassGrade()">
                <i class="el-icon-menu"></i>
                <span slot="title" >课程成绩</span>
              </el-menu-item>
              <el-menu-item
                index="/school_administration_index/school_administration_studentInfo"
                @click="GotoStudentGrade()">
                <i class="el-icon-menu"></i>
                <span slot="title" >学生成绩</span>
              </el-menu-item>
              <el-menu-item
                index="/school_administration_index/school_administration_grade_check"
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
            prop="name"
            label="课程名称"
            width="120">
          </el-table-column>
          <el-table-column
            prop="school"
            label="所属学院"
            width="120">
          </el-table-column>
          <el-table-column
            prop="grade"
            label="课程年级"
            width="120">
          </el-table-column>
          <el-table-column
            prop="semester"
            label="授课学期"
            width="120">
          </el-table-column>
          <el-table-column
            prop="period"
            label="课程周期"
            width="120">
          </el-table-column>
          <el-table-column
            prop="credit"
            label="课程学分"
            width="120">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="120">
            <template slot-scope="scope">
              <el-button
                @click="getClass(scope.$index, scope.row)"
                type="text"
                size="small"
              >
                查看讲台
              </el-button>
              <el-button
                @click="getAllGrade(scope.$index, scope.row)"
                size="small"
                type="danger"

              >
                查看所有成绩
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
        this.$router.push("/school_administration_index/school_administration_courseInfo");

      },
      GotoStudentGrade(){
        this.$router.push("/school_administration_index/school_administration_studentInfo");
      },
      GotoGradeCheck(){
        this.$router.push("/school_administration_index/school_administration_grade_check")
      },
      getClass(index,row){
        console.log(row.name);
        this.$router.push({
          path:'/school_administration_index/school_administration_classInfo',
          query:{
            CourseName:row.name,
          }
        })

      },
      getAllGrade(index,row){
        console.log(row.name);
        this.$router.push({
          path:'/school_administration_index/school_administration_course_grade',
          query:{
            CourseName:row.name,
          }
        })

      },
      getCourses(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryAllLessons',

        }).then((res)=>{
          console.log(localStorage.getItem("adminSchool"));
          let i;
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              Id: '',
              name: '',
              school:'',
              grade:'',
              semester:'',
              period:'',
              credit: '',
            };
            temp.Id=res.data[i].lessonId;
            temp.name=res.data[i].lessonName;
            temp.school=res.data[i].lessonSchool;
            temp.grade=res.data[i].grade;
            temp.semester=res.data[i].semester;
            temp.period=res.data[i].startweek+'-'+res.data[i].endweek;
            temp.credit=res.data[i].credit;
            this.tableData.push(temp);
          }
        })
      },

    }
    ,
    mounted() {
      this.getCourses();

    }


  }
</script>

<style scoped>

  .home-container{
    height: 100%;
  }
</style>
