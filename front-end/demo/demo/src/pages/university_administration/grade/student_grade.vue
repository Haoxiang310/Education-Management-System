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
          id="score_table"
          class="table">
          <el-table-column
            label="学号"
            prop="sId"
            width="150">
          </el-table-column>
          <el-table-column
            label="学生姓名"
            prop="stuName"
            width="150">
          </el-table-column>
          <el-table-column
            label="课程编号"
            prop="cId"
            width="150">
          </el-table-column>
          <el-table-column
            label="课程名称"
            prop="cName"
            width="150">
          </el-table-column>
          <el-table-column
            label="成绩"
            prop="score"
            width="150">
          </el-table-column>
        </el-table>
        <div style="margin: 80px 0;"></div>
        <el-button  type="success" plain @click="exportExcel()" >导出成绩单</el-button>
        <el-button v-print="table" type="success" plain>打印成绩单</el-button>
      </el-main>

    </el-container>




  </div>

</template>

<script>
  import axios from 'axios'
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'
  export default {
    data() {
      return {
        tableData: [],
        StudentId:'',
      }
    },
    methods: {
      exportExcel() {
        let wb = XLSX.utils.table_to_book(document.querySelector('#score_table'))
        let wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
        try {
          FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'Battery.xlsx')
        } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout) }
        return wbout
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
      getStudentId(){
        this.StudentId=this.$route.query.StudentId;

      },
      getStudentGrade(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryStuAllCourse',
          params: {
            'sId':this.StudentId
          },
        }).then((res)=>{
          let i;
          console.log(res.data);
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              sId: '',
              stuName: '',
              cName:'',
              cId:'',
              score: '',
            };
            temp.sId=res.data[i].sId;
            temp.cId=res.data[i].cId;
            temp.stuName=res.data[i].stuName;
            temp.cName=res.data[i].cName;
            temp.score=res.data[i].score;
            this.tableData.push(temp);
          }
        })
      }

    }
    ,
    mounted() {

    },
    created() {
      this.getStudentId();
      this.getStudentGrade();
    }


  }
</script>

<style scoped>

</style>
