<template>
  <el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="学生学号"
      prop="number">
    </el-table-column>
    <el-table-column
      label="课堂讲台"
      prop="table">
    </el-table-column>
    <el-table-column
      label="原成绩"
      prop="old_score">
    </el-table-column>
    <el-table-column
      label="新成绩"
      prop="new_score">
    </el-table-column>
    <el-table-column
      label="原平时成绩"
      prop="old_usual_score">
    </el-table-column>
    <el-table-column
      label="原考试成绩"
      prop="old_final_score">
    </el-table-column>
    <el-table-column
      label="新平时成绩"
      prop="new_usual_score">
    </el-table-column>
    <el-table-column
      label="新考试成绩"
      prop="new_final_score">
    </el-table-column>
    <el-table-column
      align="right">

      <!--      <template slot-scope="scope">-->
      <!--        <el-button-->
      <!--          size="mini"-->
      <!--          @click="dialogVisible = true">添加成绩</el-button>-->
      <!--        <el-button-->
      <!--          size="mini"-->
      <!--          type="danger"-->
      <!--          @click.native.prevent="deleteRow(scope.$index, tableData)">删除学生</el-button>-->
      <!--      </template>-->
    </el-table-column>
  </el-table>
</template>

<script>
  import axios from 'axios'
  export default {

    mounted() {
      this.getCourses();
    },
    data() {
      return {
        cid:'',
        dialogVisible:false,
        tableData: [],
        search: '',
      }
    },
    created(){
      this.getParams();
    },
    methods: {
      deleteRow(index, rows) {
        rows.splice(index, 1);
      },
      register() {
        this.dialogVisible=true;
      },
      onSubmit(grade) {
        this.$message({
          type: 'success',
          message: '提交成功!'
        });
      },
      getParams(){
        this.cid = this.$route.query.number;
        this.table=this.$route.query.table;
        console.log(this.cid,this.table);
      },
      getCourses(){

        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/TeaScoreCheck',
          params: {
            'cId':this.cid,
            'tId':localStorage.getItem("teacherId")
          },
        }).then((res)=>{
          console.log(res.data);
          let i;


          for(i=0;i<res.data.length;i++)
          {
            let b={
              number: '',
              name: '',
              teacher:'',
              table:'',
              address: '',
              time:'',
              type:'',
              period:'',
              credit: '',
            };

            b.number=res.data[i].sId;
            b.name=res.data[i].name;
            b.old_score=res.data[i].old_score;
            b.new_score=res.data[i].new_score;
            b.table=this.table;
            b.old_usual_score=res.data[i].old_usual_score;
            b.new_usual_score=res.data[i].new_usual_score;
            b.old_final_score=res.data[i].old_final_score;
            b.new_final_score=res.data[i].new_final_score;
            // b.teacher=res.data[i].

            this.tableData.push(b);

          }



        })
      }
    }
  }
</script>

<style scoped>

</style>
