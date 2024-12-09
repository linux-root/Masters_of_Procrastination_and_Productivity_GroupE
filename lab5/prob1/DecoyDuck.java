package day11;

public class DecoyDuck extends Duck {
    DecoyDuck() {
      super(new CannotFly(), new MuteQuack());
    }
}
