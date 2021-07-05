package luke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import luke.gui.MainWindow;

/**
 * A GUI for Luke using FXML.
 */
public class Main extends Application {

    private final Luke luke = new Luke(System.getProperty("user.dir"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Luke the Skytalker");
            fxmlLoader.<MainWindow>getController().greet(luke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}