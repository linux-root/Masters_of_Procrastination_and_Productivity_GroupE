## 1.Explain why this has happened. Then provide a solution by replacing inheritance with composition.
```java
    Person p1 = new PersonWithJob("Joe", 30000);
    Person p2 = new Person("Joe");
    System.out.println("p1.equals(p2)? " + p1.equals(p2)); // false
	System.out.println("p2.equals(p1)? " + p2.equals(p1)); // true
```
- `p1.equals(p2)` is false because in the implementation of  PersonWithJob (class of p1), it has this line `if(!(aPerson instanceof PersonWithJob)) return false;` while aPerson (p2) has type Person which is NOT PersonWithJob
- `p2.equals(p1)` is true because p2 is instance of Person, and the name fields of two objects are equal
- 
## 2.
## 3.
## 4.
