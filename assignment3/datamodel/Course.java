package com.csye6225.fall2018.courseservice667.datamodel;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import javax.xml.bind.annotation.XmlRootElement;
import com.csye6225.fall2018.courseservice667.EmailAnnouncement;

@DynamoDBTable(tableName="course")
public class Course {
	private String id;
	private String courseId;
	private String professorId;
	private String taId;
	private String department;
	private String boardId;
	private List<String> listOfRegisteredStudents;
	private String notificationTopic;
	
	public Course() {
		
	}


	@DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    public String getId() { return id; }
    public void setId(String id) { this.id = id; } 
    
   
    @DynamoDBIndexHashKey(globalSecondaryIndexName="Course-CourseId",attributeName="courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}


	@DynamoDBAttribute(attributeName="professorId")
	public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}


	@DynamoDBAttribute(attributeName="taId")
	public String getTaId() {
		return taId;
	}

	public void setTaId(String studentTaId) {
		this.taId = studentTaId;
	}

	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName="boardId")
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boradId) {
		this.boardId = boradId;
	}

	@DynamoDBAttribute(attributeName="listOfRegisteredStudents")
	public List<String> getlistOfRegisteredStudents() {
		return listOfRegisteredStudents;
	}

	public void setlistOfRegisteredStudents(List<String> studentIds) {
		this.listOfRegisteredStudents = studentIds;
	}
	
	@DynamoDBAttribute(attributeName="notificationTopic")
	public String getNotificationTopic()
	{
		return notificationTopic;
	}
	
	public void setNotificationTopic(String notificationTopic)
	{
		this.notificationTopic=notificationTopic;
	}
	   
	@DynamoDBIgnore
	@Override
	public String toString() { 
		return "courseId=" + getCourseId() + ", professorId=" + getProfessorId();
	}


}