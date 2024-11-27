package prob2B;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<OrderLine> items = new ArrayList<>();
	
	Order(String name){
		if(items == null || items.size() == 0) {
			OrderLine item = new OrderLine(name, this);
			items.add(item);
		}
	}
	
	public void addItem(String name) {
		OrderLine item = new OrderLine(name, this);
		items.add(item);
	}
	
	
	@Override
	public String toString() {
		return items.toString();
	}
}
