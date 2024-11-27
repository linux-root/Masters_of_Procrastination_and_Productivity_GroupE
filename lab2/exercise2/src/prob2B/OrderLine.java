package prob2B;

public class OrderLine {
	private String name;
	private Order order;
	
	OrderLine(String name, Order order){
		this.name = name;
		this.order = order;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
