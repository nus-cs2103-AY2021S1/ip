import fei.Fei;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Fei using FXML.
 */
public class Main extends Application {

    private Fei fei = new Fei("./tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fei");
            fxmlLoader.<MainWindow>getController().setFei(fei);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
