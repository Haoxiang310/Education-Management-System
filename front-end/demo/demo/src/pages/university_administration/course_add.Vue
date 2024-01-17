<template>
  <div id="forward">
    <el-button type="primary" icon="el-icon-circle-plus-outline" @click="dialogVisible = true" >添加课程</el-button>

    <el-dialog title ="课程信息" :visible.sync="dialogVisible">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="课程名称" prop="course_name">
          <el-input v-model="ruleForm.course_name"></el-input>
        </el-form-item >

        <el-form-item label="课程学院" prop="course_school">
          <el-input type="textarea" v-model="ruleForm.course_school"></el-input>
        </el-form-item>
        <el-form-item label="课程专业" prop="course_major">
          <el-input type="textarea" v-model="ruleForm.course_major"></el-input>
        </el-form-item>

        <el-form-item label="课程类型" prop="course_type">
          <el-select v-model="ruleForm.course_type" placeholder="请选择课程类型" style="width: 560px">
            <el-option label="公选课" value="公选课"></el-option>
            <el-option label="专选课" value="专选课"></el-option>
            <el-option label="必修课" value="必修课"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="课程学期" prop="course_semester" >
          <el-input v-model="ruleForm.course_semester" placeholder="请输入1~8中任意整数"></el-input>
        </el-form-item>

        <el-form-item label="课程学分" prop="course_credit">
          <el-input v-model="ruleForm.course_credit"></el-input>
        </el-form-item>

        <el-form-item label="课程年级" prop="course_grade">
          <el-select v-model="ruleForm.course_grade" placeholder="请选择年级" style="width: 560px">
          <el-option label="大一" value="1"></el-option>
          <el-option label="大二" value="2"></el-option>
          <el-option label="大三" value="3"></el-option>
          <el-option label="大四" value="4"></el-option>
        </el-select>
        </el-form-item>

        <el-form  :model="dateForm" :inline="true"  class="demo-form-inline" ref="dateForm" >
        <el-form-item label="课程起始周" prop="course_startweek" style="width: 310px">
          <el-input v-model="dateForm.course_startweek" placeholder="请输入1~16中任意整数"></el-input>
        </el-form-item>
        <el-form-item label="课程结束周" prop="course_endweek"style="width: 310px">
          <el-input v-model="dateForm.course_endweek" placeholder="请输入不超过16的整数"></el-input>
        </el-form-item>
        </el-form>


        <el-button type="primary" @click="create_class">立即创建</el-button>
        <el-button @click="resetForm('ruleForm','dateForm')">重置</el-button>
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
      width="300">
      <template slot-scope="scope">
        <el-button
          @click="close(scope.$index, scope.row)"
          size="small"
          type="danger"
          :disabled=scope.row.tui_state>
          删除课程
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
      this.create_class();
    },
    data() {
      return {
        tableData:[],
        ruleForm: {
          course_name: '',
          course_school: '',
          course_major: '',
          course_type: '',
          course_semester:'',
          course_credit:'',
          course_grade:'',
        },
        dateForm:{
          course_startweek:'',
          course_endweek:''
        },

        rules: {
        },
        dialogVisible:false,
      };
    },
    methods: {
      create_class() {
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/addNewLessonToTrainingProgram',
          params: {
            'major':this.ruleForm.course_major,
            'lessonName':this.ruleForm.course_name,
            'lessonSchool':this.ruleForm.course_school,
            'cType':this.ruleForm.course_type,
            'grade':this.ruleForm.course_grade,
            'semester':this.ruleForm.course_semester,
            'credit':this.ruleForm.course_credit,
            'startweek':this.dateForm.course_startweek,
            'endweek':this.dateForm.course_endweek
          },
        }).then((res)=>{
          console.log(res.data);
          if(res.data="set new lesson success")
          {
            this.$message('添加课程成功!');
          }
          this.reload();
        })
      },
      resetForm(formName1,formName2) {
        this.$refs[formName1].resetFields();
        this.$refs[formName2].resetFields();
      },
    }
  }
</script>




<style scoped>
.table{
  left: 50px;
}
</style>
