<template>
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
      label="年级/班级"
      prop="grade">
    </el-table-column>
    <el-table-column
      label="课程"
      prop="table">
    </el-table-column>
    <el-table-column
      label="讲台"
      prop="address">
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
        this.table=this.$route.query.name;
        this.address=this.$route.query.table;
        console.log(this.cid,this.table);
      },
      getCourses(){

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
              time:'',
              type:'',
              period:'',
              credit: '',
            };

            b.number=res.data[i].sId;
            b.name=res.data[i].name;
           // b.address=res.data[i].place;
            b.table=this.table;
            b.address= this.address;
            b.grade=res.data[i].major+'-'+res.data[i].grade;
            b.type=res.data[i].cType;
            b.period=res.data[i].startweek+'-'+res.data[i].endweek;
            b.credit=res.data[i].credit;
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
