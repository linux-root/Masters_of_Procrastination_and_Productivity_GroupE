package prob2;

public class CylinderComposition {
    private CircleComposition base; 
    private double height;

    public CylinderComposition(double radius, double height) {
        this.base = new CircleComposition(radius); 
        this.height = height;
    }

    public double computeVolume() {
        return base.computeArea() * height; 
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public CircleComposition getBase() {
        return base;
    }

    public void setBase(CircleComposition base) {
        this.base = base;
    }
}
