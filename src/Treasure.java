
public class Treasure extends Item {
	
	public Treasure(int x, int y) {
		super(x, y);
	}

	@Override
	public void pickUpItem() {
		System.out.println("You found The treasure");
		System.out.println("You can stop playing now");
	}
	
	public String icon() {
		return "X";
	}

}
