package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String RESOURCE_PATH = "/view/MainWindow.fxml";
    private static final String STYLESHEETS_PATH = "/styles/stylesheets.css";

    private Duke duke = new Duke();

    /**
     * Overrides start method, starts the GUI.
     *
     * @param stage {@link Stage} to be displayed.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(RESOURCE_PATH));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(STYLESHEETS_PATH);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setTitle("Duke");

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setDuke(duke);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
