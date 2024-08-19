package pixelcraft.project;
import javafx.scene.image.Image;
import java.io.IOException;

public abstract class Converter {
    protected abstract Image alterImage(Image image, int width, int height) throws IOException;
    // Convert method to read image, convert it and write new image based on converter
    public Image convert(Image image) throws IOException {
        return alterImage(image, (int) image.getWidth(), (int) image.getHeight());
    }
}


