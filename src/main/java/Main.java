import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Duke;
import ui.MainWindow;

/**
 * A GUI for model.Duke using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Duke duke = new Duke("data/TaskList.txt");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("DukeBunny");
            stage.getIcons().add(new Image("/images/DukeBunnyPxl.PNG"));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
