package butler;

import java.io.IOException;

import butler.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Butler using FXML.
 */
public class Main extends Application {

    private static final String FILEPATH = "data/tasks.txt";
    private final Butler butler = new Butler(FILEPATH);
    /**
     * Starts the application GUI.
     *
     * @param stage Stage for this application, where the scene is set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setButler(butler);
            stage.setTitle("Butler");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
