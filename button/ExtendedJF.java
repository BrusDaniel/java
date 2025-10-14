import javax.swing.*;
import java.awt.*;

public class ExtendedJF extends JFrame {

    private int x;
    private int y;
    private int r;

    public ExtendedJF (int x, int y, int r){
        this.x=x;
        this.y=y;
        this.r=r;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        g.setColor(new Color (255, 1, 100));
        g.drawOval(x, y, r, r);
        int height = this.getHeight();
        System.out.println(height);
        int width = this.getWidth();
        System.out.println(width);
    }
}
