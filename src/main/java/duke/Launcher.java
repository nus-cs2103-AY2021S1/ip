package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launcher entry's point.
     *
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
