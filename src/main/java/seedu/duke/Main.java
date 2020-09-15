package seedu.duke;
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

    private Duke duke;

    /**
     * Starts Duke GUI.
     * @param stage Stage of Duke GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            duke = new Duke();
            assert (duke != null);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Gui>getController().setDuke(duke);
            fxmlLoader.<Gui>getController().setWelcomeWords();
            stage.setTitle("Duke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
