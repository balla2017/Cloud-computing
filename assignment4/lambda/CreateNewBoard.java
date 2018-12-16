package com.csye6225.fall2018.courseservice667.lambda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.csye6225.fall2018.courseservice667.datamodel.Board;
import com.csye6225.fall2018.courseservice667.datamodel.Course;
import com.csye6225.fall2018.courseservice667.service.CourseService;

public class CreateNewBoard implements RequestHandler<Course, String> {
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").build();
    DynamoDBMapper mapper = new DynamoDBMapper(client);
    

	@Override
    public String handleRequest(Course input, Context context) 
	{
      Board b=new Board();
      b.setCourseId(input.getCourseId());
      b.setBoardId(input.getCourseId());
      input.setBoardId(b.getBoardId());
      mapper.save(b);
      mapper.save(input);
	  return "New board added successfully!";
	}
}
