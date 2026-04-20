import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Gateway {

    private int x, y;
    private int width = 87;
    private int height = 106;

    private boolean activated;

    //animation time
    private BufferedImage[] frames;
    private int currentFrame;
    private int frameCounter;
    private int frameDelay = 5;

    public Gateway(int x, int y, BufferedImage[] frames) {
        this.x = x;
        this.y = y;
        this.frames = frames;

        this.activated = false;
        this.currentFrame = 0;
        this.frameCounter = 0;
    }

    public void activate() {
        if (!activated) {
            activated = true;
            //SoundManager.playSound("gateway_open.wav");
        }
    }

    public void update() {
        //updating the animation when active
        if (activated) {
            frameCounter++;

            if (frameCounter >= frameDelay) {
                currentFrame = (currentFrame + 1) % frames.length;
                frameCounter = 0;
            }
        }
    }

    public void draw(Graphics g, int cameraX) {
        if (activated) {
            g.drawImage(frames[currentFrame], x - cameraX, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isPlayerEntering(Player player) {
        return activated && player.getBounds().intersects(getBounds());
    }

    public boolean isActivated() {
        return activated;
    }



}