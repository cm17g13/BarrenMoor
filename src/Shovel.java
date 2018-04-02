
public class Shovel extends Item {
	
	public Shovel(int x, int y, int sightDistance) {
		super(x, y);
		
	}
	
	public void pickUpItem() {
		System.out.println("You found the shovel");
		System.out.println("This lets you dig up treasure");
	}

	public String icon() {
		return "S";
	}
	

}