package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.DoorDirection;

public class BoardTestPartI {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		board.initialize();
	}

	@Test
	public void testRooms() {
		assertEquals(11, Board.getRooms().size());
		assertEquals("Bathroom", Board.getRooms().get('B'));
		assertEquals("Kitchen", Board.getRooms().get('K'));
		assertEquals("Hallway", Board.getRooms().get('H'));
		assertEquals("Lounge", Board.getRooms().get('L'));
		assertEquals("Patio", Board.getRooms().get('P'));
		assertEquals("Dining Room", Board.getRooms().get('D'));
		assertEquals("Observatory", Board.getRooms().get('O'));
		assertEquals("Master bedroom", Board.getRooms().get('M'));
		assertEquals("Closet", Board.getRooms().get('X'));
		assertEquals("Walkway", Board.getRooms().get('W'));
	}
	
	@Test
	public void loadConfig() {
		assertEquals(26, board.getNumRows());
		assertEquals(13, board.getNumColumns());
		
		assertEquals(DoorDirection.RIGHT, board.getCellAt(14, 7).getDirection());
		assertEquals(DoorDirection.UP, board.getCellAt(2, 0).getDirection());
		assertEquals(DoorDirection.LEFT, board.getCellAt(25, 7).getDirection());
		assertEquals(DoorDirection.DOWN, board.getCellAt(7, 0).getDirection());
		
		assertTrue(!board.getCellAt(0, 0).isDoorway());
		
		assertEquals(14, board.getNumDoors());

		assertEquals('B', board.getCellAt(0, 5).getInitial());
		assertEquals('K', board.getCellAt(4, 0).getInitial());
		assertEquals('H', board.getCellAt(11, 0).getInitial());
		assertEquals('L', board.getCellAt(12, 12).getInitial());
		assertEquals('P', board.getCellAt(22, 4).getInitial());
		assertEquals('D', board.getCellAt(18, 4).getInitial());
		assertEquals('O', board.getCellAt(12, 9).getInitial());
		assertEquals('M', board.getCellAt(0, 12).getInitial());
		assertEquals('X', board.getCellAt(11, 6).getInitial());
		assertEquals('W', board.getCellAt(0, 0).getInitial());				
	}

	
	@Test (expected = FileNotFoundException.class)
	public void testFileOpening() throws  FileNotFoundException, BadConfigFormatException{
		Board board = new Board("not a file", "not a file");
		board.loadBoardConfig();
		board.loadRoomConfig();
	}
}
