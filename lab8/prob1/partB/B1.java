package prob1.partB;

import java.util.function.Supplier;

public class B1 {

	public static void main(String[] args) {
		Supplier<Double> r1 = Math::random;
		Supplier<Double> r2 = () -> Math.random();
		
		System.out.println(r1.get());
		System.out.println(r2.get());
	}

}
