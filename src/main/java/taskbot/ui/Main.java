package taskbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taskbot.logic.Taskbot;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Taskbot taskbot = new Taskbot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTaskbot(taskbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}