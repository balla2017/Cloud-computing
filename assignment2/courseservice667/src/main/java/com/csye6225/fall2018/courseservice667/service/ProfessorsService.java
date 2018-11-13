package com.csye6225.fall2018.courseservice667.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
import com.csye6225.fall2018.courseservice667.datamodel.InMemoryDatabase;
import com.csye6225.fall2018.courseservice667.datamodel.Professor;

public class ProfessorsService {
	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public ProfessorsService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withIndexName("professorID-index")
			    .withConsistentRead(false);

	    List<Professor> list =  mapper.scan(Professor.class, scanExpression);
		return list ;
	}

	// Adding a professor
	public Professor addProfessor(Professor prof) {
		Professor prof2 = new Professor();
		prof2.setFirstName(prof.getFirstName());
		prof2.setLastName(prof.getLastName());
		prof2.setDepartment(prof.getDepartment());
		prof2.setProfessorId(prof.getLastName() + "." + prof.getFirstName());
		prof2.setJoiningDate(prof.getJoiningDate());
		mapper.save(prof2);
		
		System.out.println("added:");
	    System.out.println(prof2.toString());
	    
	    return prof2;
	}
	
	
	// Getting One Professor
	public Professor getProfessor(String profId) {
		List<Professor> list = getProfessorFromDDB(profId);
		return list.size() != 0 ? list.get(0) : null;
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		List<Professor> list = getProfessorFromDDB(profId);
		Professor prof = null;
		if(list.size() != 0){
			prof = list.get(0);
			mapper.delete(prof);
			
			Professor deletedProf = mapper.load(Professor.class, prof.getID());
			if (deletedProf == null) {
	            System.out.println("deleted successfully.");
	            System.out.println(prof.toString());
	        }
		}
		
		return prof;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {	
		List<Professor> list = getProfessorFromDDB(profId);
		Professor oldProf = null;
		if(list.size() != 0) {
			oldProf = list.get(0);
			oldProf.setFirstName(prof.getFirstName());
			oldProf.setLastName(prof.getLastName());
			oldProf.setDepartment(prof.getDepartment());
			oldProf.setJoiningDate(prof.getJoiningDate());
			mapper.save(oldProf);
			System.out.println("updated:");
		    System.out.println(oldProf.toString());
		}
		
		return oldProf;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}
	
	// helper function 
	public List<Professor> getProfessorFromDDB(String profId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(profId));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
		    .withIndexName("professorID-index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("professorID = :v1")
		    .withExpressionAttributeValues(eav);

		List<Professor> list =  mapper.query(Professor.class, queryExpression);
		return list;
	}
}