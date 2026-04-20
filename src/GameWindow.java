import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {

        setTitle("Gateway");
        setSize(400, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);

        setVisible(true);
        panel.startGame();
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
