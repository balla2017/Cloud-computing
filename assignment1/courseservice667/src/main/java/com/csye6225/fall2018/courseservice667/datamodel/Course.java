package com.csye6225.fall2018.courseservice667.datamodel;

import java.util.List;

public class Course {
    private String courseId;
    private String courseName;
    private List<Student> roster;
    private List<Lecture> lectures;
    private String board;
    private Professor professor;
    private Student studentTA;

    public Course(){

    }
    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Course(String courseId, String courseName, List<Student> roster, List<Lecture> lectures, String board,Professor professor,Student studentTA) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.roster = roster;
        this.lectures = lectures;
        this.board = board;
        this.professor=professor;
        this.studentTA=studentTA;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getRoster() {
        return roster;
    }

    public void setRoster(List<Student> roster) {
        this.roster = roster;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
    
    public Professor getProfessor()
    {
    	return professor;
    }
    
    public void setProfessor(Professor p)
    {
    	this.professor=p;
    }
    
    public Student getStudentTA()
    {
    	return studentTA;
    }
    
    public void setStudentTA(Student s)
    {
    	this.studentTA=s;
    }
}