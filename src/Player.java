import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public class Player {

    private int x, y;
    private int width = 81;
    private int height = 64;

    private int velocityX = 0;
    private int velocityY = 0;

    private int speed = 4;
    private int jumpStrength = -12;
    private int gravity = 1;

    private boolean left, right, jump;
    private boolean onGround;

    //for the anaimations
    private BufferedImage[] rightFrames;
    private BufferedImage[] leftFrames;
    private BufferedImage idleFrame;

    private int currentFrame;
    private int frameCounter;
    private int frameDelay = 8;

    private boolean facingRight = true;

    public Player(int x, int y, BufferedImage[] rightFrames, BufferedImage[] leftFrames, BufferedImage idleFrame) {
        this.x = x;
        this.y = y;

        this.rightFrames = rightFrames;
        this.leftFrames = leftFrames;
        this.idleFrame = idleFrame; //mind you idle is just a static picture
    }

    public void update(Level level) {
        handleMovement();
        applyGravity();
        handleHorizontalCollision(level.getPlatforms());
        handleVerticalCollision(level.getPlatforms());

        backgroundBoundaries();
        updateAnimation();
    }

    private void handleMovement() {
        velocityX = 0;

        if (left) {
            velocityX = -speed;
            facingRight = false;
        }
        if (right) {
            velocityX = speed;
            facingRight = true;
        }

        if (jump && onGround) {
            velocityY = jumpStrength;
            onGround = false;
        }
    }

    private void applyGravity() {
        velocityY += gravity;
    }

    private void handleHorizontalCollision(List<Rectangle> platforms) {
        x += velocityX;

        for (Rectangle platform : platforms) {
            if (getBounds().intersects(platform)) {

                if (velocityX > 0) {
                    x = platform.x - width; // hit right side
                } else if (velocityX < 0) {
                    x = platform.x + platform.width; // hit left side
                }
            }
        }
    }

    private void handleVerticalCollision(List<Rectangle> platforms) {
        y += velocityY;
        onGround = false;

        for (Rectangle platform : platforms) {
            if (getBounds().intersects(platform)) {

                if (velocityY > 0) {
                    // falling → land on platform
                    y = platform.y - height;
                    velocityY = 0;
                    onGround = true;
                } else if (velocityY < 0) {
                    // hitting ceiling
                    y = platform.y + platform.height;
                    velocityY = 0;
                }
            }
        }
    }

    private void backgroundBoundaries() {

        int worldWidth = 1600;
        int worldHeight = 800;

        //left
        if (x < 0) {
            x = 0;
        }

        //right
        if (x + width > worldWidth) {
            x = worldWidth - width;
        }

        //top? idt i need this
        if (y < 0) {
            y = 0;
            velocityY = 0;
        }

        //floor
        if (y + height > worldHeight) {
            y = worldHeight - height;
            velocityY = 0;
            onGround = true;
        }
    }

    private void updateAnimation() {

        if (velocityX != 0) {
            frameCounter++;

            if (frameCounter >= frameDelay) {
                currentFrame = (currentFrame + 1) % rightFrames.length;
                frameCounter = 0;
            }
        } else {
            currentFrame = 0;
        }
    }

    public void draw(Graphics g, int cameraX, int cameraY) {

        BufferedImage frame;

        if (velocityX == 0) {
            frame = idleFrame;
        } else if (facingRight) {
            frame = rightFrames[currentFrame];
        } else {
            frame = leftFrames[currentFrame];
        }

        g.drawImage(frame, x - cameraX, y - cameraY, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void resetPosition(int x, int y) {
        this.x = x;
        this.y = y;
        velocityX = 0;
        velocityY = 0;
    }

    public void setLeft(boolean left) {

        this.left = left;
    }

    public void setRight(boolean right) {

        this.right = right;
    }

    public void setJump(boolean jump) {

        this.jump = jump;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}