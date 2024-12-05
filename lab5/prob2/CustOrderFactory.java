package prob2;

import java.time.LocalDate;

public class CustOrderFactory {
	public static Customer createCustomer(String name) {
		return new Customer(name);
	}

	public static Order addOrder(Customer cust, LocalDate orderDate) {
		Order ord = new Order(orderDate);
		cust.addOrder(ord);
		return ord;
	}

	public static Item newItem(String name) {
		return new Item(name);
	}
	
	public static void addItem(Order order, Item item) {
		order.addItem(item);
	}
}
