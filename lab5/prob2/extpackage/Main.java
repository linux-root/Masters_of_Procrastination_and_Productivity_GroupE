package prob2.extpackage;

import java.time.LocalDate;

import prob2.CustOrderFactory;
import prob2.Customer;
import prob2.Order;

public class Main {
	public static void main(String[] args) {
		Customer cust = CustOrderFactory.createCustomer("Bob");
		Order order = CustOrderFactory.addOrder(cust, LocalDate.now());
		CustOrderFactory.addItem(order, CustOrderFactory.newItem("Shirt"));
		CustOrderFactory.addItem(order, CustOrderFactory.newItem("Laptop"));

		order = CustOrderFactory.addOrder(cust, LocalDate.now());
		CustOrderFactory.addItem(order, CustOrderFactory.newItem("Pants"));
		CustOrderFactory.addItem(order, CustOrderFactory.newItem("Knife set"));

		System.out.println(cust.getOrders());
	}
}

		
