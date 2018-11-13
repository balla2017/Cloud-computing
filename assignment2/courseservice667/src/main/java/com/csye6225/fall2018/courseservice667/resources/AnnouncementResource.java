package com.csye6225.fall2018.courseservice667.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye6225.fall2018.courseservice667.datamodel.Announcement;
import com.csye6225.fall2018.courseservice667.service.AnnouncementService;

@Path("announcements")
public class AnnouncementResource 
{
	AnnouncementService announcementService = new AnnouncementService();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnouncements() {
		return announcementService.getAllAnnouncements(); 
	}
	
	// ... webapi/announcements/ANNOUN1001 
	@GET
	@Path("/byannouncement/{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement getAnnouncementByAnnouncementId(@PathParam("announcementId") String announId) {
		return announcementService.getAnnouncementByAnnouncementId(announId);
	}
	
	//Get boards by board 
	@GET
	@Path("/byboard/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Announcement> getAnnouncementsByBoard(@PathParam("boardId") String boardId) {
		if (boardId==null) {
			return announcementService.getAllAnnouncements();
		}
		return announcementService.getAnnouncementsByBoard(boardId);		
	}
	
	@DELETE
	@Path("/{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Announcement deleteAnnouncement(@PathParam("announcementId") String announId) {
		return announcementService.deleteAnnouncement(announId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement addAnnoun(Announcement announ) {
			return announcementService.addAnnouncement(announ);
	}
	
	@PUT
	@Path("/{announcementId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Announcement updateAnnouncement(@PathParam("announcementId") String announId, Announcement announ) {
		return announcementService.updateAnnouncementInformation(announId, announ);
	}
	
	public void addAnnouncement(String id, String announId, String announText, String boardId) {
		announcementService.addAnnouncement(id, announId, announText, boardId);
	}
}
