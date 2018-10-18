package com.csye6225.fall2018.courseservice667.datamodel;

import java.util.HashMap;

public class InMemoryDatabase 
{

	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long,Student> students = new HashMap<>();
    private static HashMap<String,Course> courses = new HashMap<>();
    private static HashMap<Long,Program> programs = new HashMap<>();
    private static HashMap<Long,Lecture> lectures = new HashMap<>();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<Long,Student> getStudents()
	{ 
		return students; 
	}
	
    public static HashMap<String,Course> getCourses()
    { 
    	return courses; 
    }
    
    public static HashMap<Long,Program> getPrograms()
    {
        return programs;
    }
    
    public static HashMap<Long, Lecture> getLectures() 
    { 
    	return lectures; 
    }
    
    public static void setProfessorDB(HashMap<Long, Professor> p) {
        InMemoryDatabase.professorDB = p;
    }
    
    public static void setStudents(HashMap<Long, Student> students) {
        InMemoryDatabase.students = students;
    }

    public static void setCourses(HashMap<String, Course> courses) {
    	InMemoryDatabase.courses = courses;
    }

    public static void setPrograms(HashMap<Long, Program> programs) {
    	InMemoryDatabase.programs = programs;
    }

    public static void setLectures(HashMap<Long, Lecture> lectures) {
    	InMemoryDatabase.lectures = lectures;
    }
}
