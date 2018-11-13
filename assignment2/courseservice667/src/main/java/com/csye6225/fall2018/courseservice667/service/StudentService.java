package com.csye6225.fall2018.courseservice667.service;

import java.util.*;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
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
	
	// Getting One Student
		public Student getStudent(String studentId) {
			List<Student> studentList = mapper.scan(Student.class, new DynamoDBScanExpression()); 
			Student retrievedStud = new Student(); 
			for (Student student : studentList) {
				if (student.getStudentId().equals(studentId)) {
					retrievedStud = student; 
				     System.out.println("Student got:");
				     System.out.println(student.toString());
				}
			}

			return retrievedStud;
		}
		
		//Getting one list of students 
		public List<Student> getAllStudents() {	
			//Getting the list
			List<Student> studentList = mapper.scan(Student.class, new DynamoDBScanExpression()); 
			System.out.println("The student list is: ");
			System.out.println(studentList.toString());
			return studentList ;
		}
		
		// Get students in a department 
		public List<Student> getStudentsByDepartment(String department) {	
			 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			  eav.put(":val1", new AttributeValue().withS(department));
			  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("department = :val1").withExpressionAttributeValues(eav);
			  List<Student> s = mapper.scan(Student.class, scanExpression);
			  if(s.size() ==0) {
				  return null;
			  }
			return s ;
		}

		// Adding a student
		public Student addStudent(String id, String studentId, String firstName, String lastName, 
				String department, String joiningDate, List<String> registeredCourses) {
			Student s = new Student(id, studentId, firstName, lastName, department, joiningDate, registeredCourses);
			mapper.save(s);
			 System.out.println("Student added:");
		     System.out.println(s.toString());
			return s;

		}
		
		public Student addStudent(Student student) {	
			mapper.save(student);
			System.out.println("Student added.");
		    System.out.println(student.toString());
			return student;	
		}
		

		// Deleting a student
		public Student deleteStudent(String studentId) {
			List<Student> studentList = mapper.scan(Student.class, new DynamoDBScanExpression()); 
			Student student = new Student(); 
			for (Student s : studentList) {
				if (s.getStudentId().equals(studentId)){
					student = s; 
					mapper.delete(s);
				}
			}
			 System.out.println("Student deleted.");
		     System.out.println(student.toString());
			return student;
		}
		
		// Updating Student Info
		public Student updateStudentInformation(String studentId, Student student) {
			List<Student> studentList = mapper.scan(Student.class, new DynamoDBScanExpression());
			for (Student s2 : studentList) 
			{
				if (s2.getStudentId().equals(studentId)) 
				{
					mapper.delete(s2);
					mapper.save(student);
					 System.out.println("Student updated.");
				     System.out.println(student.toString());
				}
			}
			return student;
		}
}