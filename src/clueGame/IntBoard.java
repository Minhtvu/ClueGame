package clueGame;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// Contains the grid and adjacency lists
public class IntBoard {

	private BoardCell[][] grid = null;	// Grid to store all the cells
	private Map<BoardCell, ArrayList<BoardCell>> adjacencies = null;	// Maps a cell to a list of its adjacent cells

	
	// Calculates the adjacency list for each grid cell, stores in a map
	public void calcAdjacencies() {
		adjacencies = new HashMap<BoardCell, ArrayList<BoardCell>>(grid.length, grid[0].length);	// Initializing the HashMap
		for (int x=0; x<grid.length; x++) {
			for (int y=0; y < grid[x].length; y++) {
				ArrayList<BoardCell> al = new ArrayList<BoardCell>();	// Temporary list to store the adjacent cells in
				if (x - 1 >= 0) al.add(grid[x-1][y]);					// Adds the left cell if it exists
				if (y - 1 >= 0) al.add(grid[x][y-1]);					// Adds the top cell if it exists
				if (x + 1 < grid.length) al.add(grid[x+1][y]);			// Adds the right cell if it exists
				if (y + 1 < grid[x].length) al.add(grid[x][y+1]);		// Adds the top cell if it exists
				adjacencies.put(grid[x][y], al);						// Puts the list of adjacent cells into the map
			}			
		}		
	}	

	
	// Calculates the targets that are pathLength away from the startCell, stores in local variable
	private void calcTargets(BoardCell startCell, int pathLength, Set<BoardCell> visited, Set<BoardCell> targetSet) {
		Set<BoardCell> myVisited = new HashSet<BoardCell>(visited);		// Initializes a local HashSet of visited cells
																		// This is to ensure the lists of past recursions don't get accidentally manipulated
		myVisited.add(startCell);										// Adds the current cell to the list of cells that have been visited
		if (pathLength == 0) { 
			targetSet.add(startCell); return; 							// Adds the cell to the set of targets if we're at the end
		}
		for (BoardCell o: adjacencies.get(startCell)) {					// Iterates through each adjacent cell
			if (!myVisited.contains(o)) {								// If we haven't visited this adjacent cell...
				calcTargets(o, pathLength-1, myVisited, targetSet);		// Let's move to it!
			}
		}
	}

	// Constructor. Takes in a size of the matrix (as rows, columns) and the name of the file to read from.
	// Creates an empty grid, populates it from the file, then calculates the adjacencies for each cell.
	public IntBoard(final int ROWS, final int COLS, String filename) {
		super();
		grid = new BoardCell[COLS][ROWS];
		try {	// In case the file can't be found
			FileReader fin = new FileReader(filename);	// Initializing a bunch of variables.
			Scanner in = new Scanner(fin);
			String temp;
			int row=0, col=0;
			while (in.hasNext()) {
				temp = in.nextLine();					// Reads in one line at a time
				Scanner line = new Scanner(temp);
				line.useDelimiter(",");					// Separates it based on commas
				while (line.hasNext()) {
					String spot = line.next();			// Reads in each letter and...
					char initial = spot.charAt(0);
					grid[row][col] = new BoardCell(row, col, initial, initial, false);	// puts it in the appropriate place on the grid
					col++;					
				}
				col=0;
				row++;
				line.close();
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Board game file not found.");	// In case the file can't be found
		}
		calcAdjacencies();	// Calculates the adjacencies of each cell. See the code above for more.
	}

	
	// Returns a Set of the cells that are valid targets from the given cell at the given distance
	public Set<BoardCell> getTargets(BoardCell startCell, int pathLength) {
		Set<BoardCell> targetSet = new HashSet<BoardCell>();	// Set of potential targets to move to
		Set<BoardCell> visited = new HashSet<BoardCell>();		// (Empty) set of visited cells. Will be populated in the recursion
		calcTargets(startCell, pathLength, visited, targetSet);	// Begins to recursively call the target calculation function
		return targetSet;
	}

	
	// Returns an ArrayList of the cells that are adjacent to the cell passed as a parameter
	public ArrayList<BoardCell> getAdjList(BoardCell cell) {
		return adjacencies.get(cell);
	}

	
	// Returns the cell at the given location
	public BoardCell getCell(int row, int col){
		return grid[row][col];
	}

	
	public static void main(String[] args) {
		// For future use:
		IntBoard myBoard = new IntBoard(13, 26, "Clue Layout.csv");
		
		// Test code to try to discern why some of our tests were failing
		/*IntBoard x = new IntBoard(4,4, "Clue Layout2.csv");
		BoardCell cell = x.getCell(3, 3);
		Set<BoardCell> targets = x.getTargets(cell, 3);
		System.out.println(targets.size());
		System.out.println(targets);*/
	}
}
