import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException {
        setBounds(200, 200, 800, 800);
        add(new GameField());
        setVisible(true);
    }
}
