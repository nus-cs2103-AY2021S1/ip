package alfred;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Alfred using FXML.
 */
public class Main extends Application {

    private Alfred alfred = new Alfred();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Alfred");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAlfred(alfred);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
