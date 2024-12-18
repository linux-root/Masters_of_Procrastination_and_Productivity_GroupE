## 7. Ordering

```java
//Orders the integers according to this pattern:
// 0, -1, 1, -2, 2, -3, 3, . . .
//Using this ordering, this method sorts the list as part of 
//a stream pipeline, and prints to the console
public static void ordering1(List<Integer> list) {
    List<Integer> orderedList = list.stream()
            .sorted(Comparator.comparing(Math::abs))
            .collect(Collectors.toList());
    System.out.println(orderedList);

}

//Orders words by first reversing each and comparing 
//in the usual way.  So 
//    cba precedes bed
//since
//    cba.reverse() precedes bed.reverse()
//in the usual ordering
//Using this ordering, this method sorts the list as part of 
//a stream pipeline, and prints to the console
public static void ordering2(List<String> words) {
    List<String> sortedStrings = words.stream()
            .sorted(Comparator.comparing(word -> new StringBuilder(word).reverse().toString()))
            .collect(Collectors.toList());

    System.out.println(sortedStrings);

}
```

## 8. [Source code](./prob8)
---
## 9. [Source code](./prob9)
```java
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        printSquares(4);
    }

    public static void printSquares(int num){
        IntStream.iterate(1, (x)->x+1).limit(num).forEach(x -> System.out.println(x * x));
    }
}
```
---
## 10. [Source code](./prob10)
---
## 11. 
[Source code 11A](./prob11a)
[Source code 11B](./prob11b)
---
## 12. [Source code](./prob12)
---
## 13. [Source code](./prob13)
---
## 14. Lazy singleton
[source code](./prob14/MySingletonLazy.java)
```java
import java.util.Optional;

public class MySingletonLazy {
    private static MySingletonLazy instance = null;
    public void greet(){
        System.out.println("Hello");
    }

    private MySingletonLazy(){};
    public static MySingletonLazy getInstance(){
        return Optional.ofNullable(instance).orElseGet(() -> {
            instance = new MySingletonLazy();
            return instance;
        });
    }

    public static void main(String[] args) {
        MySingletonLazy.getInstance().greet();
    }

}
```
---