import java.util.Random;

public class Grid {
	
	private int dimensions;
	private String[][] grid;
	private int playerX;
	private int playerY;
	private int treasureX, treasureY;
	Random rand = new Random();
	private final String N = "north", E = "east", S = "south", W = "west";
	
	//Default constructor with no parameters
	public Grid() {
		this.dimensions = 20;
		
		//0 - 19
		//no print at 0 or 19
		//from 10 to 18
		//from 1 to 9
		/*
		this.playerX = rand.nextInt((dimensions/2) - 2) + (dimensions/2);
		this.playerY = rand.nextInt((dimensions/2) - 2) + (dimensions/2);
		this.treasureX = rand.nextInt((dimensions/2) - 2) + 1;
		this.treasureY = rand.nextInt((dimensions/2) - 2) + 1;
		*/
		//So im played at 10, 10
		//Treasure needs to spawn at least 5 away 
		this.playerX = Math.floorDiv(dimensions, 2);
		this.playerY = Math.floorDiv(dimensions, 2);
		spawnTreasure();
		//spawnTreasureRand();
		
		createGrid();
	}
	
	public void spawnTreasureRand() {
		this.playerX = rand.nextInt((dimensions/2) - 2) + (dimensions/2);
		this.playerY = rand.nextInt((dimensions/2) - 2) + (dimensions/2);
		this.treasureX = rand.nextInt((dimensions/2) - 2) + 1;
		this.treasureY = rand.nextInt((dimensions/2) - 2) + 1;
	}
	
	public void spawnTreasure() {
		if(rand.nextBoolean()) {
			if(rand.nextBoolean()) {
				this.treasureX = rand.nextInt(Math.floorDiv(dimensions, 4) - 1) + 1;
				this.treasureY = rand.nextInt(dimensions-2) + 1; 
			} else {
				this.treasureX = rand.nextInt(Math.floorDiv(3*dimensions, 4) - 1) + 1;
				this.treasureY = rand.nextInt(dimensions-2) + 1;
			}
		} else {
			if(rand.nextBoolean()) {
				this.treasureY = rand.nextInt(Math.floorDiv(dimensions, 4) - 1) + 1;
				this.treasureX = rand.nextInt(dimensions-2) + 1; 
			} else {
				this.treasureY = rand.nextInt(Math.floorDiv(3*dimensions, 4) - 1) + 1;
				this.treasureX = rand.nextInt(dimensions-2) + 1;
			}
		}
	}
	
	//Custom constructor with given parameters
	public Grid(String[][] grid, int dimensions, int playerX, int playerY, int treasureX, int treasureY) {
		
		this.dimensions = dimensions;
		this.playerX = playerX;
		this.playerY = playerY;
		this.treasureX = treasureX;
		this.treasureY = treasureY;
		if(grid == null) 
		{
			createGrid();
		} else {
			this.grid = grid;
		}
	}
	
	public void createGrid() {
		grid = new String[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; ++j) {
				//create fog by default
				String value = "?";
				if(i == 0||i == (dimensions-1) || j == 0 || j == (dimensions-1)) { //The walls of the grid are marked with #
					value = "#";
				} else if(i == playerX && j == playerY) { // the player is marked with an X
					value = "@";
				} /*else if(i == treasureX && j == treasureY) {
					value = "X";
				} */
				grid[i][j] = value;
				
			}
		}
		removeFog();
	}
	
	public boolean move(String input) {
		System.out.println();
		int newPosX;
		int newPosY;
		String newLocation;
		
		switch (input) {
		case N:
	   		newLocation = grid[playerX][playerY - 1];
	   		if (newLocation == "#") {
	   			return false;
	   		}
	   		newPosX = playerX;
	   		newPosY = playerY - 1;
	   		break;
	   		 
	   	case E:
	   		newLocation = grid[playerX + 1][playerY];
	   		if (newLocation == "#") {
	   			return false;
	   		}
	   		newPosX = playerX + 1;
	   		newPosY = playerY;
	   		break;
	   		 
	   	case S:
	   		newLocation = grid[playerX][playerY + 1];
	   		if (newLocation == "#") {
	   			return false;
	   		}
	   		newPosX = playerX;
	   		newPosY = playerY + 1;
	   		break;
	   		
	   	case W:
	   		newLocation = grid[playerX - 1][playerY];
	   		if (newLocation == "#") {
	   			return false;
	   		}
	   		newPosX = playerX - 1;
	   		newPosY = playerY;
	   		break;
	   	default:
	   		return false;
	   	}
		
		grid[newPosX][newPosY] = "@";
		grid[playerX][playerY] = ".";		
		playerX = newPosX;
		playerY = newPosY;
		removeFog();
	   	
	   	return true;
	}
	
	//i/j 0 to 2
	// so they're - 1 from player and + 1 from player
	//i = 0, = player-1 
	
	public void removeFog() {
		int sightDistance = 1;
		for(int i = playerX-sightDistance; i <= playerX +sightDistance; i++) {
			for(int j = playerY-sightDistance; j <= playerY+sightDistance; j++) {
				if(i == treasureX && j == treasureY) {
					grid[i][j] = "X"; //X marks the spot
				} else {
					grid[i][j] = ".";
				}
				
			}
		}
		grid[playerX][playerY] = "@";
	}
	
	public void compass() {
		System.out.println(Math.hypot(playerX - treasureX, playerY - treasureY));
	}
	
	public boolean foundTreasure() {
		if (playerX == treasureX && playerY == treasureY) {
			return true;
		}
		return false;	
	}
	
	
	public void printGrid() {
		for (int i = 0; i < dimensions; ++i) {
			for (int j = 0; j < dimensions; ++j) {
				System.out.print(grid[j][i] + " ");
			}
			System.out.println();
		}
	}
}
