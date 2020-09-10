package duke;

import duke.gui.MainWindow;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

/**
 * This class is the main entry point into the Duke application.
 * Solution below adapted from https://github.com/sc-arecrow/viscount/tree/master
 *
 * @author sc-arecrow
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the GUI of Duke chatbot.
     *
     * @param stage Stage shown on the UI.
     */
    @Override
    public void start(Stage stage) {
        AnchorPane ap = new MainWindow();
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}