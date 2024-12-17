# 10. Short Answer:
## A) What happens when the following code is executed?
```java
public static void main(String[] args) {
      IntStream ones = IntStream.generate(() -> 1).distinct();
      ones.forEach(System.out::println);
}
```
Explain. What would be a quick way to fix this?


The provided code snippet will **cause an infinite loop** when executed. Here's why:

1. **`IntStream.generate(() -> 1)`**:
   - This creates an infinite stream of the value `1`. The stream continuously generates `1` with no termination.

2. **`.distinct()`**:
   - This operation ensures that only distinct values are passed through the stream.
   - However, since the stream only generates `1`, the distinct operation will see only one value (`1`) and effectively attempt to terminate the stream after outputting the first `1`.

3. **`.forEach(System.out::println)`**:
   - This operation consumes the stream and prints each element. However, due to the behavior of `.distinct()`, the stream doesn't actually terminate because the `.distinct()` operation continues to wait for more values to check for uniqueness, which never come. 

The program **enters an infinite loop** while the `.distinct()` operation keeps waiting for the next value in the infinite stream, even though no new distinct value will ever appear.

## Fix
You can limit the infinite stream by using a **short-circuiting terminal operation**, such as `.limit(n)`, which restricts the stream to `n` elements. For example:

```java
public static void main(String[] args) {
    IntStream ones = IntStream.generate(() -> 1).limit(2).distinct();
    ones.forEach(System.out::println);
}
```

## B) You have a Stream of Strings called stringStream consisting of the values “Bill”, “Thomas”, and “Mary”. Write the one line of code necessary to print this stream to the console so that the output looks like this: Bill, Thomas, Mary



```java
Stream<String> stringStream  =  Stream.of("Bill", "Thomas", "Mary");

System.out.println(stringStream.collect(Collectors.joining(", ")));
```
## C) You have a Stream of Integers called myIntStream and you need to output both the maximum and minimum values somehow, making use of this stream only once. Write compact code that efficiently accomplishes this.

```java
Stream<Integer> myIntStream  =  Stream.of(1, 2, 3, 4, 5);

IntSummaryStatistics  summary  =  myIntStream.collect(Collectors.summarizingInt(x->x));

System.out.println(summary.getMin());

System.out.println(summary.getMax());
```