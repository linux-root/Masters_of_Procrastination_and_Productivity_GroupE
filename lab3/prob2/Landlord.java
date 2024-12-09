package lesson3.labs.prob2;

import java.util.ArrayList;
import java.util.List;

public class Landlord {
	private List<Building> buildings;
	public Landlord() {
		buildings = new ArrayList<>();
	}
	
	public void addBuilding(double maintCosts, double rentFirstApt) {
		buildings.add(new Building(maintCosts, rentFirstApt));
	}
	
	public List<Building> getBuildings() {
		return buildings;
	}
	
	public double calcProfits() {
		double profit = 0.0;
		for(Building build : buildings) {
			profit += build.calcProfit();
		}
		return profit;
	}
}
