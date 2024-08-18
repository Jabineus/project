package pixelcraft.project;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Converter {
    protected abstract Image alterImage(Image image, int width, int height) throws IOException;
    // Convert method to read image, convert it and write new image
    public Image convert(Image image) throws IOException {
        return alterImage(image, (int) image.getWidth(), (int) image.getHeight());
    }
}


