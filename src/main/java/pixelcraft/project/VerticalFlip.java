package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerticalFlip extends Converter {
    @Override
    protected Image alterImage(Image image, int width, int height) throws IOException{
        WritableImage verticallyFlippedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = verticallyFlippedImage.getPixelWriter();
        flipVertical(reader, writer, 0, height-1, width);
        return verticallyFlippedImage;
    }
    //Vertically flips image through recursion
    public void flipVertical(PixelReader reader, PixelWriter writer, int top, int bottom, int width) {
        if (top >= bottom) { //Base case: stopping swapping the rows when the top and bottom ones meet
            return;
        }
        for (int x = 0; x < width; x++) {
            // Get the top and bottom row pixels
            ARGB topPixel = new ARGB(reader.getArgb(x, top));
            ARGB bottomPixel = new ARGB(reader.getArgb(x, bottom));

            // Switch the pixels
            writer.setArgb(x, top, bottomPixel.toInt());
            writer.setArgb(x, bottom, topPixel.toInt());
        }
        flipVertical(reader, writer, top + 1, bottom - 1, width);
    }
}