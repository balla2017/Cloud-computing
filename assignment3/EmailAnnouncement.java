package com.csye6225.fall2018.courseservice667;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.csye6225.fall2018.courseservice667.service.BoardService;
import com.csye6225.fall2018.courseservice667.service.CourseService;

public class EmailAnnouncement implements RequestHandler<DynamodbEvent, String> {
    private static AmazonSNS client = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
	
	@Override
	public String handleRequest(DynamodbEvent ddbEvent, Context context) {	
		context.getLogger().log("Event: " + ddbEvent);
		for (DynamodbStreamRecord record : ddbEvent.getRecords()){
			if(record!=null)
			{
				String input=record.getEventName();
				if(!input.equals("INSERT"))
					continue;
				System.out.println(record.getDynamodb().toString());
				String boardId = record.getDynamodb().getNewImage().get("boardId").getS();
				String topic = getTopicArnByBoardId(boardId);
				String message = record.getDynamodb().getNewImage().get("announcementText").getS();
				sendNotificationByEmail(topic, message, "There is a new announcement!");         
	        }
		}
	    return ddbEvent.toString();
			
	}
	
	/*public static String createTopic(String topicName) {
		return client.createTopic(topicName).getTopicArn();
	}
	
	public static void subscribe(String topicArn, String email) {
		client.subscribe(topicArn, "email", email);		
	}*/
	
	public void sendNotificationByEmail(String topicArn, final String message, final String subject) {
		PublishRequest publishRequest = new PublishRequest(topicArn, message, subject);
		client.publish(publishRequest);
	}
	
	private String getTopicArnByBoardId(String boardId) {
		CourseService courseService = new CourseService();
		BoardService boardService = new BoardService();
		String courseId = boardService.getBoardFromDDB(boardId).get(0).getCourseId();
		return courseService.getCourseFromDDB(courseId).get(0).getNotificationTopic();
	}
}