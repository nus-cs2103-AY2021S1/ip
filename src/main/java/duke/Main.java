package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The class Duke denotes the faithful robot.
 *
 * @author Alvin Chee
 */
public class Main extends Application {

    private Duke duke = new Duke(getFilePath());

    /**
     * Gets file path based on user's system.
     */
    public static String getFilePath() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Duke", "data", "tasks.text");
        return path.toString();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            duke.referStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


