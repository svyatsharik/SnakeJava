import java.awt.*;
import java.util.ArrayList;

public class Snake {
    public static enum Directions {
        UP,
        RIGHT,
        DOWN,
        LEFT,
        STAY
    }
    public ArrayList<Pair> list = new ArrayList<>();
    private int size;
    private Directions direction = Directions.STAY;
    private final int INTERVAL = 3;
    private char lastRequest = 'R';
    private Eyes eye1, eye2;

    public Snake(int cordX, int cordY, int size) {
        list.add(new Pair(cordX, cordY));
        this.size = size;
        eye1 = new Eyes(list.get(0).cordX + 9, list.get(0).cordY + 9);
        eye2 = new Eyes(list.get(0).cordX + 22, list.get(0).cordY + 9);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(list.get(0).cordX, list.get(0).cordY, size, size);
        if (list.size() >= 2) g.fillOval(list.get(1).cordX, list.get(1).cordY, size, size);
        g.setColor(Color.BLACK);
        g.fillOval(eye1.cordX, eye1.cordY, 4,4);
        g.fillOval(eye2.cordX, eye2.cordY, 4,4);
        g.setColor(Color.GREEN);
        for (int i = 2; i < list.size(); i++) g.fillOval(list.get(i).cordX, list.get(i).cordY, size, size);
    }

    Snake move() {
        switch (direction) {
            case UP:
                list.get(0).cordY -= INTERVAL;
                eye1.cordX = list.get(0).cordX + 9;
                eye1.cordY = list.get(0).cordY + 9;
                eye2.cordX = list.get(0).cordX + 22;
                eye2.cordY = list.get(0).cordY + 9;
                lastRequest = 'U';
                break;
            case RIGHT:
                list.get(0).cordX += INTERVAL;
                eye1.cordX = list.get(0).cordX + 22;
                eye1.cordY = list.get(0).cordY + 9;
                eye2.cordX = list.get(0).cordX + 22;
                eye2.cordY = list.get(0).cordY + 22;
                lastRequest = 'R';
                break;
            case DOWN:
                list.get(0).cordY += INTERVAL;
                eye1.cordX = list.get(0).cordX + 9;
                eye1.cordY = list.get(0).cordY + 22;
                eye2.cordX = list.get(0).cordX + 22;
                eye2.cordY = list.get(0).cordY + 22;
                lastRequest = 'D';
                break;
            case LEFT:
                list.get(0).cordX -= INTERVAL;
                eye1.cordX = list.get(0).cordX + 9;
                eye1.cordY = list.get(0).cordY + 9;
                eye2.cordX = list.get(0).cordX + 9;
                eye2.cordY = list.get(0).cordY + 22;
                lastRequest = 'L';
                break;
        }
        for (int i = 1; i < list.size(); i++) {
            if (lastRequest == 'U' && list.get(i).cordX == list.get(i - 1).cordX) {
                list.get(i).cordY -= INTERVAL;
                lastRequest = 'U';
            }
            else if (lastRequest == 'U' && list.get(i).cordX < list.get(i - 1).cordX){
                list.get(i).cordX = Math.min(list.get(i).cordX + INTERVAL, list.get(i - 1).cordX);
                lastRequest = 'R';
            }
            else if (lastRequest == 'U' && list.get(i).cordX > list.get(i - 1).cordX) {
                list.get(i).cordX = Math.max(list.get(i).cordX - INTERVAL, list.get(i - 1).cordX);
                lastRequest = 'L';
            }

            else if (lastRequest == 'D' && list.get(i).cordX == list.get(i - 1).cordX){
                list.get(i).cordY += INTERVAL;
                lastRequest = 'D';
            }
            else if (lastRequest == 'D' && list.get(i).cordX < list.get(i - 1).cordX){
                list.get(i).cordX = Math.min(list.get(i).cordX + INTERVAL, list.get(i - 1).cordX);
                lastRequest = 'R';
            }
            else if (lastRequest == 'D' && list.get(i).cordX > list.get(i - 1).cordX) {
                list.get(i).cordX = Math.max(list.get(i).cordX - INTERVAL, list.get(i - 1).cordX);
                lastRequest = 'L';
            }

            else if (lastRequest == 'R' && list.get(i).cordY == list.get(i - 1).cordY){
                list.get(i).cordX += INTERVAL;
                lastRequest = 'R';
            }
            else if (lastRequest == 'R' && list.get(i).cordY < list.get(i - 1).cordY){
                list.get(i).cordY = Math.min(list.get(i).cordY + INTERVAL, list.get(i - 1).cordY);
                lastRequest = 'D';
            }
            else if (lastRequest == 'R' && list.get(i).cordY > list.get(i - 1).cordY){
                list.get(i).cordY = Math.max(list.get(i).cordY - INTERVAL, list.get(i - 1).cordY);
                lastRequest = 'U';
            }

            else if (lastRequest == 'L' && list.get(i).cordY == list.get(i - 1).cordY){
                list.get(i).cordX -= INTERVAL;
                lastRequest = 'L';
            }
            else if (lastRequest == 'L' && list.get(i).cordY < list.get(i - 1).cordY){
                list.get(i).cordY = Math.min(list.get(i).cordY + INTERVAL, list.get(i - 1).cordY);
                lastRequest = 'D';
            }
            else if (lastRequest == 'L' && list.get(i).cordY > list.get(i - 1).cordY){
                list.get(i).cordY = Math.max(list.get(i).cordY - INTERVAL, list.get(i - 1).cordY);
                lastRequest = 'U';
            }
        }
        return this;
    }

    Snake changeDirection(Directions direction) {
        this.direction = direction;
        return this;
    }

    public boolean snakeInScreen() {
        return !(this.getCordX() <= -5 || this.getCordY()<= -5 || this.getCordX() + 25 >= 1525 || this.getCordY() + 25 >= 780);
    }

    public int getCordX() {
        return list.get(0).cordX;
    }

    public int getCordY() {
        return list.get(0).cordY;
    }
}
