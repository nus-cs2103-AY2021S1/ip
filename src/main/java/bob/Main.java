package bob;

import java.io.IOException;

import bob.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main environment where the application runs.
 */
public class Main extends Application {

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bob");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().initializeBob();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
