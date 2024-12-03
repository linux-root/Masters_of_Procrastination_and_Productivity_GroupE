package lab4c;

import java.time.LocalDate;

public class Salaried extends Employee{
    private double salary;

    @Override
    double calcGrossPay(LocalDate monthYear) {
        return salary;
    }

    public Salaried(String empId, double salary){
        super(empId);
        this.salary = salary;
    }
}
