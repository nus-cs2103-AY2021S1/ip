package duke;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues.
 * Serves as an entry point into our JavaFX application.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}