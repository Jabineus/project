package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rotate extends Converter {
    @Override
    protected Image alterImage(Image image, int width, int height) throws IOException {
        WritableImage rotatedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = rotatedImage.getPixelWriter();
        // Iterates through each pixel and sets the new position to be 90 degrees clockwise from original position
        int x = 0; int y = 0;
        while (x < width){
            y = 0;
            while (y < height){
                //RGB value is set for the new pixel position on rotatedImage from the original coordinate color
                ARGB pixel = new ARGB(reader.getArgb(x, y));
                writer.setArgb(height - 1 - y, x, pixel.toInt());
                y += 1;
            }
            x += 1;
        }
        return rotatedImage;
    }
}