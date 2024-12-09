package prob2B;

public class Main {

	public static void main(String[] args) {
		Order order = new Order("orderLine 1");
		order.addItem("orderLine 2");
		order.addItem("orderLine 3");
		System.out.println("The Order includes: " + order);
		
	}

}
