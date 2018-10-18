package com.csye6225.fall2018.courseservice667.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;
import com.csye6225.fall2018.courseservice667.datamodel.Program;
import com.csye6225.fall2018.courseservice667.datamodel.Student;
import com.csye6225.fall2018.courseservice667.service.ProgramService;


@Path("/programs")
public class ProgramResource {
    public static ProgramService programService = ProgramService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Program> getAllCourse(){
        return programService.getAllPrograms();
    }
    @GET
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program getProgramById(@PathParam("programId") long programId){
        return programService.getProgramById(programId);
    }

    @GET
    @Path("/{programId}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudents(@PathParam("programId") long programId){
        return programService.getStudents(programId);
    }

    @GET
    @Path("/{programId}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(@PathParam("programId") long programId){
        return programService.getCourses(programId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program addProgram(Program program){
        return programService.addProgram(program);
    }

    @PUT
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program updateCourse(@PathParam("programId") long programId,Program program){
        program.setProgramId(programId);
        return programService.updateProgram(program);
    }

    @DELETE
    @Path("/{programId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program deleteCourse(@PathParam("programId") long programId){
        return programService.deleteProgram(programId);
    }

}