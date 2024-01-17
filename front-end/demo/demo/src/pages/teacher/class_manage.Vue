<template>
  <div id="catch">
    <el-dialog title ="停调课表单" :visible.sync="dialogVisible">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="课程名称" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item >

     <el-form-item label="原课程地点" prop="oldclassposition">
        <el-input v-model="ruleForm.oldclassposition"></el-input>
     </el-form-item>

     <el-form-item label="现课程地点" prop="newclassposition">
          <el-input v-model="ruleForm.newclassposition"></el-input>
     </el-form-item>
      <el-form :inline="true"  class="demo-form-inline">

    <el-form-item label="原课程时间" required>
        <el-select v-model="ruleForm.week1" placeholder="请选择周数" style="width: 190px" >
          <el-option label="第1周" value="1"></el-option>
          <el-option label="第2周" value="2"></el-option>
          <el-option label="第3周" value="3"></el-option>
          <el-option label="第4周" value="4"></el-option>
        </el-select>

        <el-select v-model="ruleForm.day1" placeholder="请选择天数" style="width: 190px">
          <el-option label="星期一" value="1"></el-option>
          <el-option label="星期二" value="2"></el-option>
          <el-option label="星期三" value="3"></el-option>
          <el-option label="星期四" value="4"></el-option>
        </el-select>

        <el-select v-model="ruleForm.time1" placeholder="请选择节数" style="width: 190px">
          <el-option label="第一节" value="1"></el-option>
          <el-option label="第二节" value="2"></el-option>
          <el-option label="第三节" value="3"></el-option>
          <el-option label="第四节" value="4"></el-option>
        </el-select>

    </el-form-item>
      </el-form>
      <el-form :inline="true"  class="demo-form-inline">

        <el-form-item label="现课程时间" required   >
          <el-form-item prop="week2">
          <el-select v-model="ruleForm.week2" placeholder="请选择周数" style="width: 190px" >
            <el-option label="" value=""></el-option>
            <el-option label="第1周" value="1"></el-option>
            <el-option label="第2周" value="2"></el-option>
            <el-option label="第3周" value="3"></el-option>
            <el-option label="第4周" value="4"></el-option>
          </el-select>
          </el-form-item>
          <el-select v-model="ruleForm.day2" placeholder="请选择天数" style="width: 190px">
            <el-option label="星期一" value="1"></el-option>
            <el-option label="星期二" value="2"></el-option>
            <el-option label="星期三" value="3"></el-option>
            <el-option label="星期四" value="4"></el-option>
          </el-select>

          <el-select v-model="ruleForm.time2" placeholder="请选择节数" style="width: 190px">
            <el-option label="第一节" value="1"></el-option>
            <el-option label="第二节" value="2"></el-option>
            <el-option label="第三节" value="3"></el-option>
            <el-option label="第四节" value="4"></el-option>
          </el-select>

        </el-form-item>
      </el-form>
<!--    <el-form-item label="课程方式" prop="resource">-->
<!--      <el-radio-group v-model="ruleForm.resource">-->
<!--        <el-radio label="线上课程"></el-radio>-->
<!--        <el-radio label="线下课程"></el-radio>-->
<!--      </el-radio-group>-->
<!--    </el-form-item>-->
    <el-form-item label="调课理由" prop="desc">
      <el-input type="textarea" v-model="ruleForm.desc"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
      </el-form>
  </el-dialog>

    <el-dialog title ="选课要求" :visible.sync="dialogVisible2">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="选课年级" >
          <el-input v-model="ruleForm.grade"></el-input>
        </el-form-item >

        <el-form-item label="选课专业" >
          <el-input v-model="ruleForm.major"></el-input>
        </el-form-item>

        <el-form-item label="课程对应学分" >
          <el-input v-model="ruleForm.credit"></el-input>
        </el-form-item>

        <el-form-item label="课程类型" >
          <el-input v-model="ruleForm.cType"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm2('ruleForm')">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">

      <el-table-column width="220px"
        label="课程id"
        prop="number">
      </el-table-column>
      <el-table-column width="220px"
        label="课程名称"
        prop="name">
      </el-table-column>
      <el-table-column width="220px"
        label="讲台名"
        prop="table">
      </el-table-column>
      <el-table-column width="220px"
        label="课程地点"
        prop="address">
      </el-table-column>
      <el-table-column width="220px"
        label="课程时间"
        prop="period">
      </el-table-column>


      <el-table-column
        align="right">



        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="adjust(scope.$index,scope.row)">停调课申请</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="demand(scope.$index,scope.row)">选课要求</el-button>
        </template>
      </el-table-column>


    </el-table>
    <el-table-column
      align="right">
    <el-button

      size="mini"
      type="danger"
      @click="show">查看停调课申请</el-button>
    </el-table-column>
</div>

</template>

<script>
  import axios from 'axios'
  export default {
    data() {
      return {
        cid:'',
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
          credit:'',
          cType:'',
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
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 1, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          oldclassposition: [
            { required: true, message: '请输入教室地点', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          newclassposition: [
            { required: true, message: '请输入教室地点', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择活动区域', trigger: 'change' }
          ],
          week1: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          day1: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          time1: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],

          week2: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          day2: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          time2: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ],
          resource: [
            { required: true, message: '请选择活动资源', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]
        },
        dialogVisible:false,
        dialogVisible2:false,
      };
    },
    mounted() {
      this.getCourses();
    },
    methods: {
      show(index,row){
        this.$router.push({
          path:'/teacher_index/teacher_adjust',
        })

      },

      adjust(index,row){
        this.dialogVisible = true;
        this.cid=row.number;
      },
      demand(index,row){
        this.dialogVisible2 = true
        this.index=index;
        console.log(this.index);
      },

      submitForm2(formName) {


        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/checkCourse',
          params: {
            'teaid':localStorage.getItem("teacherId")
          },
        }).then((res)=>{
          console.log(res.data);
          let i;

            let b={
              number: '',
              name: '',
              teacher:'',
              table:'',
              class_:'',
              address: '',
              time:'',
              semester:'',
              type:'',
              period:'',
              credit: '',
              place:'',
              day:'',
              startweek:'',
              endweek:''
            };

            b.semester=res.data[this.index].semester;
            b.number=res.data[this.index].cId;
            b.name=res.data[this.index].cName;
            b.address=res.data[this.index].place;
            b.class_=res.data[this.index].class_;
            b.table=res.data[this.index].class_;
            b.day=res.data[this.index].day
            b.time=res.data[this.index].time;
            b.type=res.data[this.index].cType;
            b.startweek=res.data[this.index].startweek
            b.endweek=res.data[this.index].endweek;
            b.credit=res.data[this.index].credit;
            b.place=res.data[this.index].place;

            // b.teacher=res.data[i].


          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/updateCourseRequirement',
            params: {
              'cId':b.number,
              cName:b.name,
              grade:this.ruleForm.grade,
              class_:b.class_,
              major:this.ruleForm.major,
              semester:b.semester,
              credit:this.ruleForm.credit,
              place:b.place,
              day:b.day,
              time:b.time,
              startweek:b.startweek,
              endweek:b.endweek,
              cType:this.ruleForm.cType,
            },
          }).then((res)=>{

            console.log(res.data);

          })

        });



      },

      submitForm(formName) {
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/changeCourseTime',
              params: {
                'cId':this.cid,
                'tId':localStorage.getItem("teacherId"),
                'reason':this.ruleForm.desc,
                'old_place':this.ruleForm.oldclassposition,
                'new_place':this.ruleForm.newclassposition,
                'old_week':this.ruleForm.week1,
                'old_day':this.ruleForm.day1,
                'old_time':this.ruleForm.time1,
                'new_week':this.ruleForm.week2,
                'new_day':this.ruleForm.day2,
                'new_time':this.ruleForm.time2
              },
            }).then((res)=>{

              console.log(res.data);

            })

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
