package duke;

import java.io.IOException;

import duke.exception.DukeException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke;

    /**
     * Creates a new Duke instance and loads the save file.
     *
     * @throws IOException If an IO error occurs while loading the saved data into Storage.
     * @throws DukeException If an error occurs while executing the loaded data.
     */
    public Main() throws IOException, DukeException {
        duke = new Duke();
        duke.loadSave();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setExitFunction(stage::close);
            stage.show();
            stage.getIcons().add(new Image(getClass().getResource("/images/Icon.png").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
