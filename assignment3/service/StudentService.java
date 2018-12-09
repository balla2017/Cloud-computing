package com.csye6225.fall2018.courseservice667.service;

import java.util.*;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
import com.csye6225.fall2018.courseservice667.registerNotificationSNS;
import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Student;

public class StudentService 
{
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public StudentService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting a list of all Student 
		// GET "..webapi/students"
		public List<Student> getAllStudents() {	
			//Getting the list
			DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withIndexName("Student-StudentId")
			    .withConsistentRead(false);

		    List<Student> list =  mapper.scan(Student.class, scanExpression);
		    return list ;
		}

		
		public Student addStudent(Student student) {	
			Student s = new Student();
			s.setFirstName(student.getFirstName());
			s.setLastName(student.getLastName());
			s.setDepartment(student.getDepartment());
			s.setStudentId(student.getLastName() + "." + student.getFirstName().charAt(0));
			s.setCourseIds(student.getCourseIds());
			s.setEmailID(student.getEmailID());
			mapper.save(s);
			
			System.out.println("Item added.");
		    
		    return s;
		    
		}
		
		// Getting One Student
		public Student getStudent(String studentId) {
			List<Student> list = getStudentFromDDB(studentId);
			return list.size() != 0 ? list.get(0) : null;
		}
		
		// Deleting a student
		public Student deleteStudent(String studentId) {
			List<Student> list = getStudentFromDDB(studentId);
			Student s = null;
			if(list.size() != 0){
				s = list.get(0);
				mapper.delete(s);
				Student deleted = mapper.load(Student.class, s.getId());
				
				if (deleted == null) {
		            System.out.println(stu.toString());
		        }
			}
			
			return s;
		}
		
		// Updating Student Info
		public Student updateStudentInformation(String studentId, Student student) {	
			List<Student> list = getStudentFromDDB(studentId);
			Student pre = null;
			if(list.size() != 0) {
				pre = list.get(0);
				pre.setFirstName(student.getFirstName());
				pre.setLastName(student.getLastName());
				pre.setDepartment(student.getDepartment());
				pre.setCourseIds(student.getCourseIds());
				pre.setEmailID(student.getEmailID());
				mapper.save(pre);				
				System.out.println("Item updated.");
			}
			
			return pre;
		}
		
		// register for a course 
		public Student registerCourse(String studentId, Course course){
			List<Student> list = getStudentFromDDB(studentId);
			CourseService c = new CourseService();
			Student s = null;
			if(list.size() != 0) {
				s = list.get(0);
				
				if(s.getCourseIds().size() < 3) {
					s.getCourseIds().add(course.getCourseId());
					course.getlistOfRegisteredStudents().add(studentId);
					
					// update information in database
					updateStudentInformation(studentId,s);
					c.updateCourseInformation(course.getCourseId(), course);
					
					registerNotificationSNS.subscribe(course.getNotificationTopic(), s.getEmailID());
				}
			}
			
			return stu;
		}
	 
		public List<Student> getStudentFromDDB(String stuId)
		{
		    HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		    eav.put(":v1",  new AttributeValue().withS(stuId));

		    DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("Student-StudentId")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);

		    List<Student> list =  mapper.query(Student.class, queryExpression);
			return list;
		}
}
