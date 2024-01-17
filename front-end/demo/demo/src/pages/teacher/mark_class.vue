<template>
  <div id="catch">

    <el-dialog title ="选课要求" :visible.sync="dialogVisible2">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="选课年级" prop="name">
          <el-input v-model="ruleForm.grade"></el-input>
        </el-form-item >

        <el-form-item label="选课专业" prop="oldclassposition">
          <el-input v-model="ruleForm.major"></el-input>
        </el-form-item>

        <el-form-item label="先修课程" prop="newclassposition">
          <el-input v-model="ruleForm.frontclass"></el-input>
        </el-form-item>

        <el-form-item label="是否挂科" prop="newclassposition">
          <el-input v-model="ruleForm.frontclassgrade" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        label="课程id"
        prop="number">
      </el-table-column>
      <el-table-column
        label="课程名称"
        prop="name">
      </el-table-column>
      <el-table-column
        label="讲台名"
        prop="table">
      </el-table-column>
      <el-table-column
        label="课程地点"
        prop="address">
      </el-table-column>
      <el-table-column
        label="课程时间"
        prop="period">
      </el-table-column>
      <el-table-column
        align="right">

        <template slot-scope="scope" >
          <el-button

            size="mini"
            type="success"
            @click="markmanage2(scope.$index, scope.row)">查看勘误记录</el-button>
          <el-button

            size="mini"
            type="danger"
            @click="markmanage(scope.$index, scope.row)">成绩登记</el-button>
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
        tableData: [

        ],
        search: '',
        grade: {
          normal: '',
          final: ''
        },
        ruleForm: {
          oldclassposition: '',
          newclassposition: '',
          name: '',
          region: '',
          grade:'',
          major:'',
          frontclass:'',
          frontclassgrade:'',
          week1: '',
          day1: '',
          time1: '',
          week2: '',
          day2: '',
          time2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        rules: {

        },
        dialogVisible2:false,
      };
    },
    mounted() {
      this.getCourses();
    },
    methods: {
      markmanage2(index,row){
        this.$router.push({
          path:'/teacher_index/teacher_mark_resign',
          query:{
            number: row.number,
            table:row.table
          }
        })


      },

      markmanage(index,row){
        console.log(row.number);
        this.$router.push({
          path:'/teacher_index/mark_register',
          query:{
            number: row.number,
            table:row.table
          }
        })
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$message({
              type: 'success',
              message: '提交成功!'
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },

      getCourses(){

        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/checkCourse',
          params: {
            'teaid':localStorage.getItem("teacherId")
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

            b.number=res.data[i].cId;
            b.name=res.data[i].cName;
            b.address=res.data[i].place;
            b.table=res.data[i].class_;
            b.time=res.data[i].day+'-'+res.data[i].time;
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
  #forward
  {
    margin: 0 auto;
    text-align: center;
    width: 500px;
    height: 500px;
    top: 500px;
    background-size:50px 100px;
  }
</style>
