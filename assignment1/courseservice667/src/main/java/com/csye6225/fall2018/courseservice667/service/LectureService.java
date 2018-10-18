package com.csye6225.fall2018.courseservice667.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.csye6225.fall2018.courseservice667.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice667.datamodel.Lecture;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;

public class LectureService {
    public static Map<Long,Lecture> lectures = InMemoryDatabase.getLectures();
    private static LectureService lectureService;
    public LectureService(){
    	Professor p1=new Professor(1L,"Bill","Information System",new Date());
		Professor p2=new Professor(2L,"Balla","Information System",new Date());
        Lecture l1 = new Lecture(100001L,"name1", "material1", "notes1", "semster1", p1);
        Lecture l2 = new Lecture(100002L,"name2", "material2", "notes2", "semster2", p1);
        Lecture l3 = new Lecture(100003L,"name3", "material3", "notes3", "semster3", p2);
        l1.setBelongedCoursesId("csye6225");
        l2.setBelongedCoursesId("csye6225");
        l3.setBelongedCoursesId("csye6225");


        Lecture l4 = new Lecture(100004L,"name4", "material4", "notes4", "semster3", p1);
        Lecture l5 = new Lecture(100005L,"name5", "material5", "notes5", "semster3", p1);
        Lecture l6 = new Lecture(100006L,"name6", "material6", "notes6", "semster3", p2);
        Lecture l7 = new Lecture(100007L,"name7", "material7", "notes7", "semster3", p2);
        l4.setBelongedCoursesId("info6205");
        l5.setBelongedCoursesId("info6205");
        l6.setBelongedCoursesId("info6205");
        l7.setBelongedCoursesId("info6205");

        Lecture l8 = new Lecture(100008L,"name8", "material8", "notes8", "semster8", p1);
        l8.setBelongedCoursesId("cs5600");
        lectures.put(100001L,l1);
        lectures.put(100002L,l2);
        lectures.put(100003L,l3);
        lectures.put(100004L,l4);
        lectures.put(100005L,l5);
        lectures.put(100006L,l6);
        lectures.put(100007L,l7);
        lectures.put(100008L,l8);
    }

    public static LectureService getInstance(){
        if (lectureService == null){
            lectureService = new LectureService();
        }
        return lectureService;
    }
    //get all lectures
    public List<Lecture> getAllLectures(){
        return new ArrayList<>(lectures.values());
    }

    //get lecture by id
    public Lecture getLectureById(long lectureId){
        return lectures.get(lectureId);
    }

    //post a new lecture
    public Lecture addLecture(Lecture lecture){
        return lectures.put(lecture.getLectureId(),lecture);
    }

    //put/update lecture by id
    public Lecture updateLecture(Lecture lecture){
        if (lecture.getLectureId() == 0 ){
            return null;
        }
        return lectures.put(lecture.getLectureId(),lecture);
    }
    //delete a lecture
    public Lecture deleteLecture(long lectureId){
        return lectures.remove(lectureId);
    }
}
