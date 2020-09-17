package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application.
     * @param args arguments input by the user
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        System.exit(0);
    }
}
