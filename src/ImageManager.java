import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageManager {
    //blue player
    public static BufferedImage[] loadBluePlayerRightFrames() {
        return new BufferedImage[] {
                loadImage("blue_face.png"),
                loadImage("blue_right1.png"),
                loadImage("blue_right2.png")
        };
    }

    public static BufferedImage[] loadBluePlayerLeftFrames() {
        return new BufferedImage[] {
                loadImage("blue_face.png"),
                loadImage("blue_left1.png"),
                loadImage("blue_left2.png")
        };
    }

    public static BufferedImage loadBluePlayerIdle() {
        return loadImage("blue_face.png");
    }

    //yellow player
    public static BufferedImage[] loadYellowPlayerRightFrames() {
        return new BufferedImage[] {
                loadImage("yellow_face.png"),
                loadImage("yellow_right1.png"),
                loadImage("yellow_right2.png")
        };
    }

    public static BufferedImage[] loadYellowPlayerLeftFrames() {
        return new BufferedImage[] {
                loadImage("yellow_face.png"),
                loadImage("yellow_left1.png"),
                loadImage("yellow_left2.png")
        };
    }

    public static BufferedImage loadYellowPlayerIdle() {
        return loadImage("yellow_face.png");
    }

    //diamonds
    public static BufferedImage[] loadDiamondFrames() {
        return new BufferedImage[] {
                loadImage("diamond1.png"),
                loadImage("diamond2.png"),
                loadImage("diamond3.png")
        };
    }

    //gateway
    public static BufferedImage[] loadGatewayFrames() {
        return new BufferedImage[] {
            loadImage("activated1.png"),
            loadImage("activated2.png"),
            loadImage("activated3.png")
        };
    }

    public static BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Failed to load: " + fileName);
            e.printStackTrace();
            return null;
        }
    }


}