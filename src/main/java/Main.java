import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Sets up a GUI for Duke using FXML.
 */
public class Main extends Application {
    /** The Duke object to be set up. */
    private final Duke duke = new Duke(Storage.getFilePath());

    /**
     * Configures and displays the GUI for Duke.
     *
     * @param stage JavaFX Stage object.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Lana del Taco");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
