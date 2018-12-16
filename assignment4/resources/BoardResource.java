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

import com.csye6225.fall2018.courseservice667.datamodel.Board;
import com.csye6225.fall2018.courseservice667.service.BoardService;

@Path("/boards")
public class BoardResource 
{

	BoardService boardService = new BoardService();	
	@GET
	@Path("/allboards")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Board> getBoards() {
		return boardService.getAllBoards();
	}
	
	// ... webapi/boards/1 
	@GET
	@Path("/board/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoard(@PathParam("boardId") String boardId) {

		return boardService.getBoard(boardId);
	}
	
	//Get boards by course 
	@GET
	@Path("/course/{courseId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board getBoardByCourse(
			@PathParam("courseId") String courseId) {
		if (courseId==null) {
			return null;
		}
		return boardService.getBoardByCourse(courseId);
		
	}
	
	@DELETE
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Board deleteCourse(@PathParam("boardId") String boardId) {
		return boardService.deleteBoard(boardId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board addBoard(Board board) {
			return boardService.addBoard(board);
	}
	
	@PUT
	@Path("/{boardId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Board updateBoard(@PathParam("boardId") String boardId, Board board) {
		return boardService.updateBoardInformation(boardId, board);
	}
	
	public void addBoard(String id, String boardId, String courseId) {
		boardService.addBoard(id, boardId, courseId);
	}
}
