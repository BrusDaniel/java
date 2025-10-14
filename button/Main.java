import java.awt.*;
import java.util.Scanner;
import java.util.Timer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        int r = scan.nextInt();
        ExtendedJF circle = new ExtendedJF(x,y,r);
        circle.setVisible(true);
    }
}