<template>
  <div>
    <el-table
      :data="test"
      border
      style="width: 70%"
      cell-click="call()"
      class="table">
      <el-table-column
        prop="time"
        width="150">
      </el-table-column>

      <el-table-column
        prop="Monday"
        label="星期一"
        width="100">
      </el-table-column>
      <el-table-column
        prop="Tuesday"
        label="星期二"
        width="100">
      </el-table-column>
      <el-table-column
        prop="Wednesday"
        label="星期三"
        width="100">
      </el-table-column>
      <el-table-column
        prop="Thursday"
        label="星期四"
        width="100">
      </el-table-column>
      <el-table-column
        prop="Friday"
        label="星期五"
        width="100">
      </el-table-column>
      <el-table-column
        prop="Saturday"
        label="星期六"
        width="100">
      </el-table-column>
      <el-table-column
        prop="Sunday"
        label="星期日"
        width="100">
      </el-table-column>
    </el-table>
    <div class="fill" />
  </div>
</template>

<script>
  import ScheduleInfo from "./ScheduleInfo";

  import axios from 'axios'


  export default {
    name: "Schedule",
    components: {
      ScheduleInfo
    },
    mounted(){
      this.stu_course();
    },
    data() {
      return {
        days: ['','星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日'],
        times: ['8:00-9:35','9:55-11:30','13:30-15:05','15:20-16:55','17:10-18:45','19:30-21:05'],
        show: false,
        schedules: [{
          time:'8:00-9:35',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''},
          {time:'9:55-11:30',Monday:'',Tuesday:'',Wednesday:'',Thursday:'',Friday:'',Saturday:'',Sunday:''},
          {time:'13:30-15:05',Monday:'',Tuesday:'',Wednesday:'',Thursday:'',Friday:'',Saturday:'',Sunday:''},
          {time:'15:20-16:55',Monday:'',Tuesday:'',Wednesday:'',Thursday:'',Friday:'',Saturday:'',Sunday:''},
          {time:'17:10-18:45',Monday:'',Tuesday:'',Wednesday:'',Thursday:'',Friday:'',Saturday:'',Sunday:''},
          {time:'19:30-21:05',Monday:'',Tuesday:'',Wednesday:'',Thursday:'',Friday:'',Saturday:'',Sunday:''}],
        test: [{
          time:'8:00-9:35',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''}, {
          time:'9:55-11:30',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''}, {
          time:'13:30-15:05',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''}, {
          time:'15:20-16:55',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''}, {
          time:'17:10-18:45',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''}, {
          time:'19:30-21:05',
          Monday:'',
          Tuesday:'',
          Wednesday:'',
          Thursday:'',
          Friday:'',
          Saturday:'',
          Sunday:''}],
        courseInfo: {}
      }
    },
    methods: {
      /*判断是否重课*/
      call(){},
      hasChild(course) {
        return course.children != null;
      },
      /*判断是否空课*/
      showInfo(course) {
        if (!course.isEmptyCourse){
          this.courseInfo = course;
          this.show = true;
        }
      },
      stu_course()
      {
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/checkCourse',
          params: {
            'teaid':localStorage.getItem("teacherId")
          },
        }).then((res)=>{
          console.log(res.data);

          let i;
          for(i=0;i<res.data.length;i++) {
            if(res.data[i].day==1)
            {
              this.test[res.data[i].time-1].Monday=res.data[i].cName+'\n'+res.data[i].place+'\n'+res.data[i].startweek+'-'+res.data[i].endweek;
            }
            else if(res.data[i].day==2){
              this.test[res.data[i].time-1].Tuesday=res.data[i].cName+'\n'+res.data[i].place+'\n'+res.data[i].startweek+'-'+res.data[i].endweek;
            }
            else if(res.data[i].day==3){
              this.test[res.data[i].time-1].Thursday=res.data[i].cName+'\n'+res.data[i].place+'\n'+res.data[i].startweek+'-'+res.data[i].endweek;
            }
            else if(res.data[i].day==4){
              this.test[res.data[i].time-1].Wednesday=res.data[i].cName+'\n'+res.data[i].place+'\n'+res.data[i].startweek+'-'+res.data[i].endweek;
            }
            else if(res.data[i].day==5){
              this.test[res.data[i].time-1].Friday=res.data[i].cName+'\n'+res.data[i].place+'\n'+res.data[i].startweek+'-'+res.data[i].endweek;
            }
          }})},
      getCourses(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/checkCourse',


        }).then((res)=>{
          console.log(res.data);

        })
      }
    }
  }
</script>

<style scoped>

  .table
  {
    left: 200px;
  }
</style>
