import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private boolean isRunning;

    private Player player;
    private Level level;
    private SoundManager soundManager;

    private enum GameState {
        START,
        PLAYING,
        GAME_OVER,
        WIN
    }
    private GameState gameState;

    public GamePanel() {
        setFocusable(true);
        soundManager = SoundManager.getInstance();

        level = new Level(1);
        player = level.getPlayer();
        gameState = GameState.START;

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT  -> player.setLeft(true);
                    case KeyEvent.VK_RIGHT -> player.setRight(true);
                    case KeyEvent.VK_SPACE -> player.setJump(true);
                    case KeyEvent.VK_ENTER -> handleEnterPress();
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
        if (gameState == GameState.PLAYING) {
            player.update(level);
            checkDiamondCollision();
            level.update(player);
            checkWinCondition();
            checkHazardCollision();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (gameState) {
            case START -> drawStartScreen(g);

            case PLAYING -> {
                level.draw(g);
                player.draw(g, level.getCameraX(), level.getCameraY());
            }

            case GAME_OVER -> drawGameOverScreen(g);

            case WIN -> drawWinScreen(g);
        }

    }

    private void drawStartScreen(Graphics g) {

        //placeholder till i make my image
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("GATEWAY", 130, 80);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Press ENTER to Start", 110, 120);
    }

    private void drawGameOverScreen(Graphics g) {

        //placeholder
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Game Over", 140, 80);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Press ENTER to Restart", 100, 120);
    }

    private void drawWinScreen(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Thanks for Playing!", 100, 100);
    }

    private void handleEnterPress() {

        if (gameState == GameState.START) {
            level = new Level(1);
            player = level.getPlayer();
            gameState = GameState.PLAYING;
        }

        else if (gameState == GameState.GAME_OVER) {
            level = new Level(1);
            player = level.getPlayer();
            gameState = GameState.PLAYING;
        }
    }

    private void checkHazardCollision() {

        for (Rectangle hazard : level.getHazards()) {
            if (player.getBounds().intersects(hazard)) {
                //SoundManager.playSound("death.wav");
                gameState = GameState.GAME_OVER;
            }
        }
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
                //player.resetPosition(50, 250);
                player = level.getPlayer();
            }
            else if (level.getLevelNumber() == 2) {
                gameState = GameState.WIN;
                isRunning = false; // end game
            }
        }
    }
}



