package clippy.ui;

import java.io.IOException;

import clippy.Clippy;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

/**
 * A GUI for Clippy using FXML.
 */
public class Main extends Application {

    private Clippy clippy = new Clippy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setClippy(clippy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
