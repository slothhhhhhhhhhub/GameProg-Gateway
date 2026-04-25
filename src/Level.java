import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public class Level {

    private List<Rectangle> platforms;
    private List<Rectangle> hazards;
    private List<Diamond> diamonds;
    private Gateway gateway;
    private Player player;

    private int cameraX;
    private int cameraY; //in order to move up and down
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 500;

    private final int LARGE_PLATFORM_WIDTH = 114;
    private final int LARGE_PLATFORM_HEIGHT = 34;
    private final int SMALL_PLATFORM_WIDTH = 64;
    private final int SMALL_PLATFORM_HEIGHT = 34;
    //platforms and hazards are the same size so i'll just use these for that as well

    private BufferedImage[] diamondFrames = ImageManager.loadDiamondFrames();
    private BufferedImage[] gatewayFrames = ImageManager.loadGatewayFrames();
    private Image background;

    private int levelNumber;

    public Level(int levelNumber){
        platforms = new ArrayList<>();
        hazards = new ArrayList<>();
        diamonds = new ArrayList<>();
        this.levelNumber = levelNumber;

        loadLevel(levelNumber);
        cameraX = 1600 - SCREEN_WIDTH;
        cameraY = 800 - SCREEN_HEIGHT;
    }

    private void loadLevel(int levelNumber) {
        if (levelNumber == 1) {
            background = ImageManager.loadImage("Level1Background.png");

            //loading the right player for the level
            player = new Player(15, 691, ImageManager.loadYellowPlayerRightFrames(), ImageManager.loadYellowPlayerLeftFrames(), ImageManager.loadYellowPlayerIdle());

            //floor
            platforms.add(new Rectangle(0, 761, 1600, 34));

            //adding large, then small platforms
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));

            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));

            //adding hazards
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));

            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));

            //adding diamonds, these need to be drawn in
            diamonds.add(new Diamond(1, 1, diamondFrames));


            //adding the gateway
            gateway = new Gateway(989, 655, gatewayFrames);

        }
        else if (levelNumber == 2) {
            background = ImageManager.loadImage("Level2Background.png");

            player = new Player(15, 691, ImageManager.loadBluePlayerRightFrames(), ImageManager.loadBluePlayerLeftFrames(), ImageManager.loadBluePlayerIdle());

            //floor
            platforms.add(new Rectangle(0, 761, 1600, 34));

            //adding large, then small platforms
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));

            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));

            //adding hazards
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, LARGE_PLATFORM_WIDTH, LARGE_PLATFORM_HEIGHT));

            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));
            platforms.add(new Rectangle(1, 1, SMALL_PLATFORM_WIDTH, SMALL_PLATFORM_HEIGHT));

            //adding diamonds, these need to be drawn in
            diamonds.add(new Diamond(1, 1, diamondFrames));


            //adding the gateway
            gateway = new Gateway(989, 655, gatewayFrames);

        }
    }

    //camera stuff
    public void update(Player player) {
        // camera follows player
        cameraX = player.getX() - SCREEN_WIDTH / 2;
        cameraY = player.getY() - SCREEN_HEIGHT / 2;

        if (cameraX < 0){
            cameraX = 0;
        }

        int maxCameraX = 1600 - SCREEN_WIDTH;
        if (cameraX > maxCameraX) {
            cameraX = maxCameraX;
        }

        if (cameraY < 0) {
            cameraY = 0;
        }

        int maxY = 1600 - SCREEN_HEIGHT;
        if (cameraY > maxY) {
            cameraY = maxY;
        }
        for (Diamond d : diamonds) {
            d.update();
        }

        boolean allCollected = true;

        for (Diamond d : diamonds) {
            if (!d.isCollected()) {
                allCollected = false;
                break;
            }
        }

        if (allCollected) {
            gateway.activate();
        }

        gateway.update();
    }

    public void draw(Graphics g) {
        g.drawImage(background, -cameraX, -cameraY, null);


        for (Diamond d : diamonds) {
            d.draw(g, cameraX);
        }

        gateway.draw(g, cameraX);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Rectangle> getPlatforms() {
        return platforms;
    }

    public List<Rectangle> getHazards() {
        return hazards;
    }

    public List<Diamond> getDiamonds() {
        return diamonds;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}