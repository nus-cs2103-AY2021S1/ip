package duke;

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

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        try {
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Zoroark");
            stage.show();
            duke = new Duke();
            fxmlLoader.<MainWindow>getController().setDuke(duke);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            fxmlLoader.<MainWindow>getController().getZoroarkMessage(e.getMessage());
        }
    }
}
