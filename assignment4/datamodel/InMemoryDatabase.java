package com.csye6225.fall2018.courseservice667.datamodel;

import java.util.HashMap;

public class InMemoryDatabase 
{

	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long,Student> students = new HashMap<>();
    private static HashMap<String,Course> courses = new HashMap<>();
    private static HashMap<String, Board> board = new HashMap<> ();
    private static HashMap<String, Announcement> announcement = new HashMap<> ();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<Long,Student> getStudentDB()
	{ 
		return students; 
	}
	
    public static HashMap<String,Course> getCourseDB()
    { 
    	return courses; 
    }
    
    public static HashMap<String, Board> getBoardDB() {
		return board;
	}
	public static HashMap<String, Announcement> getAnnouncementDB() {
		return announcement;
	}
    
	public static void setAnnouncementDB(HashMap<String, Announcement> announcement) {
		InMemoryDatabase.announcement = announcement;
	}

}
