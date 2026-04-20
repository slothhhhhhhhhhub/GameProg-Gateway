import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private boolean isRunning;

    private Player player;
    private Level level;
   // private SoundManager soundManager;

    public GamePanel() {
        setFocusable(true);
        //soundManager = SoundManager.getInstance();

        level = new Level(1);
        player = level.getPlayer();

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT  -> player.setLeft(true);
                    case KeyEvent.VK_RIGHT -> player.setRight(true);
                    case KeyEvent.VK_SPACE -> player.setJump(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT  -> player.setLeft(false);
                    case KeyEvent.VK_RIGHT -> player.setRight(false);
                    case KeyEvent.VK_SPACE -> player.setJump(false);
                }
            }

        });
    }

    public void startGame() {
        gameThread = new Thread(this);
        isRunning = true;
        gameThread.start();
        //soundManager.playClip("background", true);
    }

    @Override
    public void run() {
        while (isRunning) {

            gameUpdate();
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void gameUpdate() {
        player.update(level);
        checkDiamondCollision();
        level.update(player);
        checkWinCondition();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        level.draw(g);
        player.draw(g, level.getCameraX());

    }

    private void checkDiamondCollision() {

        for (Diamond d : level.getDiamonds()) {
            if (!d.isCollected() && player.getBounds().intersects(d.getBounds())) {
                d.collect();
            }
        }
    }

    private void checkWinCondition() {

        if (level.getGateway().isPlayerEntering(player)) {
            //SoundManager.playSound("level_complete.wav");

            if (level.getLevelNumber() == 1) {
                level = new Level(2); // load next level
                player.resetPosition(50, 250);
            }
            else if (level.getLevelNumber() == 2) {
                isRunning = false; // end game
            }
        }
    }
}



