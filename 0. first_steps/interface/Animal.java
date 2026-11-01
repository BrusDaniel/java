package ex2;

public class Animal implements IAnimalBehaviors {
    @Override
    public void move() {
        System.out.println("Zwierzę porusza się");
    }

    @Override
    public void speak() {
        System.out.println("Zwierzę wydaje dźwięk");
    }
}
