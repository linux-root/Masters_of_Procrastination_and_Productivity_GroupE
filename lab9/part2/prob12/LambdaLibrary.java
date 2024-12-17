package prob12;

import java.util.List;
import java.util.stream.Collectors;


public class LambdaLibrary {

	public static final TriFunction<List<Employee>, Integer, Character, Long> LAMBDA =
				(emps, salaryBound, letter) -> 
			            emps.stream()
			            	.filter((Employee e) -> e.getSalary() > salaryBound)
			            	.filter((Employee e) -> e.getLastName().startsWith(""+letter))
			            	.count();
			            
			            
    public static final TriFunction<List<Employee>, Integer, Character, String> LAMBDA2 =
			(emps, salaryBound, letter) -> 
		            emps.stream()
		            	.filter((Employee e) -> e.getSalary() > salaryBound)
		            	.filter((Employee e) -> e.getFirstName().startsWith(""+letter))
		            	.map((Employee e) -> Employee.fullName(e).toUpperCase())
		            	.sorted().collect(Collectors.joining(", "));
}
