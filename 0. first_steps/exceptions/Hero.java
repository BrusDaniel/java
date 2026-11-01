public class Hero {
    private String name;
    private int strength;
    public Hero (String name, int strength){
        this.name = name;
        this.strength = strength;
    }

    public void sayHelloTo (Hero a){
        System.out.println("Hi "+a.name+"!");
    }
    public String getName() {
        return name;
    }
}