package com.csye6225.fall2018.courseservice667.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
import com.csye6225.fall2018.courseservice667.datamodel.Announcement;

public class AnnouncementService 
{
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public AnnouncementService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}	
	
	// Getting One Announcement by announcementId
	public Announcement getAnnouncementByAnnouncementId(String announId) {
		List<Announcement> announcementList = mapper.scan(Announcement.class, new DynamoDBScanExpression()); 
		Announcement retrievedAnnouncement= new Announcement(); 
		for (Announcement announcement : announcementList) {
			if (announcement.getAnnouncementId().equals(announId)) {
				retrievedAnnouncement = announcement; 
			     System.out.println("Announcement got:");
			     System.out.println(announcement.toString());
			}
		}
		return retrievedAnnouncement;
	}
	

	// Getting a board's announcements
	public List<Announcement> getAnnouncementsByBoard(String boardId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		  eav.put(":val1", new AttributeValue().withS(boardId));
		  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    .withFilterExpression("boardId = :val1").withExpressionAttributeValues(eav);
		  List<Announcement> retrievedAnnouncement = mapper.scan(Announcement.class, scanExpression);
		  if(retrievedAnnouncement.size() ==0) {
			  return null;
		  }

	     System.out.println("Announcements retrieved:");
	     System.out.println(retrievedAnnouncement.toString());
		return retrievedAnnouncement;
	}
	
	//Getting one list of Announcements 
	public List<Announcement> getAllAnnouncements() {	
		List<Announcement> announcementList = mapper.scan(Announcement.class, new DynamoDBScanExpression()); 
			System.out.println("The retrieved list of Announcements");
			System.out.println(announcementList.toString());
		return announcementList ;
	}
	
	
	
	// Adding a Announcement
	public Announcement addAnnouncement(String id, String announcementId, String announcementText, String boardId) {
		Announcement newAnnouncement = new Announcement(id, announcementId, announcementText, boardId);
		if (announcementText.length() <= 160) {
			mapper.save(newAnnouncement);
		}
		 System.out.println("New announcement added:");
	     System.out.println(newAnnouncement.toString());
		return newAnnouncement;

	}

	public Announcement addAnnouncement(Announcement announcement) {
		if(announcement.getAnnouncementText().length()<=160)
	        {mapper.save(announcement);
	        }
		System.out.println("New announcement added:");
	    System.out.println(announcement.toString());
		return announcement;	
	}
	

	// Deleting a Announcement
	public Announcement deleteAnnouncement(String announcementId) {
		List<Announcement> announcementList = mapper.scan(Announcement.class, new DynamoDBScanExpression()); 
		Announcement deletedAnnouncement = new Announcement(); 
		for (Announcement a : announcementList) {
			if (a.getAnnouncementId().equals(announcementId)) {
				deletedAnnouncement = a; 
				mapper.delete(a);
			}
			 System.out.println("Announcement deleted:");
		     System.out.println(deletedAnnouncement.toString());
		}

		return deletedAnnouncement;
	}
	
	// Updating Announcement Info
	public Announcement updateAnnouncementInformation(String announcementId, Announcement announcement) {
		List<Announcement> announcementList = mapper.scan(Announcement.class, new DynamoDBScanExpression());
		for (Announcement a : announcementList) {
			if (a.getAnnouncementId().equals(announcementId)) {
				mapper.delete(a);
				mapper.save(announcement);
				 System.out.println("Announcement has been updated to:");
			     System.out.println(announcement.toString());
			}
		}
		return announcement;
	}

}
