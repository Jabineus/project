package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Grayscale extends Converter {
    @Override
    protected Image alterImage(Image image, int width, int height) throws IOException {
        WritableImage grayedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = grayedImage.getPixelWriter();
        //Iterates through each pixel and sets the color to grayscale for the corresponding pixel in grayedIamge
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB pixel = new ARGB(reader.getArgb(x, y));
                // Averages the red, green and blue components of the pixel color to make it gray
                int rgbGray = (int) (pixel.red * 0.3) + (int) (pixel.green * 0.59) + (int) (pixel.blue * 0.11);
                ARGB newPixel = new ARGB(pixel.alpha, rgbGray, rgbGray, rgbGray);
                writer.setArgb(x, y, newPixel.toInt());
            }
        }
        return grayedImage;
    }
}
