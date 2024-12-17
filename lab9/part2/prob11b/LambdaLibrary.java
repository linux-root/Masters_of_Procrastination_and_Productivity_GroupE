package lesson9.labs.prob11b;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaLibrary {
    public static final TriFunction<List<Employee>, Integer, Character, String> FILTER_EMPLOYEES = 
        (emps, salaryThreshold, startingLetter) -> emps.stream()
            .filter(e -> e.getSalary() > salaryThreshold) 
            .filter(e -> e.getLastName().charAt(0) > startingLetter) 
            .map(e -> e.getFirstName() + " " + e.getLastName()) 
            .sorted()
            .collect(Collectors.joining(", "));
}
