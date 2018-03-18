import java.util.Scanner;

public class TextHandler {

	private Scanner input;
	//private long startTime;

	public TextHandler() {
		input = new Scanner(System.in);
	}

	public void printWelcome() {
		System.out.println("           Welcome to           ");
		System.out.println("---------------------------------");
		System.out.println("The adventure of the barren moor");
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println("You awaken to find yourself in a barren moor.");
	}

	public String sanitisedInput() {
		return input.nextLine().toLowerCase().trim();
	}

	public void initialLook() {
		System.out.println("Grey foggy clouds float oppressively close to you,");
		System.out.println("reflected in the murky grey water which reaches up your shins.");
		System.out.println("Some black plants barely poke out of the shallow water.");
		System.out.println("Try \"north\",\"south\",\"east\",or \"west\"");
		System.out.println("You notice a small watch-like device in your left hand.");
		System.out.println("It has hands like a watch, but the hands don't seem to tell time.");
		System.out.println("Try \"move\", \"compass\" or \"map\"");
	}
	
	public void whatDo() {
		System.out.println("What would you like to do?");
	}
	
	public void youWon() {
		System.out.println("Congratulations, you found the treaure");
	}
	
	public void timeLeft(Long start) {
		
	}

	public String readInput() {
		String userInput = sanitisedInput();

		switch (userInput) {
		case "move":
			System.out.println("What direction would you like to move?");
			return chooseDirection(sanitisedInput());

		case "compass":
			return userInput;

		case "map":
			return userInput;
			
		case "north":
		case "east":
		case "south":
		case "west":
			return userInput;
	
		default:
			System.err.println("Sorry, that wasn't a valid input");
			System.err.println("Try \"move\", \"compass\" or \"map\"");
			return "";
		}

	}

	public String chooseDirection(String direction) {
		switch (direction) {
		case "north":
		case "east":
		case "south":
		case "west":
			return direction;
		default:
	   		System.err.println("Sorry that was not a correct input, please try again");
	   		return "no direction";
		

		}

	}

}
