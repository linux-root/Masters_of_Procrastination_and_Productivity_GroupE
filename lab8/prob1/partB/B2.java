package prob1.partB;

import java.util.function.Supplier;

public class B2 {

	public static void main(String[] args) {
		Supplier<Double> r1 = Math::random;
		System.out.print(r1.get());

	}

}
