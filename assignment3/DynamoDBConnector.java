package com.csye6225.fall2018.courseservice667;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBConnector {

	static AmazonDynamoDB dynamoDB ;
 
	 public static void init() {
		if (dynamoDB == null) {
		DefaultAWSCredentialsProviderChain credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
		credentialsProvider.getCredentials();
			
		dynamoDB = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider).withRegion("us-east-2").build();		
		System.out.println("Client created successfully!");
		} 

	}	 
	 public AmazonDynamoDB getClient() {
		 return dynamoDB;
	 }
	
}