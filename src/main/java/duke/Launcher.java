package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main method to launch the chat bot application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
