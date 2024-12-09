package lab4c;

public class Paycheck {
    private double grossPay;
    private double fica;
    private double state;
    private double local;
    private double medicare;
    private double socialSecurity;

    public void print(){}

    public double getNetPay(){
        return grossPay - fica -state - local - medicare - socialSecurity;
    }


    Paycheck(double grossPay){
        this.grossPay = grossPay;
        this.fica = grossPay * 0.23;
        this.state = grossPay * 0.05;
        this.local = grossPay *0.01;
        this.medicare = grossPay * 0.03;
        this.socialSecurity = grossPay * 0.075;
    }
}
