import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'
import  student_index from '../pages/student/student_index';
import  teacher_index from '../pages/teacher/teacher_index';
import  school_administration_index from '../pages/school_administration/school_administration_index';
import  university_administration_index from '../pages/university_administration/university_administration_index';
import  login from'../pages/login';
import student_schedule from '../pages/student/schedule';
import teacher_schedule from '../pages/teacher/schedule';
import teacher_adjust from '../pages/teacher/adjust';
import student_class_manage from '../pages/student/class_manage';
import teacher_class_manage from '../pages/teacher/class_manage';
import teacher_student_class from '../pages/teacher/student_class';
import student_mark_manage from '../pages/student/mark_manage';
import teacher_mark_resign from '../pages/teacher/mark_resign';
import mark_register from '../pages/teacher/mark_register';
import mark_class from '../pages/teacher/mark_class';
import school_administration_class_manage from '../pages/school_administration/class_manage';
import school_administration_grade_manage from  '../pages/school_administration/grade_manage';
import school_administration_class_adjustment_check from '../pages/school_administration/school_class_adjustment_check';
import university_administration_class_adjustment_check from '../pages/university_administration/university_class_adjustment_check';
import university_administration_training_program from '../pages/university_administration/training_program';
import university_administration_course_add from '../pages/university_administration/course_add';
import university_administration_class_manage from '../pages/university_administration/class_manage';


import school_administration_class_grade from  '../pages/school_administration/grade/class_grade';
import school_administration_course_grade from  '../pages/school_administration/grade/course_grade';
import school_administration_student_grade from  '../pages/school_administration/grade/student_grade';
import school_administration_grade_check from  '../pages/school_administration/grade/grade_check';
import school_administration_courseInfo from  '../pages/school_administration/grade/courseInfo';
import school_administration_studentInfo from  '../pages/school_administration/grade/studentInfo';
import school_administration_classInfo from  '../pages/school_administration/grade/classInfo';

import university_administration_class_grade from  '../pages/university_administration/grade/class_grade';
import university_administration_course_grade from  '../pages/university_administration/grade/course_grade';
import university_administration_student_grade from  '../pages/university_administration/grade/student_grade';
import university_administration_grade_check from  '../pages/university_administration/grade/grade_check';
import university_administration_courseInfo from  '../pages/university_administration/grade/courseInfo';
import university_administration_studentInfo from  '../pages/university_administration/grade/studentInfo';
import university_administration_classInfo from  '../pages/university_administration/grade/classInfo';
import Header from '../components/Header'

import student_menu from '../pages/teacher/student_menu';

Vue.use(Router);
/*主页面子路由*/



const student_index_Children = [
  {
    path: '',
    redirect: '/student_index/student_class_manage'
  },
  {
    path: 'student_schedule',
    component: student_schedule
  },
  {
    path: 'student_class_manage',
    component: student_class_manage
  },
  {
    path:'mark_manage',
    component:student_mark_manage
  }
];
const teacher_index_Children = [
  {
    path: '',
    redirect: '/teacher_index/teacher_class_manage'
  },
  {
    path: 'teacher_schedule',
    component: teacher_schedule
  },
  {
    path: 'teacher_mark_resign',
    component: teacher_mark_resign
  },
  {
    path: 'mark_class',
    component: mark_class
  },
  {
    path: 'teacher_adjust',
    component: teacher_adjust
  },
  {
    path: 'teacher_class_manage',
    component: teacher_class_manage
  },
  {
    path:'mark_register',
    component:mark_register
  },
  {
    path:'student_menu',
    component:student_menu
  },
  {
    path:'teacher_student_class',
    component:teacher_student_class
  }
];
const school_administration_index_Children = [
  {
    path: '',
    redirect: '/school_administration_index/school_administration_class_adjustment_check'
  },

  {
    path: 'school_administration_class_manage',
    component: school_administration_class_manage
  },
  {
    path:'school_administration_grade_manage',
    component:school_administration_grade_manage,
  },

  {
    path: 'school_administration_class_adjustment_check',
    component: school_administration_class_adjustment_check
  },
  {
    path: 'school_administration_course_grade',
    component: school_administration_course_grade
  },
  {
    path:'school_administration_class_grade',
    component:school_administration_class_grade
  },
  {
    path:'school_administration_student_grade',
    component:school_administration_student_grade
  },
  {
    path:'school_administration_grade_check',
    component:school_administration_grade_check
  }
  ,
  {
    path:'school_administration_classInfo',
    component:school_administration_classInfo
  },
  {
    path:'school_administration_studentInfo',
    component:school_administration_studentInfo
  },
  {
    path:'school_administration_courseInfo',
    component:school_administration_courseInfo
  }
];

const university_administration_index_Children = [
  {
    path: '',
    redirect: '/university_administration_index/university_administration_class_adjustment_check'
  },

  {
    path: 'university_administration_class_adjustment_check',
    component: university_administration_class_adjustment_check
  },
  {
    path:'university_administration_training_program',
    component:university_administration_training_program
  },
  {
    path: 'university_administration_class_manage',
    component: university_administration_class_manage
  },
  {
    path: 'university_administration_course_grade',
    component: university_administration_course_grade
  },
  {
    path:'university_administration_class_grade',
    component:university_administration_class_grade
  },
  {
    path:'university_administration_student_grade',
    component:university_administration_student_grade
  },
  {
    path:'university_administration_grade_check',
    component:university_administration_grade_check
  },
  {
    path:'university_administration_classInfo',
    component:university_administration_classInfo
  },
  {
    path:'university_administration_studentInfo',
    component:university_administration_studentInfo
  },
  {
    path:'university_administration_courseInfo',
    component:university_administration_courseInfo
  },
  {
    path:'university_administration_course_add',
    component:university_administration_course_add
  }
];

export default new Router({
  routes: [
    {
      path: '',
      redirect:'/login'
    },
    {
      path: '/student_index',
      component: student_index,
      children: student_index_Children
    },
    {
      path: '/teacher_index',
      component: teacher_index,
      children: teacher_index_Children
    },
    {
      path: '/school_administration_index',
      component: school_administration_index,
      children: school_administration_index_Children
    },
    {
      path: '/university_administration_index',
      component: university_administration_index,
      children: university_administration_index_Children
    },
    {
      path: '/login',
      component: login
    },{
      path:'/header',
      component:Header,
      meta:{
        keepalive:false
      }
    }
  ]
})
