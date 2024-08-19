package pixelcraft.project;

import javafx.application.Application;
import javafx.stage.Stage;

public class PixelCraftGUI extends Application {

    @Override
    public void start(Stage stage) {
        ImageModel model = new ImageModel();
        MainView lightView = new MainView(stage, model);
        model.addObserver(lightView);
        ImageController controller = new ImageController(lightView, model);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
