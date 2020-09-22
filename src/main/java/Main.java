import java.io.IOException;

import com.sun.prism.Image;
import duke.Duke;
import duke.logic.Storage;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();
    private TaskList tasks = new TaskList();
    private Storage storage = new Storage();
    private boolean isFinished = false;

    @Override
    public void start(Stage stage) {
        try {
            javafx.scene.image.Image dukeImage = new javafx.scene.image.Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.getIcons().add(dukeImage);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
