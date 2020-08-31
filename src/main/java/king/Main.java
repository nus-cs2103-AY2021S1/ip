/**
 * A GUI for Duke using FXML.
 * Serves as a bridge between the launcher and the
 * King Program.
 *
 * Sets the scenes.
 */
package king;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.controllers.MainWindow;

import java.io.IOException;

public class Main extends Application {

    private King king = new King("data/king.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKing(king);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
