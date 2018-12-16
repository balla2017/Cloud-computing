package com.csye6225.fall2018.courseservice667.lambda;

import java.util.Map;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.csye6225.fall2018.courseservice667.datamodel.Course;

public class NewCourseDetection implements RequestHandler<DynamodbEvent, Course> {
	private AmazonDynamoDB client=AmazonDynamoDBClientBuilder.standard().withRegion("us-east-2").withCredentials(new EnvironmentVariableCredentialsProvider()).build(); ; 
    private DynamoDBMapper mapper=new DynamoDBMapper(client); ;

	@Override
    public Course handleRequest(DynamodbEvent input, Context context) {
        Course c=new Course();
        for(DynamodbStreamRecord record : input.getRecords())
        {
        	if(record!=null)
        	{
        		String event=record.getEventName();
        		if(event.equals("INSERT"))
        		{
        			Map<String, AttributeValue> map = record.getDynamodb().getNewImage();
        			String department=map.get("department").getS().toLowerCase();
        			c.setId(map.get("Id").getS());
    				c.setCourseId(map.get("courseId").getS());
    				c.setProfessorId(map.get("professorId").getS());
    				c.setDepartment(map.get("department").getS());
    				mapper.save(c);
        			if((!map.containsKey("boardId") && !map.containsKey("listOfRegisteredStudents") && !map.containsKey("notificationTopic"))&&!department.equals("seminars"))
        			{
        				c.setStatus("true");
        			}
        			else
        				c.setStatus("false");
        		}
        	}
        }

        StartExecutionRequest request = new StartExecutionRequest();
        request.setInput(c.toString());
        request.setStateMachineArn("arn:aws:states:us-east-2:267886740618:stateMachine:CourseRegisterStateMachine");
        AWSStepFunctions client2=AWSStepFunctionsClientBuilder.standard().withRegion("us-east-2").build();
        client2.startExecution(request);
        return c;
    }
}