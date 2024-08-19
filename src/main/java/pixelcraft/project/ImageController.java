package pixelcraft.project;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    private void changeTheme() {
        if (onDarkView) {
            view.getStage().getScene().setRoot(view.getBorderpane());
        } else {
            if (darkView == null) {
                darkView = new DarkView(view.getStage(), model);
                model.addObserver(darkView);
            }
            view.getStage().getScene().setRoot(darkView.getBorderpane());
        }
        onDarkView = !onDarkView;
    }
}

