package duke.components;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.views.Welcome;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Encapsulates the GUI for the program.
 */
public class Starter extends Application {
    /**
     * Starts the GUI welcome window.
     *
     * @param window the window of the current view.
     */
    @Override
    public void start(Stage window) {
        // set title for the window
        window.setTitle("Duke");

        // execute GUI loop
        TaskList list = new TaskList();
        Storage storage = new Storage(list);
        new Welcome(storage).setScene(window);
    }
}
