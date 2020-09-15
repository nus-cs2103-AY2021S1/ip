package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.view.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            String home = System.getProperty("user.dir");
            Path path = Paths.get(home, "src", "main", "resources", "view", "MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(path.toUri().toURL());

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}