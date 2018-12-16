package com.csye6225.fall2018.courseservice667.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Student;
import com.csye6225.fall2018.courseservice667.service.StudentService;

import java.util.List;

@Path("/students")

public class StudentResource {
	StudentService studentService = new StudentService();
	
	//GET all students
	@GET
	@Path("/allstudents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentss() {
		return studentService.getAllStudents();
	}

	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId") String studentId) {
		return studentService.getStudent(studentId);
	}
	
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") String studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
			return studentService.addStudent(student);
	}
	
	@PUT 
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") String studentId, Student student) {
		return studentService.updateStudentInformation(studentId, student);
	}
	
	@POST
	@Path("/{studentId}/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student registerCourse(@PathParam("studentId") String studentId, Course course)
	{
		return studentService.registerCourse(studentId,course);
	}
	
}