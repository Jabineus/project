package pixelcraft.project;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Frame extends Converter {
    @Override
    //Puts a brown square/rectanqular frame around the image
    protected Image alterImage(Image image, int width, int height) throws IOException{
        int frameThickness = 8;
        width = width + 2 * frameThickness;
        height = height + 2 * frameThickness;
        WritableImage framedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = framedImage.getPixelWriter();
        ARGB frameColor = new ARGB(255,140, 65, 20);
        //Itertaes through each pixel and if it falls within the frame area then it is colored brown
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < frameThickness || x >= width - frameThickness || y < frameThickness || y >= height - frameThickness) {
                    writer.setArgb(x, y, frameColor.toInt());
                } else {
                    // Outside the frame area the original image pixels are set to the original colors
                    int originalX = x - frameThickness;
                    int originalY = y - frameThickness;
                    ARGB pixel = new ARGB(reader.getArgb(originalX, originalY));
                    writer.setArgb(x, y, pixel.toInt());
                }
            }
        }
        return framedImage;
    }
}