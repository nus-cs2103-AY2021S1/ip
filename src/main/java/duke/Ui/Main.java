package duke.ui;

import java.io.IOException;

import duke.main.Duke;
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
    /** Duke to run the program. **/
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        Image applicationIcon = new Image(this.getClass().getResourceAsStream("/images/Application Icon.png"));
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Popii | Your Personal Task Manager | V0.2");
            stage.getIcons().add(applicationIcon);
            stage.show();
            fxmlLoader.<MainWindow>getController().showGreetingMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
