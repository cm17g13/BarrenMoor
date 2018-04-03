
public class Treasure extends Item {
	
	public Treasure(int x, int y) {
		super(x, y);
	}

	public void pickUpItem() {
		System.out.println("You found The treasure");
	}
	
	public String icon() {
		return "X";
	}

}
