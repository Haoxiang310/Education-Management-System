<template>
  <div>
    <el-container>
      <el-dialog title ="课程安排" :visible.sync="dialogVisible">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="课程地点" prop="name">
            <el-input v-model="ruleForm.location"></el-input>
          </el-form-item>
          <el-form-item label="天数" prop="name">
            <el-input v-model="ruleForm.day"></el-input>
          </el-form-item >
          <el-form-item label="节数" prop="name">
            <el-input v-model="ruleForm.time"></el-input>
          </el-form-item >
          <el-form-item label="起始周" prop="name">
            <el-input v-model="ruleForm.startweek"></el-input>
          </el-form-item >
          <el-form-item label="结束周" prop="name">
            <el-input v-model="ruleForm.endweek"></el-input>
          </el-form-item >
          <el-form-item>
          <el-button type="primary" @click="submitForm">确定创建</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
  <el-table
    :data="tableData"
    style="width: 100%"
    class="table"
    max-height="800">
    <el-table-column
      prop="cId"
      label="课程编号"
      width="150">
    </el-table-column>
    <el-table-column
      prop="name"
      label="课程名称"
      width="150">
    </el-table-column>
    <el-table-column
      prop="teacher"
      label="授课教师"
      width="150">
    </el-table-column>
    <el-table-column
      prop="address"
      label="授课地点"
      width="150">
    </el-table-column>
    <el-table-column
      prop="time"
      label="授课时间"
      width="150">
    </el-table-column>
    <el-table-column
      prop="type"
      label="课程类型"
      width="150">
    </el-table-column>
    <el-table-column
      prop="period"
      label="课程周期"
      width="150">
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
          scope.row.time=ruleForm.time
          @click="open(scope.$index, scope.row)"
          type="text"
          size="small">
          排课
        </el-button>
      </template>
    </el-table-column>
  </el-table>

    </el-container>
</div>
</template>

<script>
  import axios from 'axios'
  export default {
    inject:['reload'],
    data() {
      return {
        dialogVisible:false,
        status:'',
        cId:'',
        ruleForm: {
          time:'',
          location:'',
          day:'',
          startweek:'',
          endweek:''
        },
        rules: {
          time: [
            { required: true, message: '请输入课程时间', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          location: [
            { required: true, message: '请输入课程地点', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
        },


        tableData: [],
      }
    },
    methods: {
      getCourses()
      {
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/getMajorCourse',
          params: {
            'adminId':localStorage.getItem("adminId"),
          },
        }).then((res)=>{
          // console.log(res.data);
          let i;
          for(i=0;i<res.data.length;i++)
          {
            let b={
              cId: '',
              name: '',
              teacher:'',
              address: '',
              time:'',
              type:'',
              period:'',
              credit: '',

            };
            b.cId=res.data[i].cId;
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/queryTeacher',
              params: {
                'cId':b.cId
              }
            }).then((res)=>{
                b.teacher=res.data.tName;
              }
            );
            b.name=res.data[i].cName;
            b.address=res.data[i].place;
            b.time='周'+res.data[i].day+'第'+res.data[i].time+'节课';
            b.type=res.data[i].cType;
            b.period=res.data[i].startweek+'-'+res.data[i].endweek;
            b.credit=res.data[i].credit;
            this.tableData.push(b);
          }
        })
      },
      open(index,row) {
         this.dialogVisible=  true;
         this.cId=row.cId;
      },
      submitForm() {

          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryEmptyClassroom',
            params:{
              'place':this.ruleForm.location,
              'startweek':this.ruleForm.startweek,
              'endweek':this.ruleForm.endweek,
              'day':this.ruleForm.day,
              'time':this.ruleForm.time
            }
          }).then((res)=>{
            console.log(res.data);
            let i;
            this.status=1;
            for(i=0;i<res.data.length;i++){
              if(res.data[i].includes("no crash"))
                continue;
              else {
                this.status=0;
                // this.$message({
                //   type: 'info',
                //   message: '有冲突'
                // });
                break;
              }
            }
            if(this.status!=0){
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/setClassroomAndCourseInfo',
              params:{
                'cId':this.cId,
                'place':this.ruleForm.location,
                'startweek':this.ruleForm.startweek,
                'endweek':this.ruleForm.endweek,
                'day':this.ruleForm.day,
                'time':this.ruleForm.time
              }
            }).then((res)=>{
                this.$message({
                  type: 'success',
                  message: '提交成功!'
                });
                this.reload();

            });
            }
            else {
              this.$message({
                type: 'error',
                message: '有冲突提交失败!'
              });
              this.reload();
            }

          });



      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    },

    mounted() {
      this.getCourses();
    }
  }
</script>

<style scoped>

</style>
