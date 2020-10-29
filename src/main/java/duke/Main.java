package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
            stage.setTitle("Duke");
            // Shooting Stars icon by Icons8 from https://icons8.com/icon/81220/shooting-stars
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
