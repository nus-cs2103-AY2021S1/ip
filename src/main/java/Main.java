import java.io.IOException;

import duke.Duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sponge and Bob");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
