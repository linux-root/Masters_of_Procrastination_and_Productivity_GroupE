import java.util.Optional;

public class MySingletonLazy {
    private static MySingletonLazy instance = null;
    public void greet(){
        System.out.println("Hello");
    }

    private MySingletonLazy(){};
    public static MySingletonLazy getInstance(){
        return Optional.ofNullable(instance).orElseGet(() -> {
            instance = new MySingletonLazy();
            return instance;
        });
    }

    public static void main(String[] args) {
        MySingletonLazy.getInstance().greet();
    }

}
