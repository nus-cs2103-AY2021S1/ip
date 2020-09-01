package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * This is the main method to launch JavaFX.
     * @param args Unused.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
