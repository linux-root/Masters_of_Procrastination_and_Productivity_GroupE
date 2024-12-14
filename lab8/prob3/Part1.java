import java.util.function.*;

class Employee {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Apple {
    private double weight;

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

    public static void main(String[] args) {
        // A. (Employee e) -> e.getName()
        Function<Employee, String> getName1 = (Employee e) -> e.getName();
        Function<Employee, String> getName2 = Employee::getName; 
        // Method reference type: Class::instanceMethod
        
        // B. (Employee e, String s) -> e.setName(s)
        BiConsumer<Employee, String> setName1 = (Employee e, String s) -> e.setName(s);
        BiConsumer<Employee, String> setName2 = Employee::setName; 
        // Method reference type: Class::instanceMethod
        
        // C. (String s1, String s2) -> s1.compareTo(s2)
        BiFunction<String, String, Integer> compareTo1 = (s1, s2) -> s1.compareTo(s2);
        BiFunction<String, String, Integer> compareTo2 = String::compareTo; 
        // Method reference type: Class::instanceMethod
        
        // D. (Integer x, Integer y) -> Math.pow(x, y)
        BiFunction<Integer, Integer, Double> pow1 = (x, y) -> Math.pow(x, y);
        BiFunction<Integer, Integer, Double> pow2 = Math::pow; 
        // Method reference type: Class::staticMethod
        
        // E. (Apple a) -> a.getWeight()
        Function<Apple, Double> getWeight1 = (Apple a) -> a.getWeight();
        Function<Apple, Double> getWeight2 = Apple::getWeight; 
        // Method reference type: Class::instanceMethod
        
        // F. (String x) -> Integer.parseInt(x)
        Function<String, Integer> parseInt1 = (String x) -> Integer.parseInt(x);
        Function<String, Integer> parseInt2 = Integer::parseInt; 
        // Method reference type: Class::staticMethod
        
        // G. EmployeeNameComparator comp = new EmployeeNameComparator();
        EmployeeNameComparator comp = new EmployeeNameComparator();
        BiFunction<Employee, Employee, Integer> compareEmp1 = (e1, e2) -> comp.compare(e1, e2);
        BiFunction<Employee, Employee, Integer> compareEmp2 = comp::compare; 
        // Method reference type: instance::instanceMethod
    }
}
