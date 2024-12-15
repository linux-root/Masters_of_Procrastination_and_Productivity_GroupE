package lesson9.labs.prob4;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class PrimeStream {
    private static Integer nextPrime(Integer currentPrime){
        return nextPrime(currentPrime, currentPrime + 1);
    }

    private static Integer nextPrime(Integer currentPrime, Integer candidate){
        for (int i = 2; i <= currentPrime; i++) {
            if (candidate % i == 0) {
                return nextPrime(currentPrime, candidate + 1);
            }
        }
        return candidate;
    }

    // 4A
    private static final Stream<Integer> primes = Stream.iterate(2, PrimeStream::nextPrime);

    // 4B Solution: always create new Stream because the Stream is stateful and cannot be used after being closed
    private static final Supplier<Stream<Integer>> primesSupplier = () -> Stream.iterate(2, PrimeStream::nextPrime);

    /*** 4B
     * Next, create a variation of the primes Stream that can be called multiple times by a
     * method printFirstNPrimes(long n), which prints to the console the first n prime
     * numbers. Note that the Stream primes that you created in part A cannot be used a
     * second time; how can you get around that limitation? Prove that you succeeded by calling
     * the method printFirstNPrimes(long n) (from a main method) more than once.
     */
    static void printFirstNPrimes(long n) {
        //primes.limit(n).forEach(System.out::println); // <---- Have issue : stream has already been operated upon or closed
        primesSupplier.get().limit(n).forEach(System.out::println);
    }

    public static void main(String[] args) {
        printFirstNPrimes(10);
        System.out.println("====");
        printFirstNPrimes(5);
    }
}
