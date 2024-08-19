package pixelcraft.project;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ImageController {
    private MainView view;
    private DarkView darkView;
    private boolean onDarkView = false;
    private ImageModel model;

    public ImageController(MainView view, ImageModel model) {
        this.view = view;
        this.model = model;
        initController();
    }
    private void initController() {
        // Add event handlers for GUI elements
        view.getApplyEffectButton().setOnAction(e -> applyEffect());
        view.getSaveButton().setOnAction(e -> save());
        view.getLoadImageButton().setOnAction(e -> load());
        view.getChangeThemeButton().setOnAction(e -> changeTheme());
    }
    private void applyEffect() {
        // Logic to apply the selected effect from the model
        // Example: model.applyEffect("grayscale");
        String selectedConverter = view.getSelectedConverter();
        if (selectedConverter != null) {
            model.applyConverter(ConverterFactory.getConverter(selectedConverter));
        }
    }
    private void save(){
        try {
            view.saveImage(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void load(){
        try {
            view.loadImage(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void changeTheme(){
        BorderPane pane = view.getBorderpane();
        Label title = view.getTitle();
        Label label = view.getLabel();
        VBox menu = view.getVBox();
        if(onDarkView){
            pane.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));
            title.setStyle("-fx-text-fill: #551a8b;");
            label.setStyle("-fx-text-fill: #000000;");
            menu.setBackground(new Background(new BackgroundFill(Color.web("#d6b4fc"), null, null)));

        }
        else {
            pane.setBackground(new Background(new BackgroundFill(Color.web("#363535"), null, null)));
            title.setStyle("-fx-text-fill: #d6b4fc;");
            label.setStyle("-fx-text-fill: #ffffff;");
            menu.setBackground(new Background(new BackgroundFill(Color.web("#000000"), null, null)));
        }
        onDarkView = !onDarkView;
    }
    private void switchTheme() {
        if (onDarkView) {
            view.getStage().getScene().setRoot(view.getBorderpane());
            initController();
        } else {
            if (darkView == null) {
                darkView = new DarkView(view.getStage(), model);
                model.addObserver(darkView);
            }
            view.getStage().getScene().setRoot(darkView.getBorderpane());
            initController();
        }
        onDarkView = !onDarkView;
    }
}

