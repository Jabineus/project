package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class PopArt extends Converter {
    @Override
    //Gives the image a pop art effect
    protected Image alterImage(Image image, int width, int height) throws IOException{
        WritableImage poppedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = poppedImage.getPixelWriter();
        //Iterates through each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ARGB pixel = new ARGB(reader.getArgb(x, y));

                int red = popColor(pixel.red); //Calculates each component for rgb
                int green = popColor(pixel.green);
                int blue = popColor(pixel.blue);
                ARGB newPixel = new ARGB(pixel.alpha, red, green, blue);
                writer.setArgb(x, y, newPixel.toInt());
            }
        }
        return poppedImage;
    }
    //If a value is below or above a certain limit it will return a different value to alter the color
    //This limits the variety in colors to give it that pop art effect
    public static int popColor(int color) {
        if (color < 90) {
            return 0;
        } else if (color < 180) {
            return 130;
        } else {
            return 255;
        }
    }
}