package lesson9.labs.prob9;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        printSquares(4);
    }

    public static void printSquares(int num){
        IntStream.iterate(1, (x)->x+1).limit(num).forEach(x -> System.out.println(x * x));
    }
}
