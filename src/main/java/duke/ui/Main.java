package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String PATH_TO_MAIN_WINDOW = "/view/MainWindow.fxml";
    private static final String PATH_TO_MAIN_WINDOW_STYLES = "/view/MainWindow.css";
    private static final String NAME = "Duke task manager";

    private final Duke duke = new Duke();

    /**
     * Starts the GUI application.
     *
     * @param stage Stage parameter.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(PATH_TO_MAIN_WINDOW));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(PATH_TO_MAIN_WINDOW_STYLES);
            stage.setTitle(NAME);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
