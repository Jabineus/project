package pixelcraft.project;

public class ImageController {
    private MainView view;
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
        // Get the selected effect from the converter factory and apply it in the model
        String selectedConverter = view.getSelectedConverter();
        if (selectedConverter != null) {
            model.applyConverter(ConverterFactory.getConverter(selectedConverter));
        }
    }
    //Prompt view to load the file explorer (filechooser)
    private void save(){
        try {
            view.saveImage(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Prompt view to load the file explorer (filechooser)
    private void load(){
        try {
            view.loadImage(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Toggle between light and dark theme
    private void changeTheme(){
        if(onDarkView){
            view.setPane("#ffffff");
            view.setTitle("#551a8b");
            view.setLabel("#000000");
            view.setVBox("#d6b4fc");
        }
        else {
            view.setPane("#363535");
            view.setTitle("#d6b4fc");
            view.setLabel("#ffffff");
            view.setVBox("#000000");
        }
        onDarkView = !onDarkView;
    }
}

