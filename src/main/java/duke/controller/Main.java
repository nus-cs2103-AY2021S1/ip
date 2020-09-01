package duke.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Encapsulates the GUI for the program.
 */
public class Main extends Application {
    /**
     * Starts the GUI welcome window.
     *
     * @param window the window of the current view.
     */
    @Override
    public void start(Stage window) {
        // execute GUI loop
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Menu.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
