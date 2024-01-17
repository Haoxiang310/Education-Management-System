<template>
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
          @click="open(scope.$index, scope.row)"
          type="text"
          size="small"
          :disabled=scope.row.xuan_state>
          选课
        </el-button>
        <el-button
          @click="close(scope.$index, scope.row)"
          size="small"
          type="danger"
          :disabled=scope.row.tui_state
          >
          退课
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "choose",
    mounted(){
      this.get_course();
    },

    data(){
      return {
        dialogVisible:false,
        tabled:[],
        tableData: []

      }
    },
    methods: {
      get_course()
      {
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/checkAllCourses',

        }).then((res)=>{
          console.log(res.data);
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
              xuan_state:false,
              tui_state:true
            };
            b.cId=res.data[i].cId;
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/queryIfChosen',
              params: {
                'cId':b.cId,
                'sId':localStorage.getItem("studentId")
              }
            }).then((res)=>{
                if(res.data=="已选")
                {
                  b.xuan_state=true;
                  b.tui_state=false;
                }
                else if(res.data=="未选")
                {
                }
            });
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
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/chooseCourse',
          params: {
            'sId':localStorage.getItem("studentId"),
            'cId':row.cId
          },
        }).then((res)=>{
          console.log(res.data);
          if("成功添加课程"== res.data){
            this.$message('选课成功!');
            console.log(index);
            console.log(row);
            row.xuan_state=true;
            row.tui_state=false;
          }else if("时间冲突"== res.data)
          {
            this.$message('时间冲突,选课失败!');
          }
          else if("您的专业不符合要求"== res.data)
          {
              this.$message('您的专业不符合要求,选课失败!');
          }
          else if("您的年级不符合要求"== res.data)
          {
            this.$message('您的年级不符合要求,选课失败!');
          }
          else if("您的年级和专业均不符合要求")
          {
            this.$message('您的年级和专业均不符合要求,选课失败!');
          }
        })
      },
      close(index,row) {
        row.xuan_state=false;
        row.tui_state=true;

        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/cancelCourse',
          params: {
            'sId':localStorage.getItem("studentId"),
            'cId':row.cId
          },
        }).then((res)=>{
          console.log(res.data);
          if("成功删除课程"== res.data){
            this.$message('退课成功!');
          }
        });
        console.log(index, row);
      },

    }
  }

</script>

<style scoped>
.table{
  left:0px;
}
</style>
