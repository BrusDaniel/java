
public class Main12 {
    public static void main(String[] args) {
        System.out.println("Zadanie 1");
        Spawacz tomek = new Spawacz ("Tomek", 5);
        System.out.println(tomek.wyswietl());
        System.out.println("================================");



        System.out.println("Zadanie 2");
        Drzewo dab = new Drzewo(true, 4, "kora, rdzen");
        System.out.println(dab);
        System.out.println("================================");


        System.out.println("Zadanie 3");
        DrzewoIglaste sosna = new DrzewoIglaste(true, 8, "kora, rdzen",
                154984165, 22);
        System.out.println(sosna);
        System.out.println("================================");

        DrzewoLisciaste lipa = new DrzewoLisciaste(false, 5, "kora, rdzen",
                5);
        System.out.println(lipa);
        System.out.println("================================");

        DrzewoOwocowe jablon = new DrzewoOwocowe(false, 5, "kora, rdzen",
                5, "jablko");
        System.out.println(jablon);
        System.out.println("================================");


    }
}