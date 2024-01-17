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
          style="width: 100%"
          max-height="800"

          class="table">
          <el-table-column
            label="课程名称"
            prop="cName"
            width="100">
          </el-table-column>
          <el-table-column
            label="教师姓名"
            prop="tName"
            width="100">
          </el-table-column>
          <el-table-column
            label="学号"
            prop="sId"
            width="100">
          </el-table-column>
          <el-table-column
          label="原成绩"
          prop="oldScore"
          width="100">
        </el-table-column>
          <el-table-column
            label="原平时成绩"
            prop="oldUsualScore"
            width="100">
          </el-table-column>
          <el-table-column
            label="原考试成绩"
            prop="oldFinalScore"
            width="100">
          </el-table-column>
          <el-table-column
            label="新成绩"
            prop="newScore"
            width="100">
          </el-table-column>
          <el-table-column
            label="新平时成绩"
            prop="newUsualScore"
            width="100">
          </el-table-column>
          <el-table-column
            label="新考试成绩"
            prop="newFinalScore"
            width="100">
          </el-table-column>
          <el-table-column
            label="提交原因"
            prop="reason"
            width="100">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button
                @click="pass(scope.$index, scope.row)"
                type="text"
                size="small">
                通过
              </el-button>
              <el-button
                @click="deny(scope.$index, scope.row)"
                type="text"
                size="small">
                不通过
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
    inject:['reload'],
    data() {
      return {
        tableData: [],
      }
    },
    methods: {
      getScoreCheck(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/getAllScoreCheckByAdmin',
          params: {
            'adminId':localStorage.getItem("adminId"),
          },
        }).then((res)=>{
          let i;
          console.log(res.data);
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              checkId:'',
              sId: '',
              cName:'',
              cId:'',
              tId:'',
              tName:'',
              reason:'',
              newScore: '',
              newUsualScore:'',
              newFinalScore:'',
              oldScore: '',
              oldUsualScore: '',
              oldFinalScore: '',
            };
            temp.checkId=res.data[i].checkId;
            temp.sId=res.data[i].sId;
            temp.tId=res.data[i].tId;
            temp.cId=res.data[i].cId;
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/queryTeacher',
              params: {
                'cId':temp.cId
              }
            }).then((res)=>{
              temp.tName=res.data.tName;
            });
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/querycNameBycId',
              params: {
                'cId': temp.cId,

              }

            }).then((res)=>{
              console.log(res.data);
              temp.cName=res.data;
            });
            temp.reason=res.data[i].changeReason;
            temp.newScore=res.data[i].new_score;
            temp.newUsualScore=res.data[i].new_usual_score;
            temp.newFinalScore=res.data[i].new_final_score;
            temp.oldScore=res.data[i].old_score;
            temp.oldUsualScore=res.data[i].old_usual_score;
            temp.oldFinalScore=res.data[i].old_final_score;
            this.tableData.push(temp);
          }
        })
      },
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
      pass(index,row) {
        this.$confirm('此操作将通过该成绩, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/updateGrade',
              params:{
                'stuId':row.sId,
                'courseId':row.cId,
                'usual_grade':row.newUsualScore,
                'final_grade':row.newFinalScore,
              }
            }).then((res)=>{
              console.log(res.data);


            });
          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/updateScoreCheckAdminCheck',
            params: {
              'checkId':row.checkId,
              'adminCheck':2,
            },

          }).then((res)=>{
            console.log(res.data);

              this.$message({
                type: 'success',
                message: '提交成功!'
              });
              this.reload();


          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          });
        });



      },
      deny(index,row){
        this.$confirm('此操作将否定该成绩, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/updateScoreCheckAdminCheck',
            params: {
              'checkId':row.checkId,
              'adminCheck':1,
            },

          }).then((res)=>{
            console.log(res.data);

              this.$message({
                type: 'success',
                message: '提交成功!'
              });
              this.reload();



          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          });
        });

      }
    }
    ,
    mounted() {
      this.getScoreCheck();
    }


  }
</script>

<style scoped>

</style>
