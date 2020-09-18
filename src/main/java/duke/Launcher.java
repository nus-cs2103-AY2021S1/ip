package duke;

import javafx.application.Application;

/**
 * A Launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application on to a GUI. This is the main method which
     * serves as the entry point of the Duke application with GUI.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
