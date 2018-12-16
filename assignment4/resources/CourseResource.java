package com.csye6225.fall2018.courseservice667.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;
import com.csye6225.fall2018.courseservice667.datamodel.Student;
import com.csye6225.fall2018.courseservice667.service.CourseService;

import java.util.List;


@Path("/courses")
public class CourseResource {
	CourseService courseService = new CourseService();	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses() {
		return courseService.getAllCourses();
	}
	
	// ... webapi/courses/0001 
	@GET
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourse(@PathParam("courseId") String courseId) {
		return courseService.getCourse(courseId);
	}

	@DELETE
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("courseId") String courseId) {
		return courseService.deleteCourse(courseId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course) {
			return courseService.addCourse(course);
	}
	
	@PUT
	@Path("/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("courseId") String courseId, Course course) {
		return courseService.updateCourseInformation(courseId, course);
	}
}