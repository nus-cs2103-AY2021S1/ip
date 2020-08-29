package seedu.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        BorderPane borderPane = fxmlLoader.load();
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setDuke(new Duke());
        stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
