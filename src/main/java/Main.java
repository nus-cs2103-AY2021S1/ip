import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("Tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            setTitle(stage);
            setTitleIcon(stage);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().greetUser();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTitle(Stage stage) {
        stage.setTitle("Duke");
    }

    private void setTitleIcon(Stage stage) {
        stage.getIcons().add(new Image("/images/Duke.png"));
    }
}
