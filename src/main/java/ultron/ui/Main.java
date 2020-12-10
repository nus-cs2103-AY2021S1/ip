package ultron.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ultron.Ultron;

/**
 * A GUI for Ultron using FXML.
 */
public class Main extends Application {

    private final Ultron ultron = new Ultron();

    @Override
    public void start(Stage stage) {
        try {
            //Load the FXML
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            //Create new scene with FXML
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            //Set the title
            stage.setTitle("Ultron");

            fxmlLoader.<MainWindow>getController().setUltron(ultron);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
