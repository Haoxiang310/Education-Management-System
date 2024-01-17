package com.ems902.Dao;

import com.alibaba.fastjson.JSON;
import com.ems902.Entity.*;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public interface UserDao {

    //根据学生id查找学生
    public Student selectStuById(int sId);

    //根据教师id查找教师
    public Teacher selectTeaById(int tId);



    //判断是否已有这门课程
    public StuCourse queryIfChosen(Map<Object,Object> map);

    //更新学生密码
    public void updateStuPwd(Student student);

    //查找学生成绩
    public int checkGrade(Map<Object,Object> map);

    //更新教师密码
    public void updateTeaPwd(Teacher teacher);

    //查找所有学生的信息
    public List<Student> queryAllStudent();

    public List<Student> queryStudentBySSchool(String sSchool);

    //查找所有教师的信息
    public List<Teacher> queryAllTeacher();

    //添加学生信息
    public int insertStu(Student student);

    //添加教师信息
    public void insertTea(Teacher teacher);

    //根据课程id查找课程信息
    public Course selectCourseInfoById(int cId);

    //查找所有课程信息
    public List<Course> queryAllCourses();

    //根据教师id查找教师课程
    public List<TeaCourse> selectCourseIdByTeaId(int tId);

    //根据课程id查找教师
    public TeaCourse selectTeaIdByCourseId(int cId);

    /*StuCourse*/
    //根据学生id查找学生课程
    public List<StuCourse> selectCourseIdByStuId(int id);

    public List<ScoreCheck> queryAllScoreCheck(int tId);

    //根据课程id查找讲台学生关系
    public List<StuCourse> selectStuCourseByCourseId(int cId);

    //根据学生id查找讲台学生关系
    public StuCourse selectStuCourseByStuId(int sId);

    //向学生选课表添加选课信息
    public int addCourse(StuCourse stuCourse);

    //学生选课表删除选课信息
    public int deleteCourse(StuCourse stuCourse);

    //根据课程id查看对应课程的学生id
    public List<StuCourse> selectStuIdByCourseId(int cId);

    //更新学生成绩
    public void updateGrade(StuCourse stuCourse);

    //查看学生成绩
    public List<StuCourse> queryStuScore(StuCourse stuCourse);

    //根据课程id查找课程名称
    public String querycNameBycId(int cId);

    //通过课程id查找所有讲台
    public Course getCourseById(int cId);

    //通过课程名查找所有讲台
    public List<Course> getCourseByName(String cName);

    //通过星期数查找当天的所有讲台
    public List<Course> getCourseByDay(int day);

    //通过学期数进行排课
    public List<Course> getCourseBySemester(int semester);

    //通过节数查找该节的所有讲台
    public List<Course> getCourseByTime(int time);

    //
    public List<Course> getCourseByMajor(String major);

    //通过起始周数查找所有讲台
    public List<Course> getCourseByStartWeek(int startWeek);

    //通过结束周数查找所有讲台
    public List<Course> getCourseByEndWeek(int endWeek);

    //通过上课地点查找所有讲台
    public List<Course> getCourseByPlace(String place);

    //添加一门课程
    public int addCourseIntoCourse(Course course);

    //更新课程信息
    public int updateCourseIntoCourse(Course course);

    //从数据库中删除一门课程
    public int deleteCourseFromCourse(Course course);

    //获取指定条件的课程
    public List<Course> getCourseByMultiLimit(Map<Object,Object> map);


    //查看成绩勘误信息
    public List<ScoreCheck> TeaScoreCheck(Map<Object,Object> map);
    //添加成绩勘误
    public int ScoreCheck(ScoreCheck scoreCheck);
    //改变审核状态
    public int updateAdminCheck(ScoreCheck scoreCheck);
    //通过id获取scorecheck
    public ScoreCheck selectScoreCheckById(int selectId);
    //获取所有scorecheck
    public List<ScoreCheck> selectAllScoreCheck();
    //
    public List<ScoreCheck> getAllScoreCheck();


    /*courseChange dao*/

    //添加调课停课申请
    public int insertCourseChange(CourseChange courseChange);

    //删除调课停课申请
    public int deleteCourseChange(int ChangeId);

    //查找所有调课停课申请
    public List<CourseChange> queryCourseChange(int tId);

    //通过状态返回停调课申请表
    public List<CourseChange> queryCourseChangeByStatus(int status);

    public List<CourseChange> querySchoolCourseChangeByStatus(Map<Object,Object> map);

    public List<Teacher> queryTeaByIns(String Ins);

    public List<CourseChange> queryCourseChangeByTeaId(int tId);



    //修改停调课申请表状态
    public int updateCourseChangeStatus(Map<Object,Object> map);

    /*-----------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------
     */

    //AdminDao
    public void addAdmin(Admin admin);

    //删除教务的信息
    public void deleteAdminById(int adminId);

    //更新教务的信息
    public void updateAdminInfo(Admin admin);

    //通过id选择教务
    public Admin selectAdminById(int adminId);

    public List<Admin> selectAdminsByAdminId(int adminId);

    //查找所有教务
    public List<Admin> queryAllAdmins();


    //通过教务id该教务所属学院的课程id
    public List<AdminCourse> selectCourseIdByAdminId(int adminId);

    //通过课程名字获取讲台id
    public List<Integer> selectCourseIdByName(String cName);



/*-----------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------
     */
    //ClassroomDao
    /*course-classroom表*/

    //通过roomId查找courseId
    public int getCourseIdByRoomId(int roomId);

    //通过courseId查找roomId
    public List<CourseClassroom> getRoomIdByCourseId(int cId);

    //增加course-classroom关系
    public int addCourseAndRoom(CourseClassroom courseClassroom);

    //删除course-classroom关系
    public void deleteCourseAndRoom(CourseClassroom courseClassroom);

    //更新course-classroom关系
    public void updateCourseAndRoom(CourseClassroom courseClassroom);


    /*classroom表*/
    //添加一间教室的信息
    public int addClassroom(Classroom classroom);

    //删除一间教室的信息
    public void deleteClassroom(Classroom classroom);

    //更新一间教室的信息
    public void updateClassroomInfo(Classroom classroom);

    //通过id选择教室
    public Classroom selectClassroomById(int roomId);

    //通过教室名选择教室
    public List<Classroom> selectClassroomByPlace(String place);

    //通过周数选择教室
    public List<Classroom> selectClassroomByWeek(int week);

    //通过星期几选择教室
    public List<Classroom> selectClassroomByDay(int day);

    //通过第几节课选择教室
    public List<Classroom> selectClassroomByTime(int time);

    //通过教室容量选择教室
    public List<Classroom> selectClassroomByCapacity(int capacity);

    //通过周、星期、节数选择教室
    public List<Classroom> selectClassroomByMap(Map<Object,Object> map);

    //

    /*-----------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------
     */
    //TrainingProgramDao
    //新增教学计划
    public int setTrainingProgram(TrainingProgram trainingProgram);
    //（微调）更新教学计划，增加课程
    public int updateTrainingProgram(Map<Object,Object> map);
    //通过专业名获取教学计划
    public TrainingProgram getTrainingProgramByMajor(String major);
    //获取所有教学计划
    public List<TrainingProgram> getAllTrainingProgram();
    //删除某专业的教学计划
    public int deleteTrainingProgramByMajor(String major);



    //lesson dao
    //新增一个lesson
    public int addLesson(Lesson lesson);
    //更新一个lesson
    public int updateLessonByName(String lessonName);
    //通过名字获取lesson list
    public Lesson selectLessonByName(String lessonName);
    //通过专业名获取lesson list
    public List<Lesson> selectLessonBySchool(String lessonSchool);
    //获取所有lesson
    public List<Lesson> selectAllLesson();
    //


    //通过map更新course表信息
    public int updateCourseInfoByMap(Map<Object,Object> map);

    public Classroom selectClassroomInfoByMap(Map<Object, Object> map);





}
