package lesson3.labs.prob4;

public class House  extends Property{

	private double lotSize;
	
	
	public House(String address, double lotSize) {
		super.setAddress(address);
		this.lotSize = lotSize;
	}


	public double computeRent(){
		return 0.1 * lotSize;
	}
}
