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
          style="width: 100%"
          class="table"
          max-height="800">
          <el-table-column
            prop="cId"
            label="课程编号"
            width="120">
          </el-table-column>
          <el-table-column
            prop="name"
            label="课程名称"
            width="120">
          </el-table-column>
          <el-table-column
            prop="teacher"
            label="授课教师"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="授课地点"
            width="120">
          </el-table-column>
          <el-table-column
            prop="time"
            label="授课时间"
            width="120">
          </el-table-column>
          <el-table-column
            prop="type"
            label="课程类型"
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
        CourseName:'',

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
      getCourseName(){
        this.CourseName=this.$route.query.CourseName;

      },
      getGrade(index,row){
        this.$router.push({
          path:'/university_administration_index/university_administration_class_grade',
          query:{
            CourseId:row.cId,
          }
        })

      },
      getClass(){
        console.log(this.CourseName);
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryAllCourseByCourseName',
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
              name: '',
              teacher:'',
              address: '',
              time:'',
              type:'',
              period:'',
              credit: '',
            };
            temp.cId=res.data[i].cId;
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/queryTeacher',
              params: {
                'cId':temp.cId
              }
            }).then((res)=>{
                temp.teacher=res.data.tName;
              }
            );
            temp.name=res.data[i].cName;
            temp.address=res.data[i].place;
            temp.time='周'+res.data[i].day+'第'+res.data[i].time+'节课';
            temp.type=res.data[i].cType;
            temp.period=res.data[i].startweek+'-'+res.data[i].endweek;
            temp.credit=res.data[i].credit;
            this.tableData.push(temp);
          }
        })

      }

    }
    ,
    created(){
      this.getCourseName();
      this.getClass();
    }


  }
</script>

<style scoped>

</style>
