package day11;

class Duck{
    private final FlyBehavior flyBehavior;
    private final QuackBehavior quackBehavior;

    Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
        this.flyBehavior=flyBehavior;
        this.quackBehavior=quackBehavior;
    }

    public void quack(){
        this.quackBehavior.quack();
    }
    public void display(){
        System.out.println("   display");
    }

    public void fly(){
        flyBehavior.fly();
    }

    public void swim(){
        System.out.println("   swimming");
    }


}
