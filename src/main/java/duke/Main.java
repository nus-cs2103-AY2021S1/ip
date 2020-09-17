package duke;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class to bypass JavaFX runtime components are missing error
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   8/9/2020
 */
public class Main extends Application {

    private Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.png"));
    private Stage stage;

    public void start(Stage stage) {
        try {
            this.stage = stage;
            Duke duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.getIcons().add(icon);
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.show();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }

    public static void closeDuke() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            Platform.exit();
        }).start();
    }
}