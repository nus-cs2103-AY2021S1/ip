package taskbot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import taskbot.logic.Taskbot;

/**
 * A GUI for TaskBot using FXML.
 */
public class Ui extends Application {

    private Taskbot taskbot = new Taskbot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);

            // Setting up background
            BackgroundFill backgroundFill = new BackgroundFill(Color.CORNFLOWERBLUE,
                    CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            anchorPane.setBackground(background);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("TaskBot");

            fxmlLoader.<MainWindow>getController().setTaskbot(taskbot);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
