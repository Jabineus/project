package pixelcraft.project;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements Subject {
     private List<Observer> observers;
     private Image originalImage;
     private Image modifiedImage;

    public ImageModel() {
        this.observers = new ArrayList<>();

    }
    
    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Image getOriginalImage() {
        return originalImage;
    }

    public Image getModifiedImage() {
        return modifiedImage;
    }

    public void loadImage(Image image) {
        this.originalImage = image;
        this.modifiedImage = image;
        notifyObservers();
    }

    public void saveImage(Image image, String filePath) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        WritableImage writableImage = new WritableImage(width, height);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, image.getPixelReader().getArgb(x, y));
            }
        }
        try {
            ImageIO.write(bufferedImage, "png", new File(filePath));
            System.out.println("Image saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }

    public void applyConverter(Converter converter) {
        if (originalImage != null) {
            try {
                this.modifiedImage = converter.convert(originalImage);
                notifyObservers();
            }catch (Exception e) {
            e.printStackTrace();
        }
            
        }
    }
}

