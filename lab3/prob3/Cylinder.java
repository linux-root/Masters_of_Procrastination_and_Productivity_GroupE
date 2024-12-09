package prob2;

public class Cylinder extends Circle {
    private double height;

    public Cylinder(double radius, double height) {
        super(radius); 
        this.height = height;
    }

    public double computeVolume() {
        return computeArea() * height; 
    }
}