public class Main {
    public static void main(String[] args) {
        Fruit owoc = new Fruit("Cytryna");
        show(owoc);
    }
    public static void show (Fruit owoc) {
        System.out.println(owoc.name+'\t'+owoc.weight);
    }
}
