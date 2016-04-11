package clueTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.*;

public class GameSetupTests {

	Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
		board.initialize();
	}
	
	
	// CHECKS THAT THERE ARE 6 TOTAL PLAYERS
	// CHECKS THAT THE FIRST PLAYER IS A HUMAN, HAS THE CORRECT LOADED COLOR, AND IS IN THE CORRECT LOCATION
	// CHECKS THAT THE LAST PLAYER IS A COMPUTER, HAS THE CORRECT COLOR, AND IS IN CORRECT LOCATION
	@Test
	public void testLoadPeople() {
		assertEquals(6, board.getPlayers().length);
		
		assertEquals(Color.pink, board.getPlayers()[0].getColor());
		assertEquals(board.getPlayers()[0].getClass(), HumanPlayer.class);
		assertEquals(board.getPlayers()[0].getRow(), 4);
		assertEquals(board.getPlayers()[0].getCol(), 6);
		
		assertEquals(Color.white, board.getPlayers()[5].getColor());
		assertEquals(board.getPlayers()[5].getClass(), ComputerPlayer.class);
		assertEquals(board.getPlayers()[5].getRow(), 11);
		assertEquals(board.getPlayers()[5].getCol(), 10);
	}
	
	
	// TESTS THAT THE DECK IN THE BOARD CLASS CONTAINS 21 CARDS
	// THEN CHECKS THAT THERE ARE THE CORRECT NUMBER OF EACH CARD TYPE
	// LASTLY CHECKS THAT THE NAMES LOADED CORRECTLY
	@Test
	public void testLoadCards() {
		assertEquals(21, board.getCards().length);
		
		int p=0, r=0, w=0;
		for(Card c : board.getCards()){
			switch(c.getType()){
			case PERSON:
				p++;
				break;
			case WEAPON:
				w++;
				break;
			case ROOM:
				r++;
				break;
			}
		}
		assertEquals(6, p);
		assertEquals(6, w);
		assertEquals(9, r);
		
		boolean roo = false, wep = false, per = false;
		for(Card c : board.getCards()){
			if(c.getName().equals("Miss Scarlett")){
				per = true;
			}
			if(c.getName().equals("Wrench")){
				wep = true;
			}
			if(c.getName().equals("Lounge")){
				roo = true;
			}
		}
		assertTrue(per && wep && roo);
	}
	
	
	// CHECKS THAT EACH PLAYER HAS 3 CARDS (21 - 3 solution cards = 18 --> 18 / 6 = 3)
	// THEN CHECKS THAT NO ONE HAS A DUPLICATE CARD BY ADDING ALL PLAYER CARDS BACK TO A NEW SET (18 cards)
	// FINALLY CHECKS THAT NO PLAYER HAS A CARD THAT THE SOLUTION CONTAINS, THEREFORE IT ALSO CHECKS ALL CARDS HAVE BEEN DELT AND NO DUPLICATES
	@Test
	public void testDeal() {
		for(Player p : board.getPlayers()){
			assertEquals(3, p.getMyCards().size());
		}
		
		Set<Card> cardList = new HashSet<Card>();
		for(Player p : board.getPlayers()){
			for(Card c : p.getMyCards()){
				if(!cardList.contains(c))
					cardList.add(c);
			}
		}
		assertEquals(18, cardList.size()); // 18 b/c 3 cards are in the solution
		
		for(Card c : cardList){
			switch(c.getType()){
			case PERSON:
				if(board.solution.person.equals(c.getName()))
					fail("Player has person solution card.");
				break;
			case WEAPON:
				if(board.solution.weapon.equals(c.getName()))
					fail("Player has weapon solution card.");
				break;
			case ROOM:
				if(board.solution.room.equals(c.getName()))
					fail("Player has room solution card.");
				break;
			}
		}
	}
}
