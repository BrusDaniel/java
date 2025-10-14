import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Wyrażenia lambda dla różnych operacji matematycznych
        ICalc myAdd = (a, b) -> a + b;
        ICalc mySubtract = (a, b) -> a - b;
        ICalc myMultiply = (a, b) -> a * b;
        ICalc myDivide = (a, b) -> a / b;


        // Wywołanie metod oblicz
        double resultAdd = myAdd.oblicz(10, 5);
        double resultSubtract = mySubtract.oblicz(10, 5);
        double resultMultiply = myMultiply.oblicz(10, 5);
        double resultDivide = myDivide.oblicz(10, 5);

        // Wyświetlenie wyników
        System.out.println("Dodawanie: " + resultAdd);
        System.out.println("Odejmowanie: " + resultSubtract);
        System.out.println("Mnożenie: " + resultMultiply);
        System.out.println("Dzielenie: " + resultDivide);

        Animal wilk = new Animal("wilk", 20, 1, 70);
        List<Animal> listaZwiezat = new ArrayList<>();
        listaZwiezat.add(wilk);
        for (Animal val: listaZwiezat){
            System.out.println(val);
        }

    }
}