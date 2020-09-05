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

    private Nite nite = new Nite();

    public Main() {

    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane should not be null.";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNite(nite);
            stage.setTitle("NITE");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/blackcat_circle.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
