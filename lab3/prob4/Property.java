package lesson3.labs.prob4;

public abstract class Property {
	private String address;
	abstract double computeRent();
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	};
}
