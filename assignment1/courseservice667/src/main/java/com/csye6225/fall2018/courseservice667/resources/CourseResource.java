package com.csye6225.fall2018.courseservice667.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Lecture;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;
import com.csye6225.fall2018.courseservice667.datamodel.Student;
import com.csye6225.fall2018.courseservice667.service.CourseService;

import java.util.List;


@Path("/classes")
public class CourseResource {
    public static CourseService courseService = CourseService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourse(){
        return courseService.getAllCourses();
    }
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourseById(@PathParam("courseId") String courseId){
        return courseService.getCourseById(courseId);
    }

    @GET
    @Path("/{courseId}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getRoster(@PathParam("courseId") String courseId){
        return courseService.getRoster(courseId);
    }

    @GET
    @Path("/{courseId}/lectures")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lecture> Lectures(@PathParam("courseId") String courseId){
        return courseService.getLectures(courseId);
    }
    
    @GET
    @Path("/{courseId}/professor")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor(@PathParam("courseId") String courseId){
        return courseService.getProfessor(courseId);
    }
    
    @GET
    @Path("/{courseId}/studentTA")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentTA(@PathParam("courseId") String courseId){
        return courseService.getStudentTA(courseId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course addCourse(Course course){
        return courseService.addCourse(course);
    }

    @PUT
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("courseId") String courseId,Course course){
        course.setCourseId(courseId);
        return courseService.updateCourse(course);
    }

    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") String courseId){
        return courseService.deleteCourse(courseId);
    }
}