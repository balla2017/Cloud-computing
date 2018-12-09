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
	    Course course2 = new Course();
	    course2.setCourseId(course.getCourseId());
	    course2.setBoardId(course.getBoardId());
	    course2.setDepartment(course.getDepartment());
	    course2.setProfessorId(course.getProfessorId());
	    course2.setTaId(course.getTaId());
	    course2.setlistOfRegisteredStudents(course.getlistOfRegisteredStudents());
	    course2.setNotificationTopic(topic);
		mapper.save(course2);
		
		//add board to database
		Board b=new Board(course2.getId(),course2.getBoardId(),course2.getCourseId());
		BoardService bs=new BoardService();
		bs.addBoard(b);
			
		System.out.println("Board added:");
		System.out.println(course2.toString());
		    
		return course2;
   }
	
	// Getting One course
    public Course getCourse(String courseId) {
		List<Course> list = getCourseFromDDB(courseId);
		return list.size() != 0 ? list.get(0) : null;
	}
		
	// Deleting a course
	public Course deleteCourse(String courseId) {
		List<Course> list = getCourseFromDDB(courseId);
		Course cour = null;
		if(list.size() != 0){
			cour = list.get(0);
			mapper.delete(cour);
			Course deletedCourse = mapper.load(Course.class, cour.getId());
				
			if (deletedCourse == null) {
		        System.out.println("deleted.");
		        System.out.println(cour.toString());
		    }
		}
			
	 return cour;
	}
		
	// Updating course Info
	public Course updateCourseInformation(String courseId, Course course) {	
			List<Course> list = getCourseFromDDB(courseId);
			Course oldCourse = null;
			if(list.size() != 0) {
				oldCourse = list.get(0);
				oldCourse.setBoardId(course.getBoardId());
				oldCourse.setDepartment(course.getDepartment());
				oldCourse.setProfessorId(course.getProfessorId());
				oldCourse.setTaId(course.getTaId());
				oldCourse.setlistOfRegisteredStudents(course.getlistOfRegisteredStudents());
				oldCourse.setNotificationTopic(course.getNotificationTopic());

				mapper.save(oldCourse);
				System.out.println("updated:");
			    System.out.println(oldCourse.toString());
			}		
			return oldCourse;
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
