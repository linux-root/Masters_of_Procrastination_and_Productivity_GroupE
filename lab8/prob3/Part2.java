import java.util.function.*;

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Apple {
    private double weight;

    public Apple(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

class EmployeeNameComparator {
    public int compare(Employee e1, Employee e2) {
        return e1.getName().compareTo(e2.getName());
    }
}

public class Examples {

    // Method References
    Function<Employee, String> getName = Employee::getName;
    BiConsumer<Employee, String> setName = Employee::setName;
    BiFunction<String, String, Integer> compareTo = String::compareTo;
    BiFunction<Integer, Integer, Double> pow = Math::pow;
    Function<Apple, Double> getWeight = Apple::getWeight;
    Function<String, Integer> parseInt = Integer::parseInt;
    EmployeeNameComparator comp = new EmployeeNameComparator();
    BiFunction<Employee, Employee, Integer> compareEmp = comp::compare;

    // Evaluator Method
    public void evaluator() {
        Employee emp1 = new Employee("Alice");
        Employee emp2 = new Employee("Bob");

        System.out.println("Get Name: " + getName.apply(emp1));
        setName.accept(emp1, "Charlie");
        System.out.println("Updated Name: " + getName.apply(emp1));
        System.out.println("Compare Strings: " + compareTo.apply("apple", "banana"));
        System.out.println("Power: " + pow.apply(2, 3));
        Apple apple = new Apple(1.5);
        System.out.println("Apple Weight: " + getWeight.apply(apple));
        System.out.println("Parsed Int: " + parseInt.apply("1234"));
        System.out.println("Compare Employees: " + compareEmp.apply(emp1, emp2));
    }

    public static void main(String[] args) {
        new Examples().evaluator();
    }
}
