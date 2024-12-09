package lesson3.labs.prob4;

public class Driver {

	public static void main(String[] args) {

		Property[] objects = { new House("address1",9000), new Condo("address2", 2), new Trailer("address3") };
		double totalRent = Admin.computeTotalRent(objects);
		System.out.println(totalRent);
	}
}
