package lesson3.labs.prob4;

public class Trailer extends Property{

	public double computeRent(){
		return 500;
	}

	public Trailer(String address) {
		super.setAddress(address);
	}
}
