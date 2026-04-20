import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Diamond {

    private int x, y;
    private int width = 44;
    private int height = 66;

    private boolean collected;

    //animation code
    private BufferedImage[] frames;
    private int currentFrame;
    private int frameCounter;
    private int frameDelay = 5;

    public Diamond(int x, int y, BufferedImage[] frames) {
        this.x = x;
        this.y = y;
        this.frames = frames;

        this.currentFrame = 0;
        this.frameCounter = 0;
        this.collected = false;
    }

    public void update() {
        //update the animation
        if (!collected) {
            frameCounter++;

            if (frameCounter >= frameDelay) {
                currentFrame = (currentFrame + 1) % frames.length;
                frameCounter = 0;
            }
        }
    }

    public void draw(Graphics g, int cameraX) {
        if (!collected) {
            g.drawImage(frames[currentFrame], x - cameraX, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void collect() {
        if (!collected) {
            collected = true;
            //SoundManager.playSound("diamond.wav");
        }
    }

    public boolean isCollected() {
        return collected;
    }


}