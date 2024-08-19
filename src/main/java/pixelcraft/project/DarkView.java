package pixelcraft.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.geometry.Insets;


public class DarkView implements Observer {
    private Stage stage;
    private ImageModel model;

    private Button applyEffectButton;
    private Button saveButton;
    private Button loadImageButton;
    private Button lightThemeButton;
    private ComboBox<String> converterOptions;
    private ImageView originalImageView;
    private ImageView modifiedImageView;
    private HBox imageSpace;
    private BorderPane pane;
    private Label title;

    public DarkView(Stage stage, ImageModel model) {
        this.stage = stage;
        this.model = model;
        createUI();
    }

    private void createUI() {
        pane = new BorderPane();
        pane.setBackground(new Background(new BackgroundFill(Color.web("#363535"), null, null)));
        // Set the title
        title = new Label("PixelCraft Image Editor");
        title.setFont(new Font("Comic Sans MS", 36));
        title.setStyle("-fx-text-fill: #ffffff;");
        title.setPadding(new Insets(15));

        //Set buttons
        applyEffectButton = new Button("Apply Effect");
        saveButton = new Button("Save Image");
        loadImageButton = new Button("Upload Image");
        lightThemeButton = new Button("Light Theme");

        //Set dropdown menu to select converters and label
        converterOptions = new ComboBox<>();
        converterOptions.getItems().addAll("Grayscale", "Rotate", "Blur", "Vertical Flip", "Pop Art", "Swirl", "Circle Crop", "Frame", "Pixel", "Diagonal Flip");
        Label label = new Label("Select an Effect");
        label.setStyle("-fx-text-fill: #ffffff;");
        //Create image views
        originalImageView = new ImageView();
        modifiedImageView = new ImageView();
        originalImageView.setPreserveRatio(true);
        modifiedImageView.setPreserveRatio(true);
        //Set image width and height to fit inside pane
        originalImageView.setFitWidth(pane.getWidth() / 2 - 100);
        originalImageView.setFitHeight(pane.getHeight());
        modifiedImageView.setFitWidth(pane.getWidth() / 2 - 100);
        modifiedImageView.setFitHeight(pane.getHeight());
        //Add event listeners to change image width and height if the window gets resized
        pane.widthProperty().addListener((obs, oldVal, newVal) -> {
            originalImageView.setFitWidth(newVal.doubleValue() / 2 - 100);
            modifiedImageView.setFitWidth(newVal.doubleValue() / 2 - 100);
        });

        pane.heightProperty().addListener((obs, oldVal, newVal) -> {
            originalImageView.setFitHeight(newVal.doubleValue());
            modifiedImageView.setFitHeight(newVal.doubleValue());
        });

        //Set HBox to display original and modified image views side by side
        imageSpace = new HBox();
        imageSpace.getChildren().addAll(originalImageView, modifiedImageView);
        imageSpace.setSpacing(15);
        imageSpace.setPadding(new Insets(10));

        //Set VBox for the side menu
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(25));
        menu.setBackground(new Background(new BackgroundFill(Color.web("#000000"), null, null)));
        menu.getChildren().add(loadImageButton);
        menu.getChildren().add(label);
        menu.getChildren().add(converterOptions);
        menu.getChildren().add(applyEffectButton);
        menu.getChildren().add(saveButton);
        menu.getChildren().add(lightThemeButton);

        pane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setLeft(menu);
        pane.setCenter(imageSpace);

        Scene scene = new Scene(pane, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("PixelCraftGUI");
        stage.show();

    }
    public Button getApplyEffectButton() {
        return applyEffectButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
    public Button getLoadImageButton() {return loadImageButton;}
    public Button getChangeThemeButton() {return lightThemeButton;}
    public BorderPane getBorderpane() {return pane;}
    public Stage getStage() {return stage;}

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
        originalImageView.setImage(model.getOriginalImage());
        modifiedImageView.setImage(model.getModifiedImage());

    }
}

