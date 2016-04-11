package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player {
	private String playerName;
	private int row, column;
	private Color color;
	private Set<Card> myCards;
	protected Set<Card> seenCards;
	protected int prevRow;
	protected int prevCol;
	
	public Player(String playerName, int row, int column, Color color) {
		
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
		myCards = new HashSet<Card>();
		seenCards = new HashSet<Card>();
	}

	public Card disproveSuggestion(Solution suggestion){
		
		ArrayList<Card> possibilities = new ArrayList<Card>();
		
		for(Card c : myCards){
			if(suggestion.person.equals(c.getName()))
				possibilities.add(c);
			if(suggestion.weapon.equals(c.getName()))
				possibilities.add(c);
			if(suggestion.room.equals(c.getName()))
				possibilities.add(c);
		}
		
		if(possibilities.size()>0){
			Random rand = new Random();
			int randCard = rand.nextInt(possibilities.size());
			return possibilities.get(randCard);
		}
		
		return null;
	}
	
	public void setLocation(BoardCell b){
		prevRow = row;
		prevCol = column;
		row = b.getX();
		column = b.getY();
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return column;
	}

	public void setCol(int col) {
		this.column = col;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Set<Card> getMyCards() {
		return myCards;
	}

	public void giveCard(Card card) {
		myCards.add(card);
		seeCard(card);
	}
	
	public void seeCard(Card card) {
		seenCards.add(card);
	}

	public void draw(Graphics g, BoardCell[][] board) {
		g.setColor(color);
		g.fillOval((int)(((double)column + (0.3))*(double)BoardCell.CELL_WIDTH), row*BoardCell.CELL_HEIGHT, BoardCell.CELL_HEIGHT, BoardCell.CELL_HEIGHT);
	}
}
