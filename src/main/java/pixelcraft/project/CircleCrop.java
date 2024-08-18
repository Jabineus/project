package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class CircleCrop extends Converter {
    @Override
    //Crops the image in a circular shape
    protected Image alterImage(Image image, int width, int height) throws IOException {
        WritableImage croppedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = croppedImage.getPixelWriter();
        int middleX = width / 2;
        int middleY = height / 2;
        double radius = (Math.min(width, height))/2.5; //The radius of the image that will be not be cropped
        //Iterates through each pixel and if it outside the radius then it is cropped
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double position = Math.pow((x - middleX) * (x - middleX) + (y - middleY) * (y - middleY), (0.5));
                if (position > radius) {
                    writer.setArgb(x, y, 0); // Pixel is set to transparent (cropped)
                } else {
                    ARGB pixel = new ARGB(reader.getArgb(x, y));
                    writer.setArgb(x, y, pixel.toInt());
                }
            }
        }
        return croppedImage;
    }
}


