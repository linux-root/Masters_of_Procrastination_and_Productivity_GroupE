package lab4c;

import java.time.LocalDate;

abstract class Employee {
    protected String empId;
    public void print(){}

    public Employee(String empId) {
        this.empId = empId;
    }

    public Paycheck calcCompensation(LocalDate monthYear){
        double grossPay = calcGrossPay(monthYear);
        return new Paycheck(grossPay);
    }

    abstract double calcGrossPay(LocalDate monthYear);
}
