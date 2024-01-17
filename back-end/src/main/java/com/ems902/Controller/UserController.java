package com.ems902.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ems902.Dao.UserDao;
import com.ems902.Entity.*;
import com.ems902.Service.*;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.propertyeditors.ClassArrayEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
    @Resource
    private CourseService courseService;
    @Resource
    private TrainingProgramService trainingProgramService;
    @Resource
    private LessonService lessonService;
    @Resource
    private ClassroomService classroomService;


    @RequestMapping("/studentIndex")
    public String studentIndex(){
        return "studentIndex";
    }
    @RequestMapping("/teacherIndex")
    public String teacherIndex(){
        return "teacherIndex";
    }
    @RequestMapping("/adminIndex")
    public String adminIndex(){return "adminIndex";}

    @RequestMapping(value = "/studentIndex/checkCourse",method = RequestMethod.GET)
    @ResponseBody
    public String checkCourse(@RequestParam("stuid") int sId){
        List<Course> courses = new ArrayList<>();
        courses = userService.checkCourseTable(sId);
        List<StuCourse> stuCourses = new ArrayList<>();
        stuCourses = userService.checkStuCourses(sId);
//        String QueryRecord = JSON.toJSONString(courses)+JSON.toJSONString(stuCourses);
        return JSON.toJSONString(courses);
    }

    @RequestMapping(value = "/studentIndex/queryIfChosen",method = RequestMethod.GET)
    @ResponseBody
    public String queryIfChosen(@RequestParam("cId")int cId,@RequestParam("sId")int sId)
    {
        int temp = userService.queryIfChosen(sId, cId);
        String msg;
        if(temp==0) {
            msg ="未选";
        }
        else{
            msg = "已选";
        }
        return msg;
    }

    @RequestMapping(value = "/studentIndex/checkGrade",method = RequestMethod.GET)
    @ResponseBody
    public String checkGrade(@RequestParam("cId")int cId,@RequestParam("sId")int sId){
        int grade = userService.checkGrade(cId, sId);
        return JSON.toJSONString(grade);
    }
    @RequestMapping(value = "/studentIndex/checkAllCourses", method = RequestMethod.GET)
    @ResponseBody
    public String queryAllCourses(){
        List<Course> courses = userService.queryAllCourses();


        return JSON.toJSONString(courses);
//        return JSON.toJSONString(courses+teachers);
    }
    @RequestMapping(value = "/studentIndex/queryTeacher",method = RequestMethod.GET)
    @ResponseBody
    public String queryTeacher(@RequestParam("cId")int cId){

        return JSON.toJSONString(userService.selectTeacherByCourseId(cId));
    }

    @RequestMapping(value = "/studentIndex/chooseCourse",method = RequestMethod.GET)
    @ResponseBody
    public String chooseCourse(@RequestParam("sId") int sId, @RequestParam("cId") int cId, HttpServletResponse httpServletResponse){
        int temp = userService.chooseClass(sId,cId);
        String msg = "";
        httpServletResponse.setCharacterEncoding("UTF-8");
        if(temp == 1) {
            msg = "成功添加课程";
            System.out.println(msg);
        }
        else if (temp==0){
            msg = "时间冲突，选课失败";
        }
        else if (temp==2) msg = "您的年级不符合要求，选课失败";
        else if(temp ==3) msg = "您的专业不符合要求，选课失败";
        else if(temp==4) msg="您的年级和专业均不符合要求，选课失败";
        else if(temp==5) msg="选课失败";
        return msg;
    }

    @RequestMapping(value = "/studentIndex/cancelCourse",method = RequestMethod.GET)
    @ResponseBody
    public String cancelCourse(@RequestParam("sId") int sId,@RequestParam("cId") int cId){
        userService.cancelClass(sId,cId);
        String msg = "成功删除课程";
        return JSON.toJSONString(msg);
    }


    @RequestMapping(value = "/teacherIndex/checkCourse",method = RequestMethod.GET)
    @ResponseBody
    public String checkTeaCourse(@RequestParam("teaid") int tId){
        List<Course> courses = new ArrayList<>();
        courses = userService.checkTeaCourseTable(tId);
        List<TeaCourse> teaCourses = new ArrayList<>();
       courses= userService.checkTeaCourseTable(tId);
       return JSON.toJSONString(courses);
    }

    @RequestMapping(value = "/teacherIndex/checkStudent",method = RequestMethod.GET)
    @ResponseBody
    public String checkCourseStu(@RequestParam("courseid") int cId){
        List<Student> students = new ArrayList<>();
        students = userService.checkCourseStu(cId);
        return JSON.toJSONString(students);
    }

    @RequestMapping(value = "/teacherIndex/queryStuScore",method = RequestMethod.GET)
    @ResponseBody
    public String queryStuScore(@RequestParam("sId")int sId, @RequestParam("cId")int cId){
        List<StuCourse> stuCourses = userService.queryStuScore(sId, cId);
        return JSON.toJSONString(stuCourses);
    }

    @RequestMapping(value = "/teacherIndex/updateGrade",method = RequestMethod.GET)
    @ResponseBody
    public String updateGrade(@RequestParam("stuId") int sId,@RequestParam("courseId") int cId,
                              @RequestParam("usual_grade") int usual_grade,@RequestParam("final_grade") int final_grade){
        userService.updateGrade(sId,cId,usual_grade,final_grade);
        String msg = "成功更新成绩";
        return JSON.toJSONString(msg);
    }
    @RequestMapping(value="/teacherIndex/SubmitScoreCheck",method = RequestMethod.GET)
    @ResponseBody
    public String SubmitScoreCheck(@RequestParam("sId")int sId,@RequestParam("tId")int tId,
                                   @RequestParam("cId")int cId,@RequestParam("old_score")int old_score,
                                   @RequestParam("new_score")int new_score,
                                   @RequestParam("old_usual_score")int old_usual_score,
                                   @RequestParam("old_final_score")int old_final_score,
                                   @RequestParam("new_usual_score")int new_usual_score,
                                   @RequestParam("new_final_score")int new_final_score,
                                   @RequestParam("changeReason")String changeReason){
        userService.ScoreCheck(sId, tId, cId, old_score, new_score,old_usual_score,
                old_final_score,new_usual_score,new_final_score,changeReason);
        String msg = "成功插入成绩勘误信息";
        return JSON.toJSONString(msg);
    }
    @RequestMapping(value = "/teacherIndex/TeaScoreCheck",method = RequestMethod.GET)
    @ResponseBody
    public String TeaScoreCheck(@RequestParam("cId")int cId,@RequestParam("tId")int tId){
        List<ScoreCheck> scoreChecks = userService.TeaScoreCheck(cId,tId);
        return JSON.toJSONString(scoreChecks);
    }

    @RequestMapping(value = "/teacherIndex/queryAllScoreCheck",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllScoreCheck(@RequestParam("tId") int tId){
        return JSON.toJSONString(userService.queryAllScoreCheck(tId));
    }

    @RequestMapping(value = "/teacherIndex/updateCourseRequirement",method = RequestMethod.GET)
    @ResponseBody
    public String updateCourseRequirement(@RequestParam("cId") int cId,@RequestParam("cName") String cName,@RequestParam("grade") int grade,@RequestParam("class_") String class_,
                                          @RequestParam("major") String major, @RequestParam("semester") int semester, @RequestParam("credit") int credit,
                                          @RequestParam("place") String place, @RequestParam("day")int day,@RequestParam("time") int time,
                                          @RequestParam("startweek") int startweek, @RequestParam("endweek") int endweek,@RequestParam("cType") String cType){
        Course course = new Course(cId,cName,cType,grade,class_,major,semester,credit,
                place,day,time,startweek,endweek);
        userService.updateCourseRequirement(course);
        String msg = "成功设置课程要求";
        return JSON.toJSONString(msg);
    }

    @RequestMapping(value = "/teacherIndex/changeCourseTime",method = RequestMethod.POST)
    @ResponseBody
    public String insertCourseChange(@RequestParam("cId")int cId,@RequestParam("tId")int tId,@RequestParam("reason")String reason,
                                     @RequestParam("old_week")int old_week,@RequestParam("old_day")int old_day,
                                     @RequestParam("old_time")int old_time,@RequestParam("new_week")int new_week,
                                     @RequestParam("new_day")int new_day,@RequestParam("new_time")int new_time,
                                     @RequestParam("old_place")String old_place,@RequestParam("new_place")String new_place){
        CourseChange courseChange = new CourseChange(cId,tId,reason,old_place,new_place,old_week,old_day,old_time,new_week,new_day,new_time,0);
        userService.insertCourseChange(courseChange);
        String msg = "insert Course Change Successfully";
        return JSON.toJSONString(msg);
    }

    @RequestMapping(value = "/teacherIndex/deleteChangeCourseTime",method = RequestMethod.POST)
    @ResponseBody
    public String deleteCourseChange(@RequestParam("changeId") int changeId){
        userService.deleteCourseChange(changeId);
        String msg = "delete Course Change Successfully";
        return JSON.toJSONString(msg);
    }

    @RequestMapping(value = "/teacherIndex/queryChangeCourseTime",method = RequestMethod.GET)
    @ResponseBody
    public String queryCourseChange(@RequestParam("tId")int tId){
        List<CourseChange> courseChanges = new ArrayList<>();
        courseChanges = userService.queryCourseChange(tId);
        return JSON.toJSONString(courseChanges);
    }

    @RequestMapping(value = "/teacherIndex/querycNameBycId",method = RequestMethod.GET)
    @ResponseBody
    public String querycNameBycId(@RequestParam("cId")int cId){
        return JSON.toJSONString(userService.querycNameBycId(cId));
    }


    /*  ------------------- Admin Index -----------------  */

    /*
    * @name queryCourseChangeByStatus
    * @ref 通过前端给的状态返回停调课表信息
    * @interface /adminIndex/queryCourseChangeByStatus
    * @method GET
    * @param
    * int status    //审批状态
    * int adminId   //教务id
    * @return
    * List<CourseChange>
    * */
    @RequestMapping(value = "/adminIndex/queryCourseChangeByStatus",method = RequestMethod.GET)
    @ResponseBody
    public String queryCourseChangeByStatus(@RequestParam("status")int status,
                                            @RequestParam("adminId") int adminId){
        return JSON.toJSONString(userService.queryCourseChangeByStatus(status, adminId));
    }

    /*
    * @name changeCourseChangeStatusById
    * @ref 更改给定id的停调课表状态
    * @interface /adminIndex/changeCourseChangeStatusById
    * @method GET
    * @param
    * int status    //要更改的审批状态
    * int changeId  //更改的审批表id
    * @return
    * string
    * */
    @RequestMapping(value = "/adminIndex/changeCourseChangeStatusById",method = RequestMethod.GET)
    @ResponseBody
    public String changeCourseChangeStatusById(@RequestParam("status")int status,
                                               @RequestParam("changeId") int changeId){
        int res = userService.changeCourseChangeStatus(changeId,status);
        if(res == 1) return "CourseChange " + changeId + " Status update success!";
        else return "CourseChange " + changeId + " Status update failed!";
    }

    /*Admin controller*/
    /*查询某课程所有讲台的成绩*/
    /*
    * @name queryAllCourseScore
    * @ref 查询某课程所有讲台的成绩
    * @interface /adminIndex/queryAllCourseScore
    * @method GET
    * @param String cName
    * returnParam
    * courseId  //课程id
    * stuId     //学生id
    * stuName   //学生姓名
    * grade     //学生成绩
    * */
    @RequestMapping(value = "/adminIndex/queryAllCourseScore",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllCourseScore(@RequestParam("cName")String cName){
        List<StuCourse> stuCourseList = adminService.getAllCourseScoreByName(cName);

        List<Map<Object,Object>> mapList = new ArrayList<>();

        for (StuCourse stuCourse : stuCourseList) {

            Map<Object,Object> map = new HashMap<>();//改到循环里面6.20
            map.put("courseId",stuCourse.getcId());
            map.put("stuId",stuCourse.getsId());
            map.put("stuName",userService.getStuNameById(stuCourse.getsId()));
            map.put("grade",stuCourse.getGrade());
            mapList.add(map);
        }
        return JSON.toJSONString(mapList);
    }

    /* 接口功能：
     * 查询某个讲台所有学生成绩
     * @name       querySingleCourseScore
     * @INTERFACE /adminIndex/querySingleCourseScore
     * @METHOD    GET
     * 接受数据：
     * @param int cId
     * 返回数据格式：
     * courseName   //课程名
     * grade        //成绩
     * stuId
     * stuName
     *
     * 返回数据类型JSON
     *  */
    @RequestMapping(value = "/adminIndex/querySingleCourseScore",method = RequestMethod.GET)
    @ResponseBody
    public String querySingleCourseScore(@RequestParam("cId") int cId){
        List<StuCourse> stuCourseList = adminService.getSingleCourseScoreById(cId);
        List<Map<Object,Object>> mapList = new ArrayList<>();
//        Map<Object,Object> map = new HashMap<>(); 改到循环里面6.20

        for (StuCourse stuCourse : stuCourseList) {
            Map<Object,Object> map = new HashMap<>();
            Course course = courseService.queryCourseInfoById(stuCourse.getcId());
            String stuName = userService.getStuNameById(stuCourse.getsId());

            map.put("courseName",course.getcName());
            map.put("stuId",stuCourse.getsId());
            map.put("stuName",stuName);
            map.put("grade",stuCourse.getGrade());
            mapList.add(map);
        }
        return JSON.toJSONString(mapList);

    }

    /*接口功能：
     * 查询学生所有课程的成绩，返回成绩单
     * @name queryStuAllCourse
     * @interface /adminIndex/queryStuAllCourse
     * @method GET
     * 接受数据：
     * int sId
     * 返回数据格式：
     * String stuName
     * int sId
     * String cName
     * int cId
     * int score
     * 返回数据类型JSON
     *  */

    @RequestMapping(value = "/adminIndex/queryStuAllCourse",method = RequestMethod.GET)
    @ResponseBody
    public String queryStuAllCourse(@RequestParam("sId") int sId){
        List<StuCourse> stuCourseList = adminService.getStuAllCourse(sId);//知道score，cId
        List<ScoreList> scoreLists = new ArrayList<>();

        for (StuCourse stuCourse : stuCourseList) {
            Course course = courseService.queryCourseInfoById(stuCourse.getcId());
            scoreLists.add(new ScoreList(
                    userService.getStuNameById(sId),
                    sId,
                    course.getcName(),
                    stuCourse.getcId(),
                    stuCourse.getGrade()));
        }
        return JSON.toJSONString(scoreLists);
    }

    /*TrainingProgram  Controller*/
    /*向已有的教学计划中新增课程信息*/
    /*
    * @name addNewLessonToTrainingProgram
    * @interface /adminIndex/addNewLessonToTrainingProgram
    * @method GET
    * @param
    * String major
      String lessonName
      String lessonSchool
      String cType
      int grade
      int semester
      int credit
      int startweek
      int endweek
    *
    * @return
    * */
    @RequestMapping(value = "/adminIndex/addNewLessonToTrainingProgram",method = RequestMethod.GET)
    @ResponseBody
    public String addNewLessonToTrainingProgram(@RequestParam("major")            String major,
                                                @RequestParam("lessonName")       String lessonName,
                                                @RequestParam("lessonSchool")     String lessonSchool,
                                                @RequestParam("cType")            String cType,
                                                @RequestParam("grade")            int grade,
                                                @RequestParam("semester")         int semester,
                                                @RequestParam("credit")           int credit,
                                                @RequestParam("startweek")        int startweek,
                                                @RequestParam("endweek")          int endweek){
        TrainingProgram trainingProgram = trainingProgramService.getMajorTrainingProgram(major);
        if(trainingProgram == null){
            return "cannot find this major";
        }
        Lesson lesson = new Lesson(lessonName, lessonSchool, cType, grade, semester, credit, startweek, endweek);
        int res0 = lessonService.addNewLesson(lesson);

        String lessonString = trainingProgram.getLessonList();
        if (lessonString == null)
            //trainingProgram.setLessonList(lessonStringJson);//如果之前没有课程，则不加分号
            trainingProgram.setLessonList(JSON.toJSONString(lesson));//如果之前没有课程，则不加分号
        else
            trainingProgram.setLessonList(lessonString + ";" + JSON.toJSONString(lesson)); //之前有课程，在课程之间添加；，形如“{...};{...};{...}”

        int res = trainingProgramService.updateTrainingProgramCourse(trainingProgram.getLessonList(),major);//将信息更新到数据库中
        if (res == 1) return "set new lesson success";
        else return "set lesson failed";
    }

    /*TrainingProgram  Controller*/
        /*创建一个新的教学计划*/
    /*
    * @name setNewTrainingProgram
    * @interface /adminIndex/setNewTrainingProgram
    * @method GET
    * @param:
    * String major,
    * String admission,
    * int lengthOfSchooling
    * */
    @RequestMapping(value = "/adminIndex/setNewTrainingProgram",method = RequestMethod.GET)
    @ResponseBody
    public String setNewTrainingProgram(/*@RequestParam("programId")        int programId,*/
                                        @RequestParam("major")            String major,
                                        @RequestParam("admission")        String admission,
                                        @RequestParam("lengthOfSchooling")int lengthOfSchooling
                                       /* @RequestParam("lessonStringJson") String lessonStringJson*/){
        /*TrainingProgram trainingProgram = new TrainingProgram(programId,major,admission,lengthOfSchooling,lessonStringJson);*/
        TrainingProgram trainingProgram = new TrainingProgram(major,admission,lengthOfSchooling);
        int res = trainingProgramService.setTrainingProgram(trainingProgram);
        if (res > 0) return "set success";
        else return "set failure";
    }


    /*TrainingProgram  Controller*/
    /* 获取所有的教学计划*/
    /*
     * @name queryAllTrainingProgram
     * @interface /adminIndex/queryAllTrainingProgram
     * @method GET
     * @param:
     * null
     * return:
     * List<TrainingProgram>
     * */
    @RequestMapping(value = "/adminIndex/queryAllTrainingProgram",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllTrainingProgram(){
        return JSON.toJSONString(trainingProgramService.getAllTrainingProgram());
    }

    /*Course  Controller*/
    /*
    * @name queryAllStuInfo
    * @ref 获取所有学生信息
    * @interface /adminIndex/queryAllStuInfo
    * @method GET
    * @param:
    * int adminId
    * @return:
    * String List<Student>
    *
    * */
    @RequestMapping(value = "/adminIndex/queryAllStuInfo",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllStuInfo(@RequestParam("adminId") int adminId){
        return JSON.toJSONString(userService.getAllStuInfo(adminId));
    }

    /* 删除某个教学计划*/
    /*
     * @name deleteMajorTrainingProgram
     * @interface /adminIndex/deleteMajorTrainingProgram
     * @method GET
     * @param:
     * string major
     * return:
     * success :"delete " + major + "training program success"
     * failed :"delete " + major + "training program failed"
     * */
    @RequestMapping(value = "/adminIndex/deleteMajorTrainingProgram",method = RequestMethod.GET)
    @ResponseBody
    public String deleteMajorTrainingProgram(@RequestParam("major") String major){
        int res = trainingProgramService.deleteMajorTrainingProgram(major);
        if(res > 0) return "delete " + major + "training program success";
        else return "delete " + major + "training program failed";
    }




    /*
     * @name queryAllCourseByCourseName
     * @ref 通过课程名获取所有讲台表
     * @interface /adminIndex/queryAllCourseByCourseName
     * @method GET
     * @param:
     * String cName 课程名
     * @return:
     * String List<Course>
     *
     * */
    @RequestMapping(value = "/adminIndex/queryAllCourseByCourseName",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllCourseByCourseName(@RequestParam("cName") String cName){
        List<Course> courseList = courseService.queryCourseInfoByName(cName);
        return JSON.toJSONString(courseList);
    }

    /*
     * @name queryAllLessons
     * @ref 获取所有课程
     * @interface /adminIndex/queryAllLessons
     * @method GET
     * @return:
     * String List<Lesson>
     *
     * */
    @RequestMapping(value = "/adminIndex/queryAllLessons",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllLessons(){
        List<Lesson> lessonList = lessonService.queryAllLesson();
        return JSON.toJSONString(lessonList);
    }


    /*
     * @name queryAllLessonsBySchool
     * @ref 获取指定学院的所有课程
     * @interface /adminIndex/queryAllLessonsBySchool
     * @method GET
     * @param:
     * String lessonSchool
     * @return:
     * String List<Lesson>
     *
     * */
    @RequestMapping(value = "/adminIndex/queryAllLessonsBySchool",method = RequestMethod.GET)
    @ResponseBody
    public String queryAllLessonsBySchool(@RequestParam("lessonSchool") String lessonSchool){
        List<Lesson> lessonList = lessonService.queryLessonBySchool(lessonSchool);
        return JSON.toJSONString(lessonList);
    }


    /*
     * @name addNewLesson
     * @ref 新增一个课程
     * @interface /adminIndex/addNewLesson
     * @method GET
     * @param
     * String lessonName
     * String lessonSchool,
     * String cType
     * int grade
     * int semester
     * int credit
     * int startweek
     * int endweek
     * @return:
     * "add new lesson success"
     * "add new lesson failed"
     * */
    @RequestMapping(value = "/adminIndex/addNewLesson",method = RequestMethod.GET)
    @ResponseBody
    public String addNewLesson(@RequestParam("lessonName") String lessonName,
                               @RequestParam("lessonSchool") String lessonSchool,
                               @RequestParam("cType") String cType,
                               @RequestParam("grade") int grade,
                               @RequestParam("semester") int semester,
                               @RequestParam("credit") int credit,
                               @RequestParam("startweek") int startweek,
                               @RequestParam("endweek") int endweek){
        Lesson lesson = new Lesson(lessonName, lessonSchool, cType, grade, semester, credit, startweek, endweek);
        int res = lessonService.addNewLesson(lesson);
        if(res == 1){
            return "add new lesson success";
        }
        else return "add new lesson failed";
    }

    /*
     * @name queryEmptyClassroom
     * @ref 查询是否有空教室
     * @interface /adminIndex/queryEmptyClassroom
     * @method GET
     * @param:
     * String place
       int startweek
       int endweek
       int day
       int time
     * @return:
     * String
     *
     * */
    @RequestMapping(value = "/adminIndex/queryEmptyClassroom",method = RequestMethod.GET)
    @ResponseBody
    public String queryEmptyClassroom(@RequestParam("place") String place,
                                      @RequestParam("startweek") int startweek,
                                      @RequestParam("endweek") int endweek,
                                      @RequestParam("day") int day,
                                      @RequestParam("time") int time){
        List<String> clashWeekList = classroomService.judgeEmptyClassroom(place,startweek,endweek,day,time);
        return JSON.toJSONString(clashWeekList);
    }

    /*
 * @name setClassroomAndCourseInfo
 * @ref 找到教室后，调用这个接口，设置讲台的教室，同时更新course、course-classroom、classroom三个表
 * @interface /adminIndex/setClassroomAndCourseInfo
 * @method GET
 * @param:
 * int cId
   String place
   int startweek
   int endweek
   int day
   int time
 * @return:
 * "set classroom success!"
 * "set classroom failed!"
 * */
    @RequestMapping(value = "/adminIndex/setClassroomAndCourseInfo",method = RequestMethod.GET)
    @ResponseBody
    public String setClassroomAndCourseInfo(@RequestParam("cId") int cId,
                                            @RequestParam("place") String place,
                                            @RequestParam("startweek") int startweek,
                                            @RequestParam("endweek") int endweek,
                                            @RequestParam("day") int day,
                                            @RequestParam("time") int time){
        int res = classroomService.setClassroom(cId, place , startweek, endweek, day, time);
        if(res == 1) return "set classroom success!";
        else return "set classroom failed!";

    }




    /*
     * @name updateScoreCheckAdminCheck
     * @ref 更新scorecheck的管理员状态
     * @interface /adminIndex/updateScoreCheckAdminCheck
     * @method GET
     * @param:
     * int checkId
       int adminCheck
     * @return:
     * String
     * "update adminCheck success"
     * "update adminCheck failed"
     * */
    @RequestMapping(value = "/adminIndex/updateScoreCheckAdminCheck",method = RequestMethod.GET)
    @ResponseBody
    public String updateScoreCheckAdminCheck(@RequestParam("checkId") int checkId,
                                             @RequestParam("adminCheck") int adminCheck){
        int res = adminService.updateAdminCheck(checkId,adminCheck);
        if(res > 0) return "update adminCheck success";
        else return "update adminCheck failed";

    }


    /*
    * @name getAllScoreCheckByAdmin
    * @ref admin获取所有scorecheck信息
    * @interface /adminIndex/getAllScoreCheckByAdmin
    * @method GET
    * @param:
    * int adminId
    * @return:
    *  List<ScoreCheck>
    *
    * */
    @RequestMapping(value = "/adminIndex/getAllScoreCheckByAdmin",method = RequestMethod.GET)
    @ResponseBody
    public String getAllScoreCheckByAdmin(@RequestParam("adminId") int adminId){
        List<ScoreCheck> scoreCheckList = userService.getAllScoreCheck(adminId);
        return JSON.toJSONString(scoreCheckList);

    }

    /*
     * @name getAdminByAdminId
     * @ref 根据id获取admin
     * @interface /adminIndex/getAdminByAdminId
     * @method GET
     * @param:
     * int adminId
     * @return:
     *  List<Admin>
     *
     * */
    @RequestMapping(value = "/adminIndex/getAdminByAdminId",method = RequestMethod.GET)
    @ResponseBody
    public String getAdminByAdminId(@RequestParam("adminId") int adminId){
        List<Admin> adminList = adminService.getAdminById(adminId);
        return JSON.toJSONString(adminList);

    }

    /*
     * @name getMajorCourse
     * @ref 根据adminId 获取course
     * @interface /adminIndex/getMajorCourse
     * @method GET
     * @param:
     * int adminId
     * @return:
     *  List<Course>
     * @ref：学院教务没有公选课（”公选“）
     * */
    @RequestMapping(value = "/adminIndex/getMajorCourse",method = RequestMethod.GET)
    @ResponseBody
    public String getMajorCourse(@RequestParam("adminId") int adminId){

        List<Admin> adminList = adminService.getAdminById(adminId);
        List<Course> courseList = new ArrayList<>();
        for (Admin admin : adminList) {

            if(admin.getAdminSchool().equals("school")){
                courseList = userService.queryAllCourses();
            }
            else{
                courseList = adminService.getCourseByMajor(admin.getAdminSchool());
            }
        }
        courseList.removeIf(course -> course.getcType().equals("公选"));

        return JSON.toJSONString(courseList);

    }





}
