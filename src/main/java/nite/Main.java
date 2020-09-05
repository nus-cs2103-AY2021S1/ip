package nite;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nite.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final String mainWindowView = "/view/MainWindow.fxml";
    private final String iconImage = "/images/blackcat_circle.png";
    private final String stageTitle = "NITE";

    public Main() {

    }

    @Override
    public void start(Stage stage) {
        Nite nite = new Nite();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(mainWindowView));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should not be null.";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNite(nite);
            stage.setTitle(stageTitle);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream(iconImage)));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
