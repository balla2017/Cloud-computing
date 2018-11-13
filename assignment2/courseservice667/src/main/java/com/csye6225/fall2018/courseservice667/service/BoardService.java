package com.csye6225.fall2018.courseservice667.service;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.fall2018.courseservice667.DynamoDBConnector;
import com.csye6225.fall2018.courseservice667.datamodel.Board;

public class BoardService 
{
	static DynamoDBConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public BoardService() {
		dynamoDb = new DynamoDBConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting One Board
	public Board getBoard(String boardId) {
		List<Board> BoardList = mapper.scan(Board.class, new DynamoDBScanExpression()); 
		Board retrievedBoard= new Board(); 
		for (Board board : BoardList) {
			if (board.getBoardId().equals(boardId)) {
				retrievedBoard = board; 
			     System.out.println("Board retrieved:");
			     System.out.println(board.toString());
			}
		}

		return retrievedBoard;
	}
	// Getting a course's board
	public Board getBoardByCourse(String courseId) {
		List<Board> BoardList = mapper.scan(Board.class, new DynamoDBScanExpression()); 
		Board retrievedBoard= new Board(); 
		for (Board board : BoardList) {
			if (board.getCourseId().equals(courseId)) {
				retrievedBoard = board; 
			     System.out.println("Board retrieved:");
			     System.out.println(board.toString());
			}
		}
		return retrievedBoard;
	}
	
	//Getting one list of Boards 
	public List<Board> getAllBoards() {	
		List<Board> boardList = mapper.scan(Board.class, new DynamoDBScanExpression()); 
			System.out.println("The list of Boards");
			System.out.println(boardList.toString());
		return boardList ;
	}
		
	// Adding a Board
	public Board addBoard(String id, String boardId, String courseId) {
		Board newBoard = new Board(id, boardId, courseId);	
		mapper.save(newBoard);
		 System.out.println("New board added:");
	     System.out.println(newBoard.toString());
		return newBoard;

	}
	
	public Board addBoard(Board board) {	
		mapper.save(board);
		System.out.println("Board added:");
	    System.out.println(board.toString());
		return board;	
	}
	

	// Deleting a Board
	public Board deleteBoard(String boardId) {
		List<Board> BoardList = mapper.scan(Board.class, new DynamoDBScanExpression()); 
		int n = BoardList.size();
		Board deletedBoard = new Board(); 
		for (int i = 0; i < n; i++) {
			deletedBoard = BoardList.get(i);
			if(deletedBoard.getBoardId().equals(boardId)) {
				mapper.delete(BoardList.get(i));
			}
		}
		 System.out.println("Board deleted:");
	     System.out.println(deletedBoard.toString());
		return deletedBoard;
	}
	
	
	// Updating Board Info
	public Board updateBoardInformation(String boardId, Board board) {
		List<Board> BoardList = mapper.scan(Board.class, new DynamoDBScanExpression());
		for (Board b : BoardList) {
			if (b.getBoardId().equals(boardId)) {
				mapper.delete(b);
				mapper.save(board);
				 System.out.println("Board updated:");
			     System.out.println(board.toString());
			}
		}
		return board;
	}
	
}
