package com.csye6225.fall2018.courseservice667.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice667.datamodel.Program;
import com.csye6225.fall2018.courseservice667.datamodel.Student;

public class ProgramService {
    private Map<Long,Program> programs = InMemoryDatabase.getPrograms();
    private static ProgramService programService;
    public ProgramService() {
        Student s1 = new Student(10001L,"Balla");
        Student s2 = new Student(10002L,"Glory");
        Course c1 = new Course("csye6225","Network Structure and Cloud Computing");
        Course c2 = new Course("csye6100","Computer Vision");
        
        Program csye = new Program(666L,"Computer Systems Engineering",
                Arrays.asList(s1,s2),
                Arrays.asList(c1,c2));


        programs.put(csye.getProgramId(),csye);
    }

    public static ProgramService getInstance(){
        if (programService == null){
            programService = new ProgramService();
        }
        return programService;
    }
    //  ../programs
    public List<Program> getAllPrograms(){
        return new ArrayList<>(programs.values());
    }

    // ../programs/{program_id}, getting one program by id
    public Program getProgramById(long id){
        return programs.get(id);
    }
    // ../programs/{program_id}/students
    public List<Student> getStudents(long programId){
        return getProgramById(programId).getEnrolledStudents();
    }
    // ../programs/{program_id}/courses
    public List<Course> getCourses(long programId){
        return getProgramById(programId).getCourses();
    }

    //Adding one program
    public Program addProgram(Program program){
        program.setProgramId(programs.size()+6000);
        return programs.put(program.getProgramId(),program);
    }

    //Updating one program by id
    public Program updateProgram(Program program){
        if (program.getProgramId() <= 0 ){
            return null;
        }
        return programs.put(program.getProgramId(),program);
    }
    
    //Deleting one program by id
    public Program deleteProgram(long id){
        return programs.remove(id);
    }
}
