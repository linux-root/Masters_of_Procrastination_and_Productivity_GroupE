package lesson3.labs.prob2;

import java.util.List;


public class Main {

	public static void main(String[] args) {
		Landlord landlord = new Landlord();	
		landlord.addBuilding(200, 400);
		landlord.addBuilding(200, 300);
		
		List<Building> buildings = landlord.getBuildings();
		buildings.get(0).addApartment(500);
		buildings.get(0).addApartment(600);
		
		buildings.get(1).addApartment(400);
		buildings.get(1).addApartment(450);

		
		System.out.println(landlord.calcProfits());
	}

}
