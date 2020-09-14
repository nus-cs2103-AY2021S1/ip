package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author Jeffry Lum
//Guides for SE Student Project: Java FX Tutorial
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts up the application layout and waits for user's commands.
     *
     * @param stage Main layout of the applcation.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Chatty-Bot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
