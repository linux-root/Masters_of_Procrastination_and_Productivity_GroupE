## 1A.

ArrayList implements 6 interfaces:
List<E>, RandomAccess, Cloneable, Serializable, Collection, Iterable

ArrayList extends 1 class:
AbstractList

---

## 1B.

The equals method in the Employee class doesn't override the equals method
from the Object class correctly. When comparing the Employee objects in the list,
the contains method relies on the equals method to determine if an object is in
the list. So the result is false.

to fix this, you should override the equals method from the Object class properly.
```java
	@Override
	public int hashCode() {
		return Objects.hash(name, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(name, other.name) && salary == other.salary;
	}
```
[Source Code](./prob1/partB)

---
## 1C.

HashMap requires both equals and hashCode methods to be overridden in the Employee class.
When you override the equals method, you also need to override the hashCode method.
```java
	@Override 
	public int hashCode() { 
		return Objects.hash(name, salary); 
	}
```
[Source Code](./prob1/partC)

---
## 1D
when you run the removeDuplicates method, the value Employee.visited is modified, leading
to different hash code, and different value of equals();

Solution : change both equals() to and hashCode() to be independent from value of visited:
```java
	@Override
	public int hashCode() {
		return Objects.hash(name, salary);
	}


	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof Employee)) return false;
		Employee emp = (Employee)ob;
		//return emp.name.equals(name) && emp.salary == salary && emp.visited == visited;
		return emp.name.equals(name) && emp.salary == salary ;
	}
```

[Source Code](./prob1/partD)

---
## 1E.

Java 8 supports multiple inheritance only through interfaces with default methods, not through classes. Let's evaluate the two scenarios:

**Scenario : Classes with Abstract Method**

Java does not support multiple inheritance for classes to avoid ambiguity (Diamond Problem). If D extends both B and C, Java will throw a compilation error, preventing the Diamond Problem.


**Scenario : Interface with Abstract Method**

Since Java supports multiple inheritance through interfaces, D must explicitly override method () to resolve the conflict. If D doesn't override method(), Java will throw a compilation error, forcing D to choose which implementation to use.

---
## 2.
```java
public class ForEachExample {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello there", "Goodbye", "Back soon",
                "Away", "On Vacation", "Everywhere you want to be");

        //print each element of the list in upper case format
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(toUpper(s));
            }
        });

        //list.forEach(s -> System.out.println(toUpper(s)));

    }

    public static String toUpper(String s) {
        return s.toUpperCase();
    }

    //implement a Consumer
}

```

[Source Code](./prob2)