package duke;

import java.io.IOException;

import duke.main.Duke;
import duke.ui.controller.MainWindow;
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

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            //Image is taken from https://wiki.openjdk.java.net/display/duke/Gallery.
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Duke.png")));
            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
