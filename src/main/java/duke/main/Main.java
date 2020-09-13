package duke.main;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// @@author Jeffry Lum-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications
/**
 * Main is a class that represents an application that houses the Duke chat-bot.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Solution adapted from
            // http://www.learningaboutelectronics.com/Articles/How-to-add-a-title-to-a-window-using-JavaFX.php
            stage.setTitle("NotDuke");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// @@author
