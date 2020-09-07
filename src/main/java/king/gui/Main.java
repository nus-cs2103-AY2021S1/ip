/**
 * A GUI for Duke using FXML.
 * Serves as a bridge between the launcher and the
 * King Program.
 *
 * Sets the scenes.
 */
package king.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import king.King;
import storage.StorageException;
import ui.controllers.MainWindow;

public class Main extends Application {

    private King king;

    @Override
    public void start(Stage stage) {
        try {
            king = new King("data/king.txt");
            stage.setTitle("King Bot");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKing(king);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            StackPane layout = new StackPane();
            Text text = new Text(e.message);
            layout.getChildren().add(text);
            Scene scene = new Scene(layout, 500, 600);
            stage.setScene(scene);
        } finally {
            stage.show();
        }
    }
}
