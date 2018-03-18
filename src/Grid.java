import java.util.ArrayList;
import java.util.Random;

public class Grid {

	private int dimensions;
	private String[][] grid;
	private ArrayList<Item> items;
	private int sightDistance;
	private int playerX, playerY;
	// private int treasureX, treasureY;
	Random rand = new Random();
	private final String N = "north", E = "east", S = "south", W = "west";

	// Default constructor with no parameters
	public Grid() {
		this.dimensions = 10;
		this.sightDistance = 1;
		items = new ArrayList<Item>();

		// Grid: 0 - 19
		// no print at 0 or 19
		// from 10 to 18
		// from 1 to 9
		// So im played at 10, 10
		// Treasure needs to spawn at least 5 away
		this.playerX = Math.floorDiv(dimensions, 2);
		this.playerY = Math.floorDiv(dimensions, 2);
		boolean sameLocation;
		int[] locations;
		do {
			sameLocation = false;
			locations = spawnItemLocation();
			if(!items.isEmpty()) {
				for (Item item : items) {
					if (item.getX() == locations[0] || item.getY() == locations[1]) {
						sameLocation = true;
					}
				}
			}
		} while (sameLocation);

		items.add(new Treasure(locations[0], locations[1]));

		// spawnTreasureRand();

		createGrid();
	}

	/*
	 * public int[] spawnTreasureRand() { this.playerX = rand.nextInt((dimensions/2)
	 * - 2) + (dimensions/2); this.playerY = rand.nextInt((dimensions/2) - 2) +
	 * (dimensions/2); this.treasureX = rand.nextInt((dimensions/2) - 2) + 1;
	 * this.treasureY = rand.nextInt((dimensions/2) - 2) + 1; }
	 */

	public int[] spawnItemLocation() {
		int itemX;
		int itemY;

		if (rand.nextBoolean()) {
			if (rand.nextBoolean()) {
				itemX = rand.nextInt(Math.floorDiv(dimensions, 4) - 1) + 1;
				itemY = rand.nextInt(dimensions - 2) + 1;
			} else {
				itemX = rand.nextInt(Math.floorDiv(3 * dimensions, 4) - 1) + 1;
				itemY = rand.nextInt(dimensions - 2) + 1;
			}
		} else {
			if (rand.nextBoolean()) {
				itemY = rand.nextInt(Math.floorDiv(dimensions, 4) - 1) + 1;
				itemX = rand.nextInt(dimensions - 2) + 1;
			} else {
				itemY = rand.nextInt(Math.floorDiv(3 * dimensions, 4) - 1) + 1;
				itemX = rand.nextInt(dimensions - 2) + 1;
			}
		}
		int[] location = { itemX, itemY };
		return location;
	}

	// Custom constructor with given parameters
	public Grid(String[][] grid, int dimensions, int sightDistance, int playerX, int playerY, int treasureX,
			int treasureY) {

		this.dimensions = dimensions;
		this.sightDistance = sightDistance;
		this.playerX = playerX;
		this.playerY = playerY;
		items = new ArrayList<Item>();
		items.add(new Treasure(treasureX, treasureY));
		if (grid == null) {
			createGrid();
		} else {
			this.grid = grid;
		}
	}

	public void createGrid() {
		grid = new String[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; ++j) {
				// create fog by default
				String value = "?";
				if (i == 0 || i == (dimensions - 1) || j == 0 || j == (dimensions - 1)) { // The walls of the grid are marked with #
					value = "#";
				} else if (i == playerX && j == playerY) { // the player is marked with an X
					value = "@";
				} /*
					 * else if(i == treasureX && j == treasureY) { value = "X"; }
					 */
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

	// i/j 0 to 2
	// so they're - 1 from player and + 1 from player
	// i = 0, = player-1

	public void removeFog() {
		for (int i = playerX - sightDistance; i <= playerX + sightDistance; i++) {
			for (int j = playerY - sightDistance; j <= playerY + sightDistance; j++) {
				if (grid[i][j] == "#") {
					grid[i][j] = "#";
				} else {
					grid[i][j] = ".";
				}
				
				for (int z = 0; z < items.size(); z++) {
					Item item = items.get(z);
					if (i == item.getX() && j == item.getY()) {
						grid[i][j] = item.icon();
					}
				}
			}
		}
		grid[playerX][playerY] = "@";
	}

	public int[][] getItemLocations() {
		int[][] itemArr = new int[2][items.size()];

		for (int i = 0; i < items.size(); i++) {
			itemArr[0][i] = items.get(i).getX();
			itemArr[1][i] = items.get(i).getY();
		}
		for (Item item : items) {
			item.getX();
			item.getY();
		}
		return itemArr;
	}

	public void addLeafBlower() {
		int x = 4;
		int y = 4;
		items.add(new LeafBlower(x, y, 2));
	}

	public void setSightDistance(int sightDistance) {
		this.sightDistance = sightDistance;
	}

	public void checkItem() {
		for (Item item : items) {
			if (playerX == item.getX() && playerY == item.getY()) {
				item.pickUpItem();
			}
		}
	}

	public void compass() {
		Treasure t = getTreasure();
		System.out.printf("The dial reads: " + "%.2f" + " Meters",
				(float) Math.hypot(playerX - t.getX(), playerY - t.getY()));
		System.out.println();
	}

	public Treasure getTreasure() {
		for (Item item : items) {
			if (item instanceof Treasure) {
				return (Treasure) item;
			}
		}
		return null;
	}

	public boolean foundTreasure() {
		Treasure t = getTreasure();

		if (playerX == t.getX() && playerY == t.getY()) {
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
