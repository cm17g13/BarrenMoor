
public class LeafBlower extends Item {
	
	public LeafBlower(int x, int y) {
		super(x, y);	
	}

	public void pickUpItem() {
		System.out.println("You found a leaf blower");
		System.out.println("This lets you see further in the fog");
	}

	public String icon() {
		return "~";
	}
	

}
