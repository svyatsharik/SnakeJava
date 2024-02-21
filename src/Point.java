import java.awt.*;
import java.util.Random;

public class Point {
    int minValue = 50;
    int maxValue = 700;

    private int cordX = minValue + (int) (Math.random() * (maxValue - minValue + 1));
    private int cordY = minValue + (int) (Math.random() * (maxValue - minValue + 1));
    private int size = 19;

    public void draw(Graphics g) {
        g.setColor(Color.getColor("964b00"));
        g.fillRect(cordX + 9, cordY - 7, 2, 9);
        g.setColor(Color.GREEN);
        g.fillOval(cordX - 5, cordY - 6, 14, 6);
        g.setColor(Color.RED);
        g.fillOval(cordX - 3, cordY, size, size);
        g.fillOval(cordX + 3, cordY, size, size);

    }

    public void newCords() {
        this.cordX = minValue + (int) (Math.random() * (maxValue - minValue + 1));
        this.cordY = minValue + (int) (Math.random() * (maxValue - minValue + 1));
    }

    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }
}
