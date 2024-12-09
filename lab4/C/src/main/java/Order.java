package lab4c;

import java.time.LocalDate;

public class Order {
    private int orderNo;
    private LocalDate date;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate(){
        return date;
    }

    Order(int orderNo, LocalDate date, double amount) {
        this.orderNo = orderNo;
        this.date = date;
        this.amount = amount;
    }

}
