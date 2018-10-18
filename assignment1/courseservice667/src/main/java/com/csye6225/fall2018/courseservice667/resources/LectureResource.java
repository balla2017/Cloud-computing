package com.csye6225.fall2018.courseservice667.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Lecture;
import com.csye6225.fall2018.courseservice667.service.LectureService;

import java.util.List;

@Path("/lectures")
public class LectureResource {

    public static LectureService lectureService = LectureService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lecture> getAllLectures(){
        return lectureService.getAllLectures();
    }

    @GET
    @Path("/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture getLectureById(@PathParam("lectureId") long lectureId){
        return lectureService.getLectureById(lectureId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture addLecture(Lecture lecture){
        return lectureService.addLecture(lecture);
    }

    @PUT
    @Path("/{lectureId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture updateLectureById(@PathParam("lectureId") long lectureId,Lecture lecture){
        lecture.setLectureId(lectureId);
        return lectureService.updateLecture(lecture);
    }

    @DELETE
    @Path("/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture deleteLecture(@PathParam("lectureId") long lectureId){
        return lectureService.deleteLecture(lectureId);
    }

}
