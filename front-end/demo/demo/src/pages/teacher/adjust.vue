<template>
  <el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="课程id"
      prop="number">
    </el-table-column>
    <el-table-column
      label="原课程时间"
      prop="old_time">
    </el-table-column>
    <el-table-column
      label="现课程时间"
      prop="new_time">
    </el-table-column>
    <el-table-column
      label="原课程地点"
      prop="old_place">
    </el-table-column>
    <el-table-column
      label="现课程地点"
      prop="new_place">
    </el-table-column>
    <el-table-column
      label="调课理由"
      prop="reason">
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
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/queryChangeCourseTime',
          params: {
            'tId':localStorage.getItem("teacherId")
          },
        }).then((res)=>{
          console.log(res.data);
          let i;


          for(i=0;i<res.data.length;i++)
          {
            let b={
              number: '',
              old_time:'',
              new_time:'',
              old_place:'',
              new_place:'',
              reason:'',
              name: '',
              teacher:'',
              table:'',
              address: '',
              time:'',
              type:'',
              period:'',
              credit: '',
            };

            b.number=res.data[i].cId;
            b.old_time=res.data[i].old_week+'-'+res.data[i].old_day+'-'+res.data[i].old_time;
            b.new_time=res.data[i].new_week+'-'+res.data[i].new_day+'-'+res.data[i].new_time;
            b.old_place=res.data[i].old_place;
            b.new_place=res.data[i].new_place;
            b.reason=res.data[i].reason;
            b.name=res.data[i].name;


            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/queryChangeCourseTime',
              params: {
                'tId':localStorage.getItem("teacherId")
              },
            }).then((res)=> {
              console.log(res.data);
            })

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
