package day11;

public class RubberDuck extends Duck {
    RubberDuck() {
        super(new CannotFly(), new Squeak());
    }
}
