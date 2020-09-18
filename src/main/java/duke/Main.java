package duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    /**
     * Stages the stage.
     * @param stage Stage where the application is shown.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/view/display.css");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            initializeStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeStage(Stage stage) {
        stage.setTitle("Duck");
        stage.getIcons().add(new Image("/images/DaDuke.png"));
        duke.getStage(stage);
    }
}


