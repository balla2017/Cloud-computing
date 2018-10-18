package com.csye6225.fall2018.courseservice667.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Student;
import com.csye6225.fall2018.courseservice667.service.StudentService;

import java.util.List;

@Path("/students")

public class StudentResource {

    public static StudentService stuService = StudentService.getInstance();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents(){
        return stuService.getAllStudents();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student addStudent(Student student){
        return stuService.addStudent(student);
    }

    @GET
    @Path("/{studentId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@PathParam("studentId") long studentId){
        return stuService.getCourses(studentId);
    }
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentById(@PathParam("studentId") long studentId){
        return stuService.getStudentById(studentId);
    }

    @PUT
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") long studentId,Student student){
        student.setStudentId(studentId);
        return stuService.updateStudent(student);
    }
    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStudent(@PathParam("studentId") long studentId){
        stuService.deleteStudent(studentId);
    }

}