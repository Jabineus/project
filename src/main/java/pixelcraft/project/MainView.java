package pixelcraft.project;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;


public class MainView implements Observer{
    private Stage stage;
    private ImageModel model;

    private Button applyEffectButton;
    private Button saveButton;
    private Button loadImageButton;
    private ComboBox<String> converterOptions;
    private ImageView originalImageView;
    private ImageView modifiedImageView;
    private HBox imageSpace;

    public MainView(Stage stage, ImageModel model) {
        this.stage = stage;
        this.model = model;
        createUI();
    }

    private void createUI() {
        BorderPane pane = new BorderPane();
        this.applyEffectButton = new Button("Apply Effect");
        this.saveButton = new Button("Save Image");
        this.loadImageButton = new Button("Upload Image");
        this.converterOptions = new ComboBox<>();
        imageSpace = new HBox();

        originalImageView = new ImageView();
        modifiedImageView = new ImageView();

        VBox layout = new VBox(10);
        layout.getChildren().add(loadImageButton);
        layout.getChildren().add(converterOptions);
        layout.getChildren().add(applyEffectButton);
        layout.getChildren().add(saveButton);

        pane.setLeft(layout);
        pane.setCenter(imageSpace);

        Scene scene = new Scene(pane, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("PixelCraftGUI");
        stage.show();

        converterOptions.getItems().addAll("Grayscale", "Rotate", "Blur", "Vertical Flip", "Pop Art", "Swirl", "Circle Crop", "Frame", "Pixel", "Diagonal Flip");
    
    }
    public Button getApplyEffectButton() {
        return applyEffectButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
    public Button getLoadImageButton() {return loadImageButton;}

    public String getSelectedConverter() {
        return converterOptions.getValue();
    }
    public void loadImage(ImageModel model) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            FileInputStream inputFile = new FileInputStream(file.getPath());
            Image image = new Image(inputFile);
            model.loadImage(image);
        }
    }

    public void saveImage(ImageModel model) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PNG Files", "*.png")
        );
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            model.saveImage(model.getModifiedImage(), file.getPath());
        }
    }
    @Override
    public void update() {
        // Update the original and modified images in the ImageView components
        this.originalImageView = new ImageView(model.getOriginalImage());
        this.modifiedImageView = new ImageView(model.getModifiedImage());
        imageSpace.getChildren().addAll(originalImageView, modifiedImageView);

    }
}

