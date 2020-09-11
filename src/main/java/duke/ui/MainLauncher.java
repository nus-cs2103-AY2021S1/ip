package duke.ui;

import java.io.IOException;

import duke.ui.graphics.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * Main class for GUI
 */
public class MainLauncher extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke Chatbot");
            fxmlLoader.<MainWindow>getController().setup();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
