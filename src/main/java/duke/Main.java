package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(new Duke());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
