
public class RunnerClass {
	
	public static void main(String[] args) {
		
		Grid grid = new Grid();
		//grid.removeFog();
		grid.printGrid();
		
		TextHandler textHandler = new TextHandler();
		textHandler.printWelcome();
		textHandler.initialLook();
		
		boolean play = true;
		//Long startTime = System.nanoTime();
		while(play) {
			textHandler.whatDo();
			String input = textHandler.readInput();
			grid  = pipeInput(input, grid);

			if(grid.checkItem()) {
				play = false;
				textHandler.youWon();
			}
			
		}		
	}
	
	public static Grid pipeInput(String input, Grid grid) {
		if(input.equals("north")||input.equals("east")||input.equals("south")||input.equals("west")) {
			if(!grid.move(input)) {
				System.err.println("Player did not move");
			}
		} else if(input.equals("compass")) {
			grid.compass();
		} else if(input.equals("map")) {
			grid.printGrid();
		}
		return grid;
	}
}
