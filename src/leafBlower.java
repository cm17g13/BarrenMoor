
public class LeafBlower extends Item {

	private int sightDistance;
	
	public LeafBlower(int x, int y, int sightDistance) {
		super(x, y);
		this.sightDistance = sightDistance;
		
	}
	
	public int getSightDistance() {
		return sightDistance;
	}
	
	public void setSightDistance(int sightDistance) {
		this.sightDistance = sightDistance;
	}
	

}
