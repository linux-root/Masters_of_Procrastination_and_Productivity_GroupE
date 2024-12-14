package prob1.partB;

import java.util.function.Supplier;

public class B3 {

	public static void main(String[] args) {
		Supplier<Double> s = new MySupplier();
		
		System.out.print(s.get());

	}

	static class MySupplier implements Supplier<Double>{

		@Override
		public Double get() {
			return Math.random();
		}
		
	}
}
