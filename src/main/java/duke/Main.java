package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import duke.ui.Ui;
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
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();
            Duke duke = new Duke(new Ui(System.in, mainWindow.getDialogContainer()));
            mainWindow.setDuke(duke);
            duke.startDuke();
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/logo.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
