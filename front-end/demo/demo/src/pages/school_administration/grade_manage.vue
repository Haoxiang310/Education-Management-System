<template>
  <div>

    <el-container>
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title" @click="GotoClassGrade()">课程成绩</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title" @click="GotoStudentGrade()">学生成绩</span>
          </el-menu-item>
          <el-menu-item index="3" @click="GotoGradeCheck()">
            <i class="el-icon-document"></i>
            <span slot="title">成绩审核</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
          </el-menu-item>
        </el-menu>
      </el-aside>


      <el-main>
        <el-table
          :data="tableData"
          class="table"
          max-height="800">
          <el-table-column
            fixed="left"
            prop="number"
            label="课程编号"
            width="150">
          </el-table-column>
          <el-table-column
            prop="name"
            label="授课教师"
            width="120">
          </el-table-column>
          <el-table-column
            prop="address"
            label="授课地点"
            width="120">
          </el-table-column>
          <el-table-column
            prop="hours"
            label="课程学时"
            width="120">
          </el-table-column>
          <el-table-column
            prop="credit"
            label="课程学分"
            width="120">
          </el-table-column>
          <el-table-column
            label="操作"
            width="120">
            <template slot-scope="scope">
              <el-button
                plain="true"
                @click="open"
                type="text"
                size="small">
                选课
              </el-button>
              <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                plain="true"
                @click="move"
                type="text"
                size="small">
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>




  </div>
</template>

<script>
  export default {
    name: "choose",
    methods: {
      open() {
        this.$message('选课成功!');
      },
      move() {
        this.$message('课程移除成功!');
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      },
      GotoClassGrade(){
        this.$router.push("/school_administration_index/school_administration_class_grade");

      },
      GotoStudentGrade(){
        this.$router.push("/school_administration_index/school_administration_student_grade");
      },
      GotoGradeCheck(){
        this.$router.push("/school_administration_index/school_administration_grade_check")
      }
    },
    data() {
      return {
        dialogVisible:false,
        tableData: [{
          number: '11111111',
          name: '啸哥',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '22222222',
          name: '笑锟',
          address: '机电楼',
          hours: '48学时',
          credit: '2.5学分',
        }, {
          number: '33333333',
          name: '逸帆大师',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '44444444',
          name: '笋昊翔',
          address: '教学楼',
          hours: '32学时',
          credit: '2学分',
        }, {
          number: '55555555',
          name: '崔火山',
          address: '机电楼',
          hours: '16学时',
          credit: '1.5学分',
        }, {
          number: '66666666',
          name: '林lucy',
          address: '逸夫楼',
          hours: '48学时',
          credit: '2.5学分',
        }, {
          number: '11111111',
          name: '啸哥',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '22222222',
          name: '笑锟',
          address: '机电楼',
          hours: '48学时',
          credit: '2.5学分',
        }, {
          number: '33333333',
          name: '逸帆大师',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '44444444',
          name: '笋昊翔',
          address: '教学楼',
          hours: '32学时',
          credit: '2学分',
        }, {
          number: '55555555',
          name: '崔火山',
          address: '机电楼',
          hours: '16学时',
          credit: '1.5学分',
        }, {
          number: '66666666',
          name: '林lucy',
          address: '逸夫楼',
          hours: '48学时',
          credit: '2.5学分',
        },{
          number: '11111111',
          name: '啸哥',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '22222222',
          name: '笑锟',
          address: '机电楼',
          hours: '48学时',
          credit: '2.5学分',
        }, {
          number: '33333333',
          name: '逸帆大师',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '44444444',
          name: '笋昊翔',
          address: '教学楼',
          hours: '32学时',
          credit: '2学分',
        }, {
          number: '55555555',
          name: '崔火山',
          address: '机电楼',
          hours: '16学时',
          credit: '1.5学分',
        }, {
          number: '66666666',
          name: '林lucy',
          address: '逸夫楼',
          hours: '48学时',
          credit: '2.5学分',
        },{
          number: '11111111',
          name: '啸哥',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '22222222',
          name: '笑锟',
          address: '机电楼',
          hours: '48学时',
          credit: '2.5学分',
        }, {
          number: '33333333',
          name: '逸帆大师',
          address: '逸夫楼',
          hours: '64学时',
          credit: '3学分',
        }, {
          number: '44444444',
          name: '笋昊翔',
          address: '教学楼',
          hours: '32学时',
          credit: '2学分',
        }, {
          number: '55555555',
          name: '崔火山',
          address: '机电楼',
          hours: '16学时',
          credit: '1.5学分',
        }, {
          number: '66666666',
          name: '林lucy',
          address: '逸夫楼',
          hours: '48学时',
          credit: '2.5学分',
        }]
      }
    }
  }
</script>

<style scoped>
  .table{
    left:150px;
  }
</style>
