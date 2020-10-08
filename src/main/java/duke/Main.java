package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            BorderPane borderPane = fxmlLoader.load();
            Scene scene = new Scene(borderPane);
            scene.getStylesheets().add("/css/stylesheet.css");
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.setMinHeight(200);
            stage.setMinWidth(400);
            fxmlLoader.<MainWindow>getController().setDuke(new Duke());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
