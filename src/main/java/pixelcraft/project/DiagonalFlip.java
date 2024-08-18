package pixelcraft.project;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class DiagonalFlip extends Converter {
    @Override
    //Flips the image diagonally recursively
    protected Image alterImage(Image image, int width, int height) throws IOException{
        WritableImage flippedImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = flippedImage.getPixelWriter();
        flipRecursive(reader, writer, 0, Math.min(width, height) - 1, width, height);
        return flippedImage;
    }
    private static PixelWriter flipRecursive(PixelReader reader, PixelWriter writer, int row, int col, int width, int height) {
        if (row >= height || col < 0) {
            return writer; //Base case: return image when all the rows and columns have been swapped
        }
        flipRecursiveHelper(reader, writer, row, col, 0, width, height);
        return flipRecursive(reader, writer, row + 1, col - 1, width, height);
    }
    private static void flipRecursiveHelper(PixelReader reader, PixelWriter writer, int row, int col, int i, int width, int height) {
        if (i >= Math.min(width, height)) {
            return;
        }
        ARGB color = new ARGB(reader.getArgb(i, row)); //Temporarily stores the color for swapping
        ARGB swapRow = new ARGB(reader.getArgb(width - col - 1, height - i -1));
        writer.setArgb(i, row, swapRow.toInt()); //Swaps each row with its corresponding column
        writer.setArgb(width - col - 1, height - i - 1, color.toInt());
        flipRecursiveHelper(reader, writer, row, col, i + 1, width, height);
    }
}
