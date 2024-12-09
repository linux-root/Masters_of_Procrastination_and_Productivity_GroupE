package day11;

public class Main {
    public static void main(String[] args) {
        Duck[] ducks = new Duck[]{
                new MallardDuck(),
                new DecoyDuck(),
                new RedheadDuck(),
                new RubberDuck()
        };

        for (Duck duck : ducks) {
            System.out.println(duck.getClass().getSimpleName() + ":");
            duck.display();
            duck.fly();
            duck.quack();
            duck.swim();
        }
    }
}
