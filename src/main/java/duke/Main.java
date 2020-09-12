package duke;

import java.io.IOException;

import duke.ui.MainWindow;
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

    private final Duke duke = new Duke();
    private Image applicationIcon =
            new Image(this.getClass().getResourceAsStream("/images" + "/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.getIcons().add(applicationIcon);
            scene.getStylesheets().add("styles/style.css");
            stage.setResizable(false);

            stage.show();

            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.initialiseDuke(duke);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
