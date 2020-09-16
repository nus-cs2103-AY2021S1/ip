package duke;


import java.io.IOException;

import duke.gui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();


    private void initialiseDuke(FXMLLoader loader) {
        loader.<MainWindow>getController().setDuke(duke);
        duke.start(false);
        loader.<MainWindow>getController().printWelcomeMessage();
    }


    /**
     * Starts the JavaFX Gui.
     *
     * @param stage Stage to be used in Gui.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("CS for Lyfe!");
            stage.setScene(scene);

            this.initialiseDuke(fxmlLoader);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
