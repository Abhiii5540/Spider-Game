import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException {
        Board board = new Board();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Spider Game");
        setSize(1500,920);
        setResizable(false);
        setLocationRelativeTo(null);// to place it in center
        add(board);
        setVisible(true);
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
