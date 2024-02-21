import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameField extends JPanel implements KeyListener, ActionListener {
    private Timer timer;
    private Snake snake;
    private Point point;
    private int lastKeyCode = 0;

    public GameField() {

        snake = new Snake(100, 100, 35);
        point = new Point();
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        point.draw(g);
        snake.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                if (lastKeyCode != 39) {
                    snake.changeDirection(Snake.Directions.LEFT);
                    lastKeyCode = 37;
                } break;
            case 38:
                if (lastKeyCode != 40) {
                    snake.changeDirection(Snake.Directions.UP);
                    lastKeyCode = 38;
                } break;
            case 39:
                if (lastKeyCode != 37) {
                    snake.changeDirection(Snake.Directions.RIGHT);
                    lastKeyCode = 39;
                } break;
            case 40:
                if (lastKeyCode != 38) {
                    snake.changeDirection(Snake.Directions.DOWN);
                    lastKeyCode = 40;
                } break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        repaint();
        if (!snake.snakeInScreen()) System.exit(0);
        updatePoint();
        for (Pair elem : snake.list) {
            if (Math.pow(elem.cordX - snake.list.get(0).cordX, 2) + Math.pow(elem.cordY - snake.list.get(0).cordY, 2) < 60 && elem.cordY != snake.list.get(0).cordY && elem.cordX != snake.list.get(0).cordX) {
                System.exit(0);
            }
        }
    }

    void updatePoint() {
        if (Math.pow(snake.getCordX() - point.getCordX() + 5, 2) +  Math.pow(snake.getCordY() - point.getCordY() + 5, 2) <= 500) {
            point.newCords();
            if (lastKeyCode == 37) {
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX + 15, snake.list.get(snake.list.size() - 1).cordY));
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX + 15, snake.list.get(snake.list.size() - 1).cordY));
            }
            if (lastKeyCode == 38) {
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX, snake.list.get(snake.list.size() - 1).cordY + 15));
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX, snake.list.get(snake.list.size() - 1).cordY + 15));
            }
            if (lastKeyCode == 39) {
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX - 15, snake.list.get(snake.list.size() - 1).cordY));
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX - 15, snake.list.get(snake.list.size() - 1).cordY));
            }
            if (lastKeyCode == 40) {
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX, snake.list.get(snake.list.size() - 1).cordY - 15));
                snake.list.add(new Pair(snake.list.get(snake.list.size() - 1).cordX, snake.list.get(snake.list.size() - 1).cordY - 15));
            }
        }
    }
}
