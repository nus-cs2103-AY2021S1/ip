package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /** Driver of application. */
    private Duke duke = new Duke("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/view/stylesheet.css");
            stage.setScene(scene);
            stage.setTitle("Pok\u00E9mon Mystery Dungeon");
            fxmlLoader.<MainWindow>getController().setDukeAndDisplayWelcome(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
