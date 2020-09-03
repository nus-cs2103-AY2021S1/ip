import java.io.IOException;

import duke.Duke;
import duke.DukeException;
import duke.GraphicalUi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            try {
                duke = new Duke("data/duke.txt", new GraphicalUi());
                fxmlLoader.<MainWindow>getController().setDuke(duke);
                fxmlLoader.<MainWindow>getController().greet();
            } catch (DukeException e) {
                fxmlLoader.<MainWindow>getController().showLoadError(e.getMessage());
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
