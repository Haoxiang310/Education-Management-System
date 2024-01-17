<template>
  <div>
  <el-table
    :data="tableData"
    style="width: 80%"
    max-height="800"
    class="stable"
    id="score_table">
    <el-table-column
      label="课程编号"
      prop="id"
      width="150">
    </el-table-column>
    <el-table-column
      label="课程名称"
      prop="name"
      width="150">
    </el-table-column>
    <el-table-column
      label="课程类型"
      prop="type"
      width="180">
    </el-table-column>
    <el-table-column
      label="课程学分"
      prop="credit"
      width="180">
    </el-table-column>
    <el-table-column
      label="课程分数"
      prop="grade"
      width="180">
    </el-table-column>
  </el-table>
    <div style="margin: 50px 0;"></div>
    <div >
      <div>{{message1}}{{bixiu_credit}}&#160&#160&#160&#160&#160{{message2}}{{gongxuan_credit}}&#160&#160&#160&#160&#160{{message3}}{{zhuanyuan_credit}}</div>
    </div>
    <div style="margin: 80px 0;"></div>
    <el-button  type="success" plain @click="exportExcel()" >导出成绩单</el-button>
    <el-button v-print="table" type="success" plain>打印成绩单</el-button>
  </div>
</template>

<script>
  import FileSaver from 'file-saver'
  import XLSX from 'xlsx'
  import axios from 'axios'
  export default {
    mounted(){
      this.get_score();
    },
    data() {
      return {
        gongxuan_credit:'',
        bixiu_credit:'',
        zhuanxuan_credit:'',
        tableData: [],
        search: '',
        message1:'已修的必修课学分为:',
        message2:'已修的公选课学分为:',
        message3:'已修的专选课学分为:',
      }
    },
    methods: {
      table(){},
      exportExcel() {
        let wb = XLSX.utils.table_to_book(document.querySelector('#score_table'))
        let wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' })
        try {
          FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'Battery.xlsx')
        } catch (e) { if (typeof console !== 'undefined') console.log(e, wbout) }
        return wbout
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      get_score()
      {
        console.log(localStorage.getItem("studentId"));
        axios({
          methods: 'get',
          url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/checkCourse',
          params: {
            'stuid':localStorage.getItem("studentId")
          },
        }).then((res)=>{
          console.log(res.data);

          let i;
          for(i=0;i<res.data.length;i++)
          {
            let temp=res.data[i].cId;
            let t={
              id: '',
              name: '',
              type:'',
              credit:'',
              grade:''
            };
            t.id=res.data[i].cId;
            t.name=res.data[i].cName;
            t.type=res.data[i].cType;
            t.credit=res.data[i].credit;
            axios({
              methods: 'get',
              url: 'http://222.28.46.89:8080/EduManagementSystem_war3/studentIndex/checkGrade',
              params: {
                'cId':temp,
                'sId':localStorage.getItem("studentId")
              },
            }).then((res) => {
                  t.grade=res.data;
                  if(t.grade>=60&&t.type=="公选"){
                  this.gongxuan_credit=Number(this.gongxuan_credit)+Number(t.credit);
                  }
                  else if(t.grade>=60&&t.type=="必修") {
                    this.bixiu_credit=Number(this.bixiu_credit)+Number(t.credit);
                  }
                  else if(t.grade>=60&&t.type=="专选") {
                    this.zhuanxuan_credit=Number(this.zhuanxuan_credit)+Number(t.credit);
                  }
            })
            this.tableData.push(t);

          }
        })

      }
    },
  }
</script>
<style scoped>
  .stable{
    left:250px;
  }
</style>
