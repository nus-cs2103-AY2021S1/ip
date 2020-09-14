package botbot;

import java.io.IOException;

import botbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Botbot using FXML.
 *
 * @author wakululuu-reused.
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
 */
public class Main extends Application {
    private final Botbot botbot = new Botbot("data/botbot.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBotbot(botbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
