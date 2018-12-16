package com.csye6225.fall2018.courseservice667.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
import com.csye6225.fall2018.courseservice667.datamodel.Registrar;

public class RegistrarService 
{
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 	

	
	CourseService cs = new CourseService();

	public RegistrarService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();		
		mapper = new DynamoDBMapper(dynamoDb.getClient());

	}
	
	// Getting One Registrar according to registrationId
		public Registrar getRegistrar(String registrationId) 
		{
			  Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			  eav.put(":val1", new AttributeValue().withS(registrationId));
			  
			  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("registrationId = :val1")
			    .withExpressionAttributeValues(eav);
			  
			  List<Registrar> registrarList = mapper.scan(Registrar.class, scanExpression);
			  if(registrarList.size()!=0){
				  return registrarList.get(0);  
			  }
			  
			  return null;
		}
		
		//Getting all registrars 
		public List<Registrar> getAllRegistrars() {	
			//Getting the list
			List<Registrar> registrarList = mapper.scan(Registrar.class, new DynamoDBScanExpression()); 
			System.out.println("All Registrars");
			return registrarList ;
		}
		
		// Get all registrars with specail department 
		public List<Registrar> getRegistrarByDepartment(String department) {	
			 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			  eav.put(":val1", new AttributeValue().withS(department));
			  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("department = :val1").withExpressionAttributeValues(eav);
			  List<Registrar> retrievedRegistrars = mapper.scan(Registrar.class, scanExpression);
			  if(retrievedRegistrars.size() ==0) {
				  return null;
			  }
			return retrievedRegistrars ;
		}
		
		// Get registrars by offeringType  
		public List<Registrar> getRegistrarByOfferingType(String offeringType) {	
			 Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
			  eav.put(":val1", new AttributeValue().withS(offeringType));
			  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("offeringType = :val1").withExpressionAttributeValues(eav);
			  List<Registrar> retrievedRegistrars = mapper.scan(Registrar.class, scanExpression);
			  if(retrievedRegistrars.size() ==0) {
				  return null;
			  }
			return retrievedRegistrars ;
		}

		// Adding a registrar
		public Registrar addRegistrar(String registrationId, String offeringId, String offeringType, String department,  
				int perUnitPrice ) {
			Registrar newRegistrar = new Registrar(registrationId, offeringId, offeringType, department, perUnitPrice);
			mapper.save(newRegistrar);
			System.out.println("Registrar added successfully!");
			return newRegistrar;
			

		}
		
		public Registrar addRegistrar(Registrar registrar) {			
			mapper.save(registrar);
			System.out.println("Registrar added successfully!");
			return registrar;	

		}
		

		// Deleting a registrar according to registrationId
		public Registrar deleteRegistrar(String registrationId) {
			List<Registrar> registrarList = mapper.scan(Registrar.class, new DynamoDBScanExpression()); 
			Registrar deletedRegis = new Registrar(); 
			for (Registrar r : registrarList) {
				if (r.getRegistrationId().equals(registrationId)){
					deletedRegis = r; 
					mapper.delete(r);
				}
			}
			 System.out.println("Registrar deleted successfully!");
			return deletedRegis;
		}
		
		// Updating Registrar 
		public Registrar updateRegistrarInformation(String registrationId, Registrar registrar) {
			List<Registrar> registrarList = mapper.scan(Registrar.class, new DynamoDBScanExpression());
			for (Registrar reg : registrarList) {
				if (reg.getRegistrationId().equals(registrationId)) {
					mapper.delete(reg);
					mapper.save(registrar);
					 System.out.println("Registrar updated successfully!");
				}
			}
			return registrar;
		}
}
