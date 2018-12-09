package com.csye6225.fall2018.courseservice667.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
import com.csye6225.fall2018.courseservice667.EmailAnnouncement;
import com.csye6225.fall2018.courseservice667.registerNotificationSNS;
import com.csye6225.fall2018.courseservice667.datamodel.Board;
import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;
import com.csye6225.fall2018.courseservice667.datamodel.Student;

public class CourseService 
{
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public CourseService(){
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());		
	}
		
	// Getting a list of all course 
	// GET "..webapi/courses"
	public List<Course> getAllCourses() {	
		//Getting the list
	   DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    .withIndexName("Course-CourseId")
		    .withConsistentRead(false);
	   List<Course> list =  mapper.scan(Course.class, scanExpression);
       return list ;
   }
	
	// Adding a course
	public Course addCourse(Course course) {
		String topic=registerNotificationSNS.createTopic("course"+course.getId());
	    Course c2 = new Course();
	    c2.setCourseId(course.getCourseId());
	    c2.setBoardId(course.getBoardId());
	    c2.setDepartment(course.getDepartment());
	    c2.setProfessorId(course.getProfessorId());
	    c2.setTaId(course.getTaId());
	    c2.setlistOfRegisteredStudents(course.getlistOfRegisteredStudents());
	    c2.setNotificationTopic(topic);
		mapper.save(c2);
		
		//add board to database
		Board b=new Board(c2.getId(),2.getBoardId(),c2.getCourseId());
		BoardService bs=new BoardService();
		bs.addBoard(b);
			
		System.out.println("Added successfully!");
		    
		return c2;
   }
	
	// Getting One course
    public Course getCourse(String courseId) {
		List<Course> list = getCourseFromDDB(courseId);
		return list.size() != 0 ? list.get(0) : null;
	}
		
	// Deleting a course
	public Course deleteCourse(String courseId) {
		List<Course> list = getCourseFromDDB(courseId);
		Course c = null;
		if(list.size() != 0){
			c = list.get(0);
			mapper.delete(c);
			Course deletedCourse = mapper.load(Course.class, c.getId());
				
			if (deletedCourse == null) {
		        System.out.println("deleted.");
		    }
		}
			
	 return c;
	}
		
	// Updating course Info
	public Course updateCourseInformation(String courseId, Course course) {	
			List<Course> list = getCourseFromDDB(courseId);
			Course pre = null;
			if(list.size() != 0) {
				pre = list.get(0);
				pre.setBoardId(course.getBoardId());
				pre.setDepartment(course.getDepartment());
				pre.setProfessorId(course.getProfessorId());
				pre.setTaId(course.getTaId());
				pre.setlistOfRegisteredStudents(course.getlistOfRegisteredStudents());
				pre.setNotificationTopic(course.getNotificationTopic());

				mapper.save(oldCourse);
				System.out.println("updated:");
			    System.out.println(pre.toString());
			}		
			return pre;
		}
	
		
	// helper function 
	public List<Course> getCourseFromDDB(String courseId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));
		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
			.withIndexName("Course-CourseId")
			.withConsistentRead(false)
		    .withKeyConditionExpression("courseId = :v1")
			.withExpressionAttributeValues(eav);
	    List<Course> list =  mapper.query(Course.class, queryExpression);
	    return list;
   }
}
