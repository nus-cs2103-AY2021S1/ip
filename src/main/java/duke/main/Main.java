package duke.main;

import java.io.IOException;

import duke.ui.controller.MainWindow;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Closes the MainWindow through main.
     *
     * @param stage Stage object.
     */
    public void close(Stage stage) {
        PauseTransition delay = new PauseTransition(Duration.millis(800));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }
}
