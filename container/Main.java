import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Main {
    public static void main (String[]args) {
        Kontener<Integer, Float> intValue = new Kontener<>(561, 4.f);
        Kontener<String, Boolean> stringValue = new Kontener<>("qjq", true);

        intValue.wypisz();
        stringValue.wypisz();


        ArrayList<String> alaMaKota = new ArrayList<>();
        alaMaKota.add("Ala");
        alaMaKota.add("ma");
        alaMaKota.add("Kota");
        System.out.println(alaMaKota.toArray().length);
        for (String val : alaMaKota) {
            System.out.print(val + " ");
        }
        System.out.println();


        HashSet<Integer> collection = new HashSet<>(45);
        System.out.println(collection.contains(44));
        collection.add(45);
        collection.add(45);
        collection.add(46);
        collection.add(47);
        System.out.println(collection.toArray().length);

        Map<Integer, String> calendr = new HashMap<>();

        calendr.put(31, "styczen");
        calendr.put(29, "luty");
        System.out.println(calendr);


        Osoba kowalski = new Osoba("Jan", "Kowalski");
        Osoba nowak = new Osoba("Adam", "Nowak");
        Osoba krawczyk = new Osoba("Bartosz", "Krawczyk");
        Osoba heniek = new Osoba("Kierownik", "Heniek");

        Samochod skoda1 = new Samochod("WA00001", "Skoda");
        Samochod skoda2 = new Samochod("SC36010", "Skoda");
        Samochod mazda1 = new Samochod("WA01234", "Mazda");
        Samochod mazda2 = new Samochod("DW01ASD", "Mazda");
        Samochod bmw = new Samochod("WA12690", "BMW");
        Samochod volvo = new Samochod("KR60606", "Volvo");
    }
}
