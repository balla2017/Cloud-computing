package com.csye6225.fall2018.courseservice667.datamodel;

public class Lecture {
    private long lectureId;
    private String lectureName;
    private String material;
    private String notes;
    private String semester;
    private Professor professor;
    private String belongedCoursesId;

    public Lecture(){

    }
    public Lecture(long lectureId, String lectureName) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
    }

    public Lecture(long lectureId, String lectureName, String material, String notes, String semester, Professor p) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.material = material;
        this.notes = notes;
        this.semester = semester;
        this.professor = p;
    }

    public long getLectureId() {
        return lectureId;
    }

    public void setLectureId(long lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getBelongedCoursesId() {
        return belongedCoursesId;
    }

    public void setBelongedCoursesId(String belongedCoursesId) {
        this.belongedCoursesId = belongedCoursesId;
    }
}