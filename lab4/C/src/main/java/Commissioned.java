package lab4c;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Commissioned extends Employee{
    private double baseSalary;
    private double commissionRate;

    private List<Order> orders = new ArrayList<>();


    public void addOrder(int orderNo, LocalDate date, double amount) {
        orders.add(new Order(orderNo, date, amount));
    }

    public Commissioned(String empId, double commissionRate, double baseSalary) {
        super(empId);
        this.baseSalary = baseSalary;
        this.commissionRate = commissionRate;
    }

    @Override
    double calcGrossPay(LocalDate monthYear) {
        double totalAmount = 0;
        for(Order order : orders) {
            boolean matchedMonthYear = monthYear.getYear() == order.getDate().getYear() && monthYear.getMonthValue() == order.getDate().getMonthValue();
            if (matchedMonthYear){
                totalAmount += order.getAmount();
            }
        }
        return baseSalary + commissionRate * totalAmount;
    }
}
