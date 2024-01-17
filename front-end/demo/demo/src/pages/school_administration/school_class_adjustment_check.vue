<template>
  <div >
    <el-table
      :data="tableData"
      style="width: 100%"
      class="table"
      max-height="800">
      <el-table-column
        prop="tName"
        label="授课教师"
        width="120">
      </el-table-column>
      <el-table-column
        prop="cName"
        label="课程名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="oldTime"
        label="原授课时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="oldPlace"
        label="原授课地点"
        width="120">
      </el-table-column>
      <el-table-column
        prop="newTime"
        label="新授课时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="newPlace"
        label="新授课地点"
        width="120">
      </el-table-column>
      <el-table-column
        prop="reason"
        label="申请理由"
        width="120">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="120">
        <template slot-scope="scope">
          <el-button
            @click="pass(scope.$index, scope.row)"
            type="text"
            size="small">
            同意
          </el-button>
          <el-button
            @click="deny(scope.$index, scope.row)"
            type="text"
            size="small">
            不同意
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
    name: "choose",
    methods: {
      getCourseChange(){
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/queryCourseChangeByStatus',
          params: {
            'status':0,
            'adminId':localStorage.getItem("adminId"),
          },
        }).then((res)=>{
          console.log(res.data);
          let i;
          for(i=0;i<res.data.length;i++)
          {
            let temp={
              tId: '',
              tName:'',
              cId:'',
              cName:'',
              changeId:'',
              oldTime:'',
              oldPlace:'',
              newTime:'',
              newPlace:'',
              reason: '',
              day:''
            };
            switch (res.data[i].old_day) {
              case 1:temp.day='一' ;break;
              case 2:temp.day='二' ;break;
              case 3:temp.day='三' ;break;
              case 4:temp.day='四' ;break;
              case 5:temp.day='五' ;break;
              case 6:temp.day='六' ;break;
              case 7:temp.day='日' ;break;

            }
            temp.changeId=res.data[i].changeId;
            temp.tId=res.data[i].tId;
            temp.cId=res.data[i].cId;
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/queryTeacher',
              params: {
                'cId':temp.cId
              }
            }).then((res)=>{
                temp.tName=res.data.tName;
              }
            );
            temp.oldTime='第'+res.data[i].old_week+'周周'+temp.day+'第'+res.data[i].old_time+'节课';
            switch (res.data[i].new_day) {
              case 1:temp.day='一' ;break;
              case 2:temp.day='二' ;break;
              case 3:temp.day='三' ;break;
              case 4:temp.day='四' ;break;
              case 5:temp.day='五' ;break;
              case 6:temp.day='六' ;break;
              case 7:temp.day='日' ;break;

            }
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/teacherIndex/querycNameBycId',
              params: {
                'cId':temp.cId,
              },

            }).then((res)=>{
              console.log(res.data);
              temp.cName=res.data;
            });
            temp.newTime='第'+res.data[i].new_week+'周周'+temp.day+'第'+res.data[i].new_time+'节课';
            temp.oldPlace=res.data[i].old_place;
            temp.newPlace=res.data[i].new_place;
            temp.reason=res.data[i].reason;
            this.tableData.push(temp);
          }



        })
      },
      pass(index,row) {
        this.$confirm('此操作将通过该申请, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/changeCourseChangeStatusById',
            params: {
              'status':1,
              'changeId':row.changeId,
            },

          }).then((res)=>{
            console.log(res.data);
            if(res.data=='CourseChange '+row.changeId+' Status update success!'){
              this.$message({
                type: 'success',
                message: '提交成功!'
              });
              this.reload();

            }
            else if(res.data=='CourseChange '+row.cId+' Status update failed!'){
              this.$message({
                type: 'error',
                message: '提交失败!'
              });
            }


          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          });
        });



      },
      deny(index,row){
        this.$confirm('此操作将拒绝该申请, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios({
            methods: 'get',
            url: 'http://222.28.46.89:8080/EduManagementSystem_war3/adminIndex/changeCourseChangeStatusById',
            params: {
              'status':-1,
              'changeId':row.changeId,
            },

          }).then((res)=>{
            console.log(res.data);
            if(res.data=='CourseChange '+row.changeId+' Status update success!'){
              this.$message({
                type: 'success',
                message: '提交成功!'
              });
              this.reload();

            }
            else if(res.data=='CourseChange '+row.cId+' Status update failed!'){
              this.$message({
                type: 'error',
                message: '提交失败!'
              });
            }


          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交'
          });
        });

      }

    },
    data() {
      return {

        tableData: []
      }
    },
    created(){
      this.getCourseChange();
    }
  }
</script>

<style scoped>

</style>
