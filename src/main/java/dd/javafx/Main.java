package dd.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            /* logo image from
            https://www.clipartkey.com/view/hhoTTh_transparent-emoji-peach-png-transparent-background-peach-emoji/ */
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/appIcon.png")));
            stage.setTitle("Timmi the Task Manager");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
