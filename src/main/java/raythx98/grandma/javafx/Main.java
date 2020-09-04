package raythx98.grandma.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import raythx98.grandma.Grandma;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Grandma grandma = new Grandma("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGrandma(grandma);
            stage.getIcons().add(new Image("/images/BombIcon.png"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}