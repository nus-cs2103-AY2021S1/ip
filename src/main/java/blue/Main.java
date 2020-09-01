package blue;

import java.io.IOException;

import blue.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Blue using FXML.
 */
public class Main extends Application {
    /**
     * The Blue.
     */
    private final Blue blue = new Blue();

    /**
     * Start.
     *
     * @param stage the stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(blue);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
