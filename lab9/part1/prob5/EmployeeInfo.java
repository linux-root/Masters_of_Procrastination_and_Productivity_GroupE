package lesson9.lecture.comparators2;

import java.util.*;

public class EmployeeInfo {
	static enum SortMethod {BYNAME, BYSALARY};

	private final Map<SortMethod, Comparator<Employee>> comparatorMap = Map.of(
			SortMethod.BYNAME, Comparator.comparing(Employee::getName),
			SortMethod.BYSALARY, Comparator.comparing(Employee::getSalary)
	);

	public void sort(List<Employee> emps, final SortMethod method) {
		Collections.sort(emps, comparatorMap.get(method));
	}
	
	public static void main(String[] args) {
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee("Joe", 100000));
		emps.add(new Employee("Tim", 50000));
		emps.add(new Employee("Andy", 60000));
		EmployeeInfo ei = new EmployeeInfo();
		ei.sort(emps, SortMethod.BYNAME);
		System.out.println(emps);
		//same instance
		ei.sort(emps, SortMethod.BYSALARY);
		System.out.println(emps);
	}
}
