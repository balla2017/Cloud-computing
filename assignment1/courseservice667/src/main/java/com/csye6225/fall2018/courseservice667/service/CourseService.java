package com.csye6225.fall2018.courseservice667.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice667.datamodel.Lecture;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;
import com.csye6225.fall2018.courseservice667.datamodel.Student;

public class CourseService {
    private Map<String,Course> courses = InMemoryDatabase.getCourses();
    private static CourseService courseService;
    public CourseService() {
  
        Lecture w1 = new Lecture(10001L,"lecture1");
        Lecture w2 = new Lecture(10002L,"lecture2");
        Lecture w3 = new Lecture(10003L,"lecture3");
        Student s1 = new Student(10001L,"student1");
        Student s2 = new Student(10002L,"student2");
        Student ta1 = new Student(10003L,"studentTA1");
        Student ta2 = new Student(10004L,"studentTA2");
        Student ta3 = new Student(10005L,"studentTA3");
        Professor p1 = new Professor(10001L,"professor1","cs",new Date());
        Professor p2 = new Professor(10002L,"professor2","cs",new Date());
        Professor p3 = new Professor(10003L,"professor3","cs",new Date());
    
        Course course1 = new Course("csye6225","course1",
                Arrays.asList(s1,s2),
                Arrays.asList(w1,w2,w3),"board1",p1,ta1);
        Course course2 = new Course("info6205","course2",
                Arrays.asList(s1,s2),
                Arrays.asList(w1,w2),"board2",p2,ta3);
        Course course3 = new Course("cs5600","course3",
                Arrays.asList(s1,s2),
                Arrays.asList(w1),"board3",p3,ta3);
 
        courses.put(course1.getCourseId(),course1);
        courses.put(course2.getCourseId(),course2);
        courses.put(course3.getCourseId(),course3);
    }

    public static CourseService getInstance(){
        if (courseService == null){
            courseService = new CourseService();
        }
        return courseService;
    }
    //  ../courses
    public List<Course> getAllCourses(){
        return new ArrayList<>(courses.values());
    }

    // ../courses/{course_id}
    public Course getCourseById(String id){
        return courses.get(id);
    }
    // ../courses/course_id/students
    public List<Student> getRoster(String courseId){
        return getCourseById(courseId).getRoster();
    }
    // ../courses/course_id/lectures
    public List<Lecture> getLectures(String courseId){
        return getCourseById(courseId).getLectures();
    }
    // ../courses/course_id/professor
    public Professor getProfessor(String courseId){
        return getCourseById(courseId).getProfessor();
    }
    // ../courses/course_id/studentTA
    public Student getStudentTA(String courseId){
        return getCourseById(courseId).getStudentTA();
    }

    //add course by id
    public Course addCourse(Course course){
        return courses.put(course.getCourseId(),course);
    }

    //Updating one course by id
    public Course updateCourse(Course course){
        if (course.getCourseId() == null || course.getCourseId().length() ==0 ){
            return null;
        }
        return courses.put(course.getCourseId(),course);
    }

    //Deleting one course by id
    public Course deleteCourse(String id){
        return courses.remove(id);
    }
}

