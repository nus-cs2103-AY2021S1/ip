package sparkles.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sparkles.Sparkles;

/**
 * A GUI for Sparkles using FXML.
 */
public class Main extends Application {

    private Sparkles sparkles = new Sparkles();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSparkles(sparkles);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
