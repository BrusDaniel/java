public class Animal {
    private String name;
    private int weight;
    private int high;
    private int topSpeed;

    public  Animal (String name, int weight, int high, int topSpeed){
        this.name=name;
        this.weight=weight;
        this.high=high;
        this.topSpeed=topSpeed;
    }
    public String toString (){
        return name+" "+weight+" "+high+" "+topSpeed;
    }

}
