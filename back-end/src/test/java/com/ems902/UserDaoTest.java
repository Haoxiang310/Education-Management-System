package com.ems902;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ems902.Controller.UserController;
import com.ems902.Dao.UserDao;
import com.ems902.Entity.*;
import com.ems902.Service.Impl.UserServiceImpl;
import com.ems902.Service.UserService;
import com.ems902.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.ui.Model;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    @Test
    public void insertStu(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int res = userDao.insertStu(new Student("耿昊翔",5,"123455",3,"4","cs","cs",140,138));
        if(res>0) System.out.println("插入成功");
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void queryStuC() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Course> cs = new ArrayList<>();
        List<StuCourse> res = userDao.selectCourseIdByStuId(3);

        for(StuCourse i:res){
            Course temp = userDao.selectCourseInfoById(i.getcId());
            System.out.println(temp.getcName());
        }
//            Course course = userDao.selectCourseInfoById(i.getcId());
//            System.out.println(course.getcName());
//            System.out.println(userDao.selectCourseInfoById(i.getcId()).getcName());
//            System.out.println(res.get(1).getcId());

}

    @Test
    public void sele() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Course cs = userDao.selectCourseInfoById(2);
        System.out.println(cs.getcName());
    }

    @Test
    public void addStu() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        StuCourse stuCourse = new StuCourse(4,2,0);
        userDao.addCourse(stuCourse);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteStu() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        StuCourse stuCourse = new StuCourse(4,2);
        userDao.deleteCourse(stuCourse);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectTeaC() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Course> cs = new ArrayList<>();
        List<TeaCourse> res = userDao.selectCourseIdByTeaId(1);

        for(TeaCourse i:res){
            Course temp = userDao.selectCourseInfoById(i.getcId());
            System.out.println(temp.getcName());
        }
    }

    @Test
    public void queryStuIdByCourseId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Course> cs = new ArrayList<>();
        List<StuCourse> res = userDao.selectStuIdByCourseId(1);

        for (StuCourse i : res) {
            Student temp = userDao.selectStuById(i.getsId());
            System.out.println(temp.getName());
        }
    }

    @Test
    public void testGetCourseByName() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<Course> courses = userDao.getCourseByName("c++");
        System.out.println(courses.get(0).getPlace());
    }

    @Test
    public void updateGrade() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        StuCourse stuCourse = new StuCourse(4,1,98,30,68);
        userDao.updateGrade(stuCourse);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateCourseInfo() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Course course = new Course(1,"c++","专选",2,"2","auto",2,3,"机电204",1,1,9,16);
        int res = userDao.updateCourseIntoCourse(course);
        if(res>0) System.out.println("插入成功");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void coursechangeinsert() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        CourseChange courseChange = new CourseChange(1,1,"ill","if504","if202",10,1,1,10,2,3,0);
        userDao.insertCourseChange(courseChange);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryGrade() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<StuCourse>stuCourses = userDao.queryStuScore(new StuCourse(4,1));
        System.out.println(stuCourses.get(0).getUsual_grade());
    }
    //    @Test
//    public void scorecheck() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        ScoreCheck scoreCheck = new ScoreCheck(4,1,1,98,96,"error");
//        userDao.ScoreCheck(scoreCheck);
//        sqlSession.commit();
//        sqlSession.close();
//    }


    @Test
    public void getCbyId() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Course course = userDao.getCourseById(1);
        System.out.println(course.getcName());
    }

    @Test
    public void addadmin() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
       /* Admin admin = new Admin(1,"cs");
        userDao.addAdmin(admin);
        sqlSession.commit();
         sqlSession.close();*/
    }

    @Test
    public void getCidByRid() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.getCourseIdByRoomId(1));
    }

    @Test
    public void getmajor() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.getTrainingProgramByMajor("cs").getLengthOfSchooling());
    }

    @Test
    public void queryTeachinfo() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.selectTeaIdByCourseId(1).gettId());
    }

    @Test
    public void queryGrade1() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Map<Object,Object> map = new HashMap<>();
        map.put("cId",1);
        map.put("sId",4);
        System.out.println(userDao.checkGrade(map));
    }

    @Test
    public void scoreCheck() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        ScoreCheck scoreCheck =  new ScoreCheck(0,0,0,0,0,0,0,0,0,"error",0);
        userDao.ScoreCheck(scoreCheck);
        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void getScore() {
//        List<Lesson> lessonList = new ArrayList<>();
//        Lesson lesson = new Lesson(1,"c++","scce","专业课",1,2,3,1,16);
//        lessonList.add(lesson);
//        lesson = new Lesson(2,"java","scce","专选课",4,2,3,9,16);
//        lessonList.add(lesson);
//
//        for (Lesson lesson1 : lessonList) {
//            System.out.println(JSON.toJSONString(lesson1));
//        }

        /*String s = "[{\"cType\":\"专业课\",\"credit\":3,\"endweek\":16,\"grade\":1,\"lessonId\":1,\"lessonName\":\"c++\",\"lessonSchool\":\"scce\",\"semester\":2,\"startweek\":1},{\"cType\":\"专选课\",\"credit\":3,\"endweek\":16,\"grade\":4,\"lessonId\":2,\"lessonName\":\"java\",\"lessonSchool\":\"scce\",\"semester\":2,\"startweek\":9}]";
*/        /*JSON json = new JSONObject();*/
        String jsonData = "{'cType':'professional','credit':3,'endweek':16,'grade':1,'lessonId':1,'lessonName':'c++','lessonSchool':'scce','semester':2,'startweek':1}";
        jsonData += ";{'cType':'专选课','credit':3,'endweek':8,'grade':1,'lessonId':2,'lessonName':'computer','lessonSchool':'scce','semester':1,'startweek':1}";
        //System.out.println(jsonData);

        String ref[] = jsonData.split(";");
        for (String s : ref) {
            System.out.println(s);
        }


//        JSONObject jsonObj = JSONObject.parseObject(jsonData);
//        Lesson lesson = JSONObject.parseObject(jsonData,Lesson.class);
//        System.out.println(lesson);


    }

    @Test

    public void userControllerTest(){
        /*UserController userController = new UserController();
        String s = userController.queryAllCourseScore("c++");
        System.out.println(s);*/
    }

    @Test
    public void courseChangeTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Map<Object,Object> map = new HashMap<>();
        map.put("changeId",1);
        map.put("status",1);
        userDao.updateCourseChangeStatus(map);
        sqlSession.commit();

    }

    @Test
    public void classroomTest(){
        /*SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Classroom classroom = new Classroom("if101",8,8,8,30);
        userDao.addClassroom(classroom);
        sqlSession.commit();

        *//*CourseClassroom courseClassroom = new CourseClassroom(1,2);
        userDao.addCourseAndRoom(courseClassroom);*//*


        System.out.println(classroom.getRoomId());*/
    }



}