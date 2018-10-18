package com.csye6225.fall2018.courseservice667.service;

import java.util.*;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice667.datamodel.Student;

public class StudentService {

    private static Map<Long,Student> students = InMemoryDatabase.getStudents();
    private static StudentService studentService;
    private StudentService(){

        Course c1 = new Course("csye6225","c1");
        Course c2 = new Course("info6205","c2");
        Course c3 = new Course("cs5600","c3");
        Student s1 = new Student(10001L,"Balla","Information System");
        Student s2 = new Student(10002L,"S2","Program2");
        Student s3 = new Student(10003L,"S3","Program3");
        Student s4 = new Student(10004L,"S4","Program4");
        Student s5 = new Student(10005L,"S5","Program5");
        Student s6 = new Student(10006L,"S6","Program6");

        List<Course> l1=new ArrayList<Course>();
        List<Course> l2=new ArrayList<Course>();
        List<Course> l3=new ArrayList<Course>();
        l1.add(c1);
        l2.add(c2);
        l3.add(c3);
        s1.setCourseEnrolled(l2);
        s2.setCourseEnrolled(l2);
        s3.setCourseEnrolled(l1);
        s4.setCourseEnrolled(l1);
        s5.setCourseEnrolled(l3);
        s6.setCourseEnrolled(l3);

        students.put(s1.getStudentId(),s1);
        students.put(s2.getStudentId(),s2);
        students.put(s3.getStudentId(),s3);
        students.put(s4.getStudentId(),s4);
        students.put(s5.getStudentId(),s5);
        students.put(s6.getStudentId(),s6);
    }
    public static StudentService getInstance(){

        if (studentService == null){
            studentService = new StudentService();
        }
        return studentService;
    }

    //get all the students
    public List<Student> getAllStudents(){

        return new ArrayList<Student>(students.values());
    }

    //Adding one student
    public Student addStudent(Student student){
        student.setStudentId(students.size()+108010080);
        students.put(student.getStudentId(),student);
        return student;
    }

    //Getting one student by id
    public Student getStudentById(long id){
        return students.get(id);
    }

    //Getting courses by student id
    public List<Course> getCourses(long studentId){
        return getStudentById(studentId).getCourseEnrolled();
    }

    //Updating one student by id
    public Student updateStudent(Student student){
        if (student.getStudentId() <= 0){
            return null;
        }
        students.put(student.getStudentId(),student);
        return student;
    }
    //Deleting one student by id
    public Student deleteStudent(long id){
        return students.remove(id);
    }

}