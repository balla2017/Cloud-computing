package com.csye6225.fall2018.courseservice667.lambda;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.amazonaws.http.HttpResponse;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.datamodel.Registrar;
import com.csye6225.fall2018.courseservice667.service.BonusService;
import com.csye6225.fall2018.courseservice667.service.RegistrarService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateNewRegister implements RequestHandler<Course, Course> {
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    DynamoDBMapper mapper = new DynamoDBMapper(client);
    private BonusService bonus;
    
    public CreateNewRegister()
    {
    	bonus=new BonusService();
    }

    @Override
    public Course handleRequest(Course course, Context context) 
    {
    	/*Registrar r=new Registrar();
    	r.setRegistrationId(UUID.randomUUID().toString());
    	r.setDepartment(course.getDepartment());
    	r.setOfferingId("courseID-"+course.getCourseId());
    	r.setOfferingType("Course");
    	r.setPerUnitPrice(1500);
    	mapper.save(r);*/   //this is traditional solution.
    		
    	//bonus
    	String courseId = course.getCourseId();
    	ObjectMapper mapper = new ObjectMapper();
		try {
			StringEntity params =new StringEntity("{\"registrationId\":\""+course.getCourseId()+
                    "\",\"offeringId\":\""+course.getCourseId()+
                    "\",\"offeringType\":\"Course\",\"department\":\""+course.getDepartment()+
                    "\",\"perUnitPrice\":10}");
			String requestString = mapper.writeValueAsString(params);
			bonus.callAPI("registerOffering", requestString);
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

    	return course;
    }

}
