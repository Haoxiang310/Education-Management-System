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
        width="180">
      </el-table-column>

      <el-table-column
        prop="Monday"
        label="星期一"
        width="180">
      </el-table-column>
      <el-table-column
        prop="Tuesday"
        label="星期二">
      </el-table-column>
      <el-table-column
        prop="Wednesday"
        label="星期三">
      </el-table-column>
      <el-table-column
        prop="Thursday"
        label="星期四">
      </el-table-column>
      <el-table-column
        prop="Friday"
        label="星期五">
      </el-table-column>
      <el-table-column
        prop="Saturday"
        label="星期六">
      </el-table-column>
      <el-table-column
        prop="Sunday"
        label="星期日">
      </el-table-column>

    </el-table>

    <div class="fill" />
  </div>
</template>

<script>
  import ScheduleInfo from "./ScheduleInfo";

  export default {
    name: "Schedule",
    components: {
      ScheduleInfo
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
          Monday:'复合材料结构设计基础 文科楼236 5-12(周) 沈燕讲师,赵军副教授,郭云力',
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
      }
    },
    mounted() {

    }
  }
</script>

<style scoped>

  .table
  {
    left: 200px;
  }
</style>
