module pixelcraft.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens pixelcraft.project to javafx.fxml;
    exports pixelcraft.project;
}