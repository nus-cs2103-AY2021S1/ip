package duke.gui;

import java.io.IOException;

import duke.Duke;
import duke.io.OutputHandlerForGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class is responsible for the core Duke logic in the GUI version of the app.
 */
public class DukeGui extends Application {

    /**
     * Contained <code>Duke</code> object which runs the main logic behind processing user commands.
     */
    private Duke duke;

    /**
     * Sets up the GUI.
     *
     * @param stage The stage where the application is set on.
     */
    @Override
    public void start(Stage stage) {
        try {
            // Initialize GUI
            FXMLLoader fxmlLoader = new FXMLLoader(DukeGui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            // Initialize Duke with output set to GUI
            this.duke = new Duke(new OutputHandlerForGui(fxmlLoader.<MainWindow>getController()));

            // Display GUI
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDukeGui(this);
            stage.show();

            this.duke.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles user input via <code>duke</code>.
     * @param userInput User input.
     * @return Boolean representing if a "bye" command was parsed and processed.
     */
    public boolean handleUserInput(String userInput) {
        return this.duke.processOneCommand(userInput);
    }


}
