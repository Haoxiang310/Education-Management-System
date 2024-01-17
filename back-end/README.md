# 用户表

用户id（uID）：int

用户姓名（uName）：varchar

用户密码（uPwd）：varchar

用户角色（uRole）：

# 学生

学生（sID）：int

年级（grade）：int

班级（class）：varchar

所属学院（sSchool）：varchar

专业（major）：string

应修学分（reqCredit）：int

已修学分（gainCredit）：int

# 讲台表

课程id（cID）：int

课程名（cName）：varchar

年级（grade）：int

班级（class）：varchar

专业（major）：varchar

所属学期（semester）：int

该课程学分（credit）：int

上课地点（place）：varchar

上课时间（time）：

上课周数（weeks）：

任课老师（teacher）：varchar（姓名/id）

先修课要求（preClassName）：varchar

先修课成绩（preClassScore）：int

# 讲台-学生表

课程id（cID）：int

学生学号（sID）：int

学生成绩（score）：int

# 教师表

教师id（tID）：int

所属学院（tSchool）：varchar



# 讲台-教师表

讲台id（cID）：int

教师id（tID）：int

讲台状态（cState）：int（停课/调课/未开课/已开课）

# 学院教务表

教务id（eID）：int

教务所在学院（eSchool）：

# 学校教务表

（继承学院教务）

排公选课

# 成绩勘误表

课程id（cID）：int

学生学号（sID）：int

教师id（tID）：int

学生成绩（score）：int

勘误理由（reason）：varchar

# 停调课申请表

教师id（tID）：int

课程id（cID）：int

停课还是调课：int（0：停课；1：调课）

申请理由（reason）：varchar

停课原时间（preTime）

停课现时间（nowTime）

# 培养方案表

专业（major）：int

年级（grade）：int

培养方案（scheme）：list

# 排课表

课程id（cID）

时间（cTime）

上课地点（cPlace）


### 数据库

```
mysql-5.7.34
```

### Maven

```apache-maven-3.8.1
apache-maven-3.8.1
```

### Tomcat

```xml
apache-tomcat-8.5.66
```

### JDK

```xml
jdk1.8.0_251
```

### Spring

```xml
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.12.RELEASE</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.12.RELEASE</version>
        </dependency>


    </dependencies>
```

### MyBatis

```xml
3.4.1
```

### 服务器

阿里云

### 前端

JSP ,VUE





