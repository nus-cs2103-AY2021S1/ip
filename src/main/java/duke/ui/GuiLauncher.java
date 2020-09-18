package duke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A class which launches the GUI for Duke.
 */
public class GuiLauncher {
    public static class App extends Application {
        @Override
        public void start(Stage stage) throws Exception {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(GuiLauncher.class.getResource("/view/MainWindow.fxml"));
                AnchorPane ap = fxmlLoader.load();
                Scene scene = new Scene(ap);
                stage.setScene(scene);
                stage.setTitle("Duke");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Launches the JavaFX application.
     */
    public GuiLauncher() {
        Application.launch(App.class);
    }
}
