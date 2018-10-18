package com.csye6225.fall2018.courseservice667.datamodel;

import java.util.List;

public class Program {

    private long programId;
    private String programName;
    private List<Student> enrolledStudents;
    private List<Course> courses;

    public Program(){

    }
    public Program(long programId, String programName) {
        this.programId = programId;
        this.programName = programName;
    }

    public Program(long programId, String programName, List<Student> enrolledStudents, List<Course> courses) {
        this.programId = programId;
        this.programName = programName;
        this.enrolledStudents = enrolledStudents;
        this.courses = courses;
    }

    public long getProgramId() {
        return programId;
    }

    public void setProgramId(long programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}