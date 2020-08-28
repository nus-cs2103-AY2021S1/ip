package duke;

import duke.components.Starter;
import javafx.application.Application;

/**
 * Encapsulate the main class of the object.
 */
public class Duke {
    /**
     * Executes the program.
     *
     * @param args command line arguments to be fed to the program.
     */
    public static void main(String[] args) {
        Application.launch(Starter.class, args);
    }
}
