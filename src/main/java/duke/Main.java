package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafxstyle.MainWindow;

/**
 * A GUI for Duke using FXML.
 * @author Damith C. Rajapakse
 * Reused from https://se-education.org/guides/tutorials/javaFx.html with modifications.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the application.
     *
     * @param stage Stage object.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke Application");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
