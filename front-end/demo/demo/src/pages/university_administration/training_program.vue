<template>
  <div id="forward">
      <el-button type="primary" icon="el-icon-circle-plus-outline" @click="dialogVisible = true" >添加培养方案</el-button>

    <el-dialog title ="新培养计划" :visible.sync="dialogVisible">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="专业名称" prop="major">
          <el-input v-model="ruleForm.major"></el-input>
        </el-form-item >


        <el-form-item label="专业入学要求" prop="admission">
          <el-input type="textarea" v-model="ruleForm.admission"></el-input>
        </el-form-item>


        <el-form-item label="修学年份" prop="lengthOfSchooling">
          <el-input v-model="ruleForm.lengthOfSchooling"></el-input>
        </el-form-item>



          <el-button type="primary" @click="createProgram">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form>
    </el-dialog>

    <el-table
      :data="tableData"
      style="width: 100%"
      class="table"
      max-height="800"
    >
      <el-table-column
        prop="major"
        label="培养计划专业"
        width="280">
      </el-table-column>
      <el-table-column
        prop="request"
        label="培养计划要求"
        width="280">
      </el-table-column>
      <el-table-column
        prop="time"
        label="修学年份"
        width="280">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="400">
        <template slot-scope="scope">
          <el-button
            type="small"
            size="small"
            @click="course">
            课程详情
          </el-button>
          <el-button
            @click="delete_program(scope.$index, scope.row)"
            size="small"
            type="danger">
            删除培养计划
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>

<script>

  import axios from 'axios'
  export default {
    inject:['reload'],
    mounted() {
      this.getTrainingProgram();
    },
    data() {
      return {
        tableData:[],
        ruleForm: {
          major: '',
          admission: '',
          lengthOfSchooling: '',
        },
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择活动区域', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ],
          resource: [
            { required: true, message: '请选择活动资源', trigger: 'change' }
          ]
        },
        dialogVisible:false,
      };
    },
    methods: {
      getTrainingProgram(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryAllTrainingProgram',

        }).then((res)=>{
          console.log(res.data);
          let i;
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              program_Id: '',
              major: '',
              request:'',
              time:''
            };
            temp.major=res.data[i].major;
            temp.request=res.data[i].admission;
            temp.time=res.data[i].lengthOfSchooling;
            this.tableData.push(temp);
          }
        })
      },
      createProgram() {
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/setNewTrainingProgram',
          params: {
            'major':this.ruleForm.major,
            'admission':this.ruleForm.admission,
            'lengthOfSchooling':this.ruleForm.lengthOfSchooling
          },
        }).then((res)=>{
          console.log(res.data);
          this.reload();
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      delete_program(index,row)
      {
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/deleteMajorTrainingProgram',
          params: {
            'major':row.major,
          },
        }).then((res)=>{
          console.log(res.data);
          this.reload();
        })
      },
      course()
      {
        this.$router.push("/university_administration_index/university_administration_course_add");
      }
    }
  }
</script>




<style scoped>
.table{
  left: 50px;
}
</style>
