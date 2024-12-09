package lab4c;

import java.time.LocalDate;

public class Hourly extends Employee{
    private double hourlyWage;
    private double hourPerWeek;

    @Override
    double calcGrossPay(LocalDate monthYear) {
        return hourlyWage * hourlyWage * 4;
    }

    public Hourly(String empId, double hourlyWage, double hourPerWeek) {
       super(empId);
       this.hourlyWage = hourlyWage;
       this.hourPerWeek = hourPerWeek;
    }
}
