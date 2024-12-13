# 1 A

ArrayList implements 6 interfaces:
List<E>, RandomAccess, Cloneable, Serializable, Collection, Iterable

ArrayList extends 1 class:
AbstractList

# 1 B

The equals method in the Employee class doesn't override the equals method
from the Object class correctly. When comparing the Employee objects in the list,
the contains method relies on the equals method to determine if an object is in
the list. So the result is false.

to fix this, you should override the equals method from the Object class properly.

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
	[Source Code](https://github.com/linux-root/Masters_of_Procrastination_and_Productivity_GroupE/tree/main/lab7/prob1/partB)
	
# 1 C

HashMap requires both equals and hashCode methods to be overridden in the Employee class.
When you override the equals method, you also need to override the hashCode method.

	@Override 
	public int hashCode() { 
		return Objects.hash(name, salary); 
	}
[Source Code](https://github.com/linux-root/Masters_of_Procrastination_and_Productivity_GroupE/tree/main/lab7/prob1/partC)

# 1 D

when you run the removeDuplicates method, the value of hashCode is modified, leading 
to different hash code.

	@Override
	public int hashCode() {
		return Objects.hash(name, salary);
	}
[Source Code](https://github.com/linux-root/Masters_of_Procrastination_and_Productivity_GroupE/tree/main/lab7/prob1/partD)

# 1 E

Java 8 supports multiple inheritance only through interfaces with default methods, not through classes. Let's evaluate the two scenarios:

**Scenario : Classes with Abstract Method**

Java does not support multiple inheritance for classes to avoid ambiguity (Diamond Problem). If D extends both B and C, Java will throw a compilation error, preventing the Diamond Problem.


**Scenario : Interface with Abstract Method**

Since Java supports multiple inheritance through interfaces, D must explicitly override method () to resolve the conflict. If D doesn't override method(), Java will throw a compilation error, forcing D to choose which implementation to use.

# 2
[Source Code](https://github.com/linux-root/Masters_of_Procrastination_and_Productivity_GroupE/tree/main/lab7/prob2)