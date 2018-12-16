package com.csye6225.fall2018.courseservice667;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class registerNotificationSNS 
{
	 private static AmazonSNS client = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
	 
	 public static String createTopic(String topicName) 
	 {
			return client.createTopic(topicName).getTopicArn();
	 }
		
	public static void subscribe(String topicArn, String email) 
	{
		  client.subscribe(topicArn, "email", email);		
	}
}
