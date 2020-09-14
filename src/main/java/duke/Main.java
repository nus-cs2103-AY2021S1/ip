package duke;

import java.io.IOException;

import duke.gui.DukeGuiWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            duke.onStart(true);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/DukeGui.fxml"));
            BorderPane bp = fxmlLoader.load();
            Scene scene = new Scene(bp);
            stage.setScene(scene);
            fxmlLoader.<DukeGuiWindow>getController().setUp(duke, stage);
            stage.setTitle("Duke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
