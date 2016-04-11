package clueGame;

import java.awt.Color;
import java.awt.Graphics;

// Represents one cell in the grid
public class BoardCell {
	private int x, y = 0;
	public static final int CELL_WIDTH = 80;
	public static final int CELL_HEIGHT = 30;
	public String type;
	private char initial;
	private DoorDirection direction;
	private boolean labelRoomHere;
	// TODO: I don't think anything more needs to be done to this for now

	public BoardCell(int x, int y, char type, char direction, boolean labelRoomHere) {
		super();
		this.x = x;
		this.y = y;	
		this.labelRoomHere = labelRoomHere;
		initial = type;
		switch (direction){
		case 'U':
			this.direction = DoorDirection.UP;
			break;
		
		case 'D':
			this.direction = DoorDirection.DOWN;
			break;
			
		case 'L':
			this.direction = DoorDirection.LEFT;
			break;
			
		case 'R':
			this.direction = DoorDirection.RIGHT;
			break;
			
		default:
			this.direction = DoorDirection.NONE;
			break;
		}
		
	}

	public BoardCell(int x2, int y2) {
		x = x2;
		y = y2;
		initial = 'X';
		direction = DoorDirection.NONE;
	}

	@Override
	public String toString() {
		return "BoardCell [x=" + x + ", y=" + y + ", type=" + type + "]";
	}
	
	public boolean isWalkway(){
		if (initial == 'W') return true;
		else return false;
	}
	
	public boolean isRoom(){
		if (initial != 'W' && direction == DoorDirection.NONE) return true;
		else return false;
	}
	
	public boolean isDoorway(){
		if (direction != DoorDirection.NONE) return true;
		else return false;
	}

	public DoorDirection getDoorDirection() {
		return direction;
	}

	public char getInitial() {
		return initial;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getType() {
		return type;
	}

	public DoorDirection getDirection() {
		return direction;
	}

	public void draw(Graphics g, BoardCell[][] b) {
		if(this.isWalkway()){
			g.setColor(Color.CYAN);
			g.fillRect(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH, CELL_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH, CELL_HEIGHT);
		}
		if(!this.isWalkway()){
			g.setColor(Color.GRAY);
			g.fillRect(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH, CELL_HEIGHT);
			g.setColor(Color.BLACK);
			if(x > 0 && b[x-1][y].getInitial() != b[x][y].getInitial())
				g.drawLine(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH*(y + 1), CELL_HEIGHT*x);
			if(x == 0)
				g.drawLine(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH*(y + 1), CELL_HEIGHT*x);
			if(b[x+1][y] != null && b[x+1][y].getInitial() != b[x][y].getInitial())
				g.drawLine(CELL_WIDTH*y, CELL_HEIGHT*(x+1), CELL_WIDTH*(y + 1), CELL_HEIGHT*(x+1));
			if(b[x+1][y] == null)
				g.drawLine(CELL_WIDTH*y, CELL_HEIGHT*(x+1), CELL_WIDTH*(y + 1), CELL_HEIGHT*(x+1));
			if(y > 0 && b[x][y-1].getInitial() != b[x][y].getInitial())
				g.drawLine(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH*y, CELL_HEIGHT*(x+1));
			if(y == 0)
				g.drawLine(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH*y, CELL_HEIGHT*(x+1));
			if(b[x][y+1] != null && b[x][y+1].getInitial() != b[x][y].getInitial())
				g.drawLine(CELL_WIDTH*(y+1), CELL_HEIGHT*x, CELL_WIDTH*(y+1), CELL_HEIGHT*(x+1));
			if(b[x][y+1] == null)
				g.drawLine(CELL_WIDTH*(y+1), CELL_HEIGHT*x, CELL_WIDTH*(y+1), CELL_HEIGHT*(x+1));
		}
		if(this.isDoorway()){
			g.setColor(Color.BLUE);
			if(this.getDirection() == DoorDirection.DOWN)
				g.fillRect(CELL_WIDTH*y, CELL_HEIGHT*x + ((CELL_HEIGHT*5) / 6), CELL_WIDTH, CELL_HEIGHT / 6);
			if(this.getDirection() == DoorDirection.UP)
				g.fillRect(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH, CELL_HEIGHT / 6);
			if(this.getDirection() == DoorDirection.RIGHT)
				g.fillRect(CELL_WIDTH*y + ((CELL_WIDTH*11) / 12), CELL_HEIGHT*x, CELL_WIDTH / 12, CELL_HEIGHT);
			if(this.getDirection() == DoorDirection.LEFT)
				g.fillRect(CELL_WIDTH*y, CELL_HEIGHT*x, CELL_WIDTH / 12, CELL_HEIGHT);
		}
		
	}

	public void labelRooms(Graphics g, BoardCell[][] board) {
		if(labelRoomHere){
			g.setColor(Color.BLACK);
			g.drawString(Board.rooms.get(initial), CELL_WIDTH*y+2, CELL_HEIGHT*(x+1));
		}
	}
	
	
	
}
