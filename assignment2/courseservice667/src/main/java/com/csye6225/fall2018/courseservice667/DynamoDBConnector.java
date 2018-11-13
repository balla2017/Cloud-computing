package com.csye6225.fall2018.courseservice667;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

public class DynamoDBConnector 
{
	static AmazonDynamoDB dynamoDB;
	public static void init() {
		if(dynamoDB==null)
		{
		InstanceProfileCredentialsProvider credentialsProvider = new InstanceProfileCredentialsProvider(false);
		credentialsProvider.getCredentials();
		
		dynamoDB = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(credentialsProvider)
					.withRegion("us-east-2")
					.build();
		System.out.println("I created the client");
		}
	}
	
  /* public static void main(String[] args) throws Exception
   {
	   init();
	   String tableName="professor";
	   
	   GetItemRequest getItemRequest=new GetItemRequest();
	   Map<String, AttributeValue> itemToFetch=new HashMap<>();
	   itemToFetch.put("studentid", new AttributeValue().withS("1"));
	   getItemRequest.setKey(itemToFetch);
	   getItemRequest.setTableName(tableName);
	   GetItemResult getItemResult=dynamoDB.getItem(getItemRequest);
	   System.out.println("GetItemResult: "+getItemResult);
	   
   }*/

   public AmazonDynamoDB getClient() {
	// TODO Auto-generated method stub
	return dynamoDB;
   }

}
