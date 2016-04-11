package clueTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameActionTests {

	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		board.initialize();
	}
	
	// TEST SETS PUBLIC SOLUTION VARIABLES AND CHECKS AGAINST HARD CODED GUESSES
	@Test
	public void testAcccusation(){
		board.solution.person = "Bob";
		board.solution.weapon = "Fork";
		board.solution.room = "Mines";
		
		assertTrue(board.checkAccusation("Bob", "Fork", "Mines"));
		assertFalse(board.checkAccusation("Jeff", "Fork", "Mines"));
		assertFalse(board.checkAccusation("Bob", "Spoon", "Mines"));
		assertFalse(board.checkAccusation("Bob", "Fork", "CU"));
	}
	
	
	// PLAYER DISPROVES A SUGGESTION BY RETURNING THE ONLY CARD POSSIBLE TO DISPROVE
	@Test
	public void testDisproveSuggestionOnePlayerOneMatch(){
		Player player = new ComputerPlayer("Jeff", 0, 0, null);
		Card room = new Card(CardType.ROOM, "BB316");
		Card person = new Card(CardType.PERSON, "Clayton");
		Card weapon = new Card(CardType.WEAPON, "Moniter");
		Card room2 = new Card(CardType.ROOM, "Alamode");
		Card person2= new Card(CardType.PERSON, "Jed");
		Card weapon2 = new Card(CardType.WEAPON, "Keyboard");
		player.giveCard(room);
		player.giveCard(room2);
		player.giveCard(person);
		player.giveCard(person2);
		player.giveCard(weapon);
		player.giveCard(weapon2);
		
		Card cardReturned = player.disproveSuggestion(new Solution(person.getName(), "Axe", "Closet"));
		assertEquals(cardReturned.getName(), "Clayton");
		cardReturned = player.disproveSuggestion(new Solution("Bob", weapon.getName(), "Closet"));
		assertEquals(cardReturned.getName(), "Moniter");
		cardReturned = player.disproveSuggestion(new Solution("Bob", "Axe", room.getName()));
		assertEquals(cardReturned.getName(), "BB316");
		cardReturned = player.disproveSuggestion(new Solution("Bob", "Axe", "Closet"));
		assertEquals(cardReturned, null);
	}
	
	
	// PLAYER DISPROVES A SUGGESTION BY RANDOMLY DISPLAYING ONE OF 3 CARDS POSSIBLE TO DISPROVE
	@Test
	public void testDisproveSuggestionOnePlayerThreeMatches(){
		Player player = new ComputerPlayer("Jeff", 0, 0, null);
		Card room = new Card(CardType.ROOM, "BB316");
		Card person = new Card(CardType.PERSON, "Clayton");
		Card weapon = new Card(CardType.WEAPON, "Moniter");
		Card room2 = new Card(CardType.ROOM, "Alamode");
		Card person2= new Card(CardType.PERSON, "Jed");
		Card weapon2 = new Card(CardType.WEAPON, "Keyboard");
		player.giveCard(room);
		player.giveCard(room2);
		player.giveCard(person);
		player.giveCard(person2);
		player.giveCard(weapon);
		player.giveCard(weapon2);
		
		Card cardReturned;
		boolean goodName = false, goodWeapon = false, goodRoom = false;
		
		for(int i = 0; i < 100; i++){
			cardReturned = player.disproveSuggestion(new Solution("Clayton", "Moniter", "BB316"));
			if(cardReturned.getName().equals("Clayton"))
				goodName = true;
			else if(cardReturned.getName().equals("Moniter"))
				goodWeapon = true;
			else if(cardReturned.getName().equals("BB316"))
				goodRoom = true;
			else
				fail("card returned was not part of suggestion.");
		}
		assertTrue(goodName && goodWeapon && goodRoom);
	}
	
	// STARTING WITH THE NEXT PLAYER IN BOARD'S PLAYER ARRAY, THE FIRST PERSON ABLE TO DISPROVE, DOES
	@Test
	public void testDisproveSuggestionMultiPlayer(){
		Player[] players = new Player[3];
		Player player = new ComputerPlayer("Jeff", 0, 0, null);
		Player player2 = new ComputerPlayer("Steve", 0, 0, null);
		Player player3 = new HumanPlayer("Anna", 0, 0, null);
		Card room = new Card(CardType.ROOM, "BB316");
		Card person = new Card(CardType.PERSON, "Clayton");
		Card weapon = new Card(CardType.WEAPON, "Moniter");
		Card room2 = new Card(CardType.ROOM, "Alamode");
		Card person2= new Card(CardType.PERSON, "Jed");
		Card weapon2 = new Card(CardType.WEAPON, "Keyboard");
		player.giveCard(room);
		player2.giveCard(room2);
		player3.giveCard(person);
		player.giveCard(person2);
		player2.giveCard(weapon);
		player3.giveCard(weapon2);
		
		players[0] = player;
		players[1] = player2;
		players[2] = player3;
		board.setPlayers(players);
		
		// NO ONE CAN DISPROVE B/C NO ONE HAS CARD
		assertEquals(board.handleSuggestion(new Solution("Cyndi", "lecture", "BBW270"), "Jeff", new BoardCell(0,0)), null);
		
		// FIRST POSSIBLE (AND HUMAN) PLAYER DISPROVES THE SUGGESTION
		assertEquals(board.handleSuggestion(new Solution("Clayton", "lecture", "BBW270"), "Jeff", new BoardCell(0,0)).getName(), "Clayton");
		
		// ONLY THE PLAYER SUGGESTING HAS A CARD THAT COULD DISPROVE. THEY DO NOT DISPROVE.
		assertEquals(board.handleSuggestion(new Solution("Clayton", "lecture", "BBW270"), "Anna", new BoardCell(0,0)), null);
		
		// LAST PERSON POSSIBLE IN ARRAY DISPROVES. EVERYONE IS QUERRIED
		assertEquals(board.handleSuggestion(new Solution("Clayton", "Moniter", "BBW270"), "Jeff", new BoardCell(0,0)).getName(), "Moniter");
		
	}

	
	// TESTS THAT EVERY POSSIBLE TARGET IS SELECTED BY LOOPING 100 TIMES
	@Test
	public void testRandomSelectionTarget(){
		ComputerPlayer player = new ComputerPlayer("Jeff", 22, 1, null);
		board.calcTargets(22, 1, 2);
		boolean loc_20_1 = false;
		boolean loc_21_2 = false;
		boolean loc_24_1 = false;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(20, 1))
				loc_20_1 = true;
			else if (selected == board.getCellAt(21, 2))
				loc_21_2 = true;
			else if (selected == board.getCellAt(24, 1))
				loc_24_1 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_20_1);
		assertTrue(loc_21_2);
		assertTrue(loc_24_1);	
	}
	
	
	// TESTS THAT THE PLAYER PICKS THE ROOM EVERY TIME (AS LONG AS NOT THEIR PREVIOUS BOARD LOCATION)
	@Test
	public void testRoomTarget(){
		ComputerPlayer player = new ComputerPlayer("Jeff", 14, 0, null);
		board.calcTargets(14, 0, 1);
		boolean loc_13_0 = false;
		boolean loc_15_0 = false;
		boolean loc_14_1 = false;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(13, 0))
				loc_13_0 = true;
			else if (selected == board.getCellAt(15, 0))
				loc_15_0 = true;
			else if (selected == board.getCellAt(14, 1))
				loc_14_1 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_13_0);
		assertFalse(loc_15_0);
		assertFalse(loc_14_1);	
	}
	
	
	// TESTS THAT SINCE THE PLAYER COMES FROM A ROOM, THE PLAYER HAS AN EQUAL CHANCE TO PICK TO ROOM AGAIN OR GO TO NEW LOCATION
	@Test
	public void testNotBackToRoomTarget(){
		ComputerPlayer player = new ComputerPlayer("Jeff", 13, 0, null);
		board.calcTargets(13, 0, 1);
		boolean loc_13_0 = false;
		boolean loc_15_0 = false;
		boolean loc_14_1 = false;
		BoardCell selected = player.pickLocation(board.getTargets());
		player.setLocation(selected);
		board.calcTargets(selected.getX(), selected.getY(), 1);
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(13, 0))
				loc_13_0 = true;
			else if (selected == board.getCellAt(15, 0))
				loc_15_0 = true;
			else if (selected == board.getCellAt(14, 1))
				loc_14_1 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_13_0);
		assertTrue(loc_15_0);
		assertTrue(loc_14_1);	
	}
	
	
	// TESTING THAT PLAYER NEVER PICKS A CARD THEY HAVE IN POSSESSION OR HAVE SEEN BEFORE
	@Test
	public void testComputerSuggestion(){
		
		ComputerPlayer player = new ComputerPlayer("Jeff", 2, 0, null);
		Card pp = new Card(CardType.PERSON, "Professor Periwinkle");
		Card ms = new Card(CardType.PERSON, "Miss Scarlett");
		Card mw = new Card(CardType.PERSON, "Mrs. White");
		Card mg = new Card(CardType.PERSON, "Mr. Green");
		Card cm = new Card(CardType.PERSON, "Colonel Mustard");
		Card lp = new Card(CardType.WEAPON, "Lead Pipe");
		Card cs = new Card(CardType.WEAPON, "Candlestick");
		Card k = new Card(CardType.WEAPON, "Knife");
		Card r = new Card(CardType.WEAPON, "Revolver");
		Card w = new Card(CardType.WEAPON, "Wrench");
		// LOADS PLAYER'S SEEN WITH ALL BUT 2 WEAPONS AND ALL BUT 1 PERSON
		player.giveCard(pp);
		player.seeCard(ms);
		player.giveCard(mw);
		player.seeCard(mg);
		player.giveCard(cm);
		player.seeCard(lp);
		player.giveCard(cs);
		player.seeCard(k);
		player.giveCard(r);
		
		boolean rope = false, wrench = false;
		
		Solution sol = new Solution("","","");
		
		// CHECKS FOR RANDOMNESS IN SELECTION OF LAST TWO UNSEEN WEAPONS
		for(int i = 0; i < 50; i ++){
			sol = player.makeSuggestion(board, board.getCellAt(player.getRow(), player.getCol()));
			if(sol.weapon.equals("Rope"))
				rope = true;
			if(sol.weapon.equals("Wrench"))
				wrench = true;
		}
		assertTrue(rope && wrench);
		
		// LETS THE PLAYER SEE THE SECOND TO LAST WEAPON
		player.seeCard(w);
		
		// SHOULD ONLY HAVE ONE POSSIBLE SOLUTION GUESS
		sol = player.makeSuggestion(board, board.getCellAt(player.getRow(), player.getCol()));
		assertEquals(sol.room, "Kitchen");
		assertEquals(sol.weapon, "Rope");
		assertEquals(sol.person, "Mrs. Peacock");
	}
	
}
