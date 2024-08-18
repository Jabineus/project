package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Blur extends Converter {
    @Override
    protected Image alterImage(Image image, int width, int height) throws IOException {
        WritableImage blurredImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = blurredImage.getPixelWriter();
        //Iterates through each pixel and calculates the average color of the surrounding pixels to set the new color
        int surrounding = 4; // Sets the radius of surrounding pixels taken into account and the blur strength
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int[] rgba = new int[]{0, 0, 0, 0};//Stores red, green, blue and alpha values respectively
                int total = 0;
                for (int y = -surrounding; y <= surrounding; y++) { //Iterates through surrounding pixels
                    for (int x = -surrounding; x <= surrounding; x++) {
                        int px = w + x;
                        int py = h + y;
                        if (px >= 0 && px < width && py >= 0 && py < height) {
                            ARGB pixel = new ARGB(reader.getArgb(px, py));
                            rgba[0] += pixel.red; //Adds all the red values of surrounding pixels
                            rgba[1] += pixel.green;  //Same for green, blue and alpha values
                            rgba[2] += pixel.blue;
                            rgba[3] += pixel.alpha;
                            total++;
                        }
                    }
                }
                if (total > 0) {
                    int red  = (rgba[0] / total); //Averages all the red values of surrounding
                    int green = (rgba[1] / total);//pixels and same for the green, blue and alpha values
                    int blue = (rgba[2] / total);
                    int alpha = (rgba[3] / total);
                    ARGB blurredPixel = new ARGB(alpha, red, green, blue);
                    writer.setArgb(w, h, blurredPixel.toInt()); //sets blurred pixel color
                }
            }
        }
        return blurredImage;
    }
}