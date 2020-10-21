import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();


    @Override
    public void start(Stage stage) {
        duke.myStorage.createDirectory("ToDo");
        duke.myStorage.populateList(duke.myTaskList);
        try {
            stage.setTitle("JARVIS");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/view/stylesheet.css");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}