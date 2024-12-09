package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;


public class Building {
	private List<Apartment> apartments = new ArrayList<>();
	private double maintCosts;
	
	Building(double maintCosts, double rentFirstApt) {
		this.maintCosts = maintCosts;
		apartments.add(new Apartment(rentFirstApt));
	}
	
	public void addApartment(double rentFirstApt) {
		apartments.add(new Apartment(rentFirstApt));
	}
	
	public double calcProfit() {
		double grossProfit = 0.0;
		for(Apartment a : apartments) {
			grossProfit += a.getRent();
		}
		return grossProfit - maintCosts;
	}
}
