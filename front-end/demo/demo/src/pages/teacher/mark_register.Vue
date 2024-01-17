<template>
<div id="register">

  <el-dialog title ="登记成绩" :visible.sync="dialogVisible1">
  <el-form :inline="true" :model="formInline"  class="demo-form-inline">
    <el-form-item label="平时成绩">
      <el-input v-model="grade.normal" placeholder="平时成绩"></el-input>
    </el-form-item>
    <el-form-item label="最终成绩">
      <el-input v-model="grade.final" placeholder="考试成绩"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">提交</el-button>
    </el-form-item>
  </el-form>
  </el-dialog>
  <el-dialog title ="勘误成绩" :visible.sync="dialogVisible2" >
    <el-form :label-position="right" label-width="auto">
      <el-form-item label="平时成绩" >
        <el-col :span="11">
        <el-input v-model="grade.normal" placeholder="平时成绩" style="width: 260px"></el-input>
        </el-col>
        <el-col :span="11">
      <el-form-item label="最终成绩">
        <el-input v-model="grade.final" placeholder="考试成绩" style="width: 260px"></el-input>
      </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="勘误理由">
        <el-input v-model="grade.reason" placeholder="勘误理由" style="width: 600px"></el-input>
      </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="re_onSubmit">提交</el-button>
    </el-form-item>
    </el-form>
  </el-dialog>
  <el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="学生学号"
      prop="number">
    </el-table-column>
    <el-table-column
      label="学生姓名"
      prop="name">
    </el-table-column>
    <el-table-column
      label="讲台"
      prop="address">
    </el-table-column>
    <el-table-column
      label="平时成绩"
      prop="normal">
    </el-table-column>
    <el-table-column
      label="考试成绩"
      prop="exam">
    </el-table-column>
    <el-table-column
      label="最终成绩"
      prop="final">
    </el-table-column>
    <el-table-column
      align="right">

      <template slot-scope="scope">
        <el-button
          size="mini"
          type="success"
          @click="mark_register(scope.$index,scope.row)">添加成绩</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="remark_register(scope.$index,scope.row)">勘误成绩</el-button>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        score:'',
        old_usual_score:'',
        old_final_score:'',
        number:'',
        cid:'',
        dialogVisible1:false,
        dialogVisible2:false,
        tableData: [],
        search: '',
        grade: {
          normal: '',
          final: '',
          reason:''
        }
      }
    },
    mounted() {
      this.getStudent();
    },
    methods: {
      re_onSubmit(){

        console.log(this.grade.normal,this.grade.final);
        console.log(this.old_usual_score+this.old_final_score);
        var c=Number(this.grade.normal)+Number(this.grade.final);
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/SubmitScoreCheck',

          params:{
            sId:this.number,
            tId:localStorage.getItem("teacherId"),
            cId:this.cid,
            old_score:this.score,
            new_score:c,
            old_usual_score:this.old_usual_score,
            old_final_score:this.old_final_score,
            new_usual_score:this.grade.normal,
            new_final_score:this.grade.final,
            changeReason:this.grade.reason
          }
        }).then((res)=>{
          alert(res.data);

        })
      },
      remark_register(index,row){
        this.dialogVisible2 = true;
        this.number=row.number;
        this.old_usual_score=row.normal;
        this.old_final_score=row.exam;
        this.score=row.final;
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      },
      mark_register(index,row){
        this.dialogVisible1 = true;
        this.number=row.number;
      },
      onSubmit() {
        var c=Number(this.grade.normal)+Number(this.grade.final);
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/SubmitScoreCheck',

          params:{
            sId:this.number,
            tId:localStorage.getItem("teacherId"),
            cId:this.cid,
            old_score:0,
            new_score:c,
            old_usual_score:0,
            old_final_score:0,
            new_usual_score:this.grade.normal,
            new_final_score:this.grade.final,
            changeReason:'xx'
          }
        }).then((res)=>{
          alert(res.data);

        })
        location.reload();
      },
      getParams(){
        this.cid = this.$route.query.number;
        this.address = this.$route.query.table;
        console.log(this.cid);
      },
      getStudent(){
//首先获得这门课所有的sid
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/checkStudent',
          params: {
            'courseid':this.cid
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
              normal:'',
              final:'',
              period:'',
              credit: '',
            };
            b.name=res.data[i].name;
            b.number=res.data[i].sId;
            b.address=this.address;


            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/queryStuScore',
              params: {
                'cId':this.cid,
                'sId':res.data[i].sId
              },
            }).then((res)=>{
              console.log(res.data);
              console.log(res.data[0].cId);
               b.normal=res.data[0].usual_grade;
              b.exam=res.data[0].final_grade;
              b.final=res.data[0].grade;
                // b.teacher=res.data[i].



            });
            // b.name=res.data[i].cName;
            // b.address=res.data[i].place;
            // b.table=res.data[i].class_;
            // b.time=res.data[i].day+'-'+res.data[i].time;
            // b.type=res.data[i].cType;
            // b.period=res.data[i].startweek+'-'+res.data[i].endweek;
            // b.credit=res.data[i].credit;
            // b.teacher=res.data[i].

            this.tableData.push(b);

          }
        })




        }
  },
    created(){
      this.getParams();
    }
  }
</script>
