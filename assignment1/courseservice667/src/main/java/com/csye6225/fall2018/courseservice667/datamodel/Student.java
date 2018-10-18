package com.csye6225.fall2018.courseservice667.datamodel;

import java.util.List;
public class Student {

    private long studentId;
    private String name;
    private String image;
    private List<Course> courseEnrolled;
    private String programEnrolled;

    public Student(){

    }
    public Student(long id, String name) {
        this.studentId = id;
        this.name = name;
    }

    public Student(long studentId, String name, String programName) {
        this.name=name;
        this.studentId = studentId;
        this.programEnrolled = programName;
    }

    public String getName() {
        return name;
    }
    
    public void setFirstName(String name) {
        this.name=name;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Course> getCourseEnrolled() {
        return courseEnrolled;
    }

    public void setCourseEnrolled(List<Course> courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    public String getProgramName() {
        return programEnrolled;
    }

    public void setProgramName(String programName) {
        this.programEnrolled = programName;
    }
}
