package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches Duke in the GUI application.
     *
     * @param args Standard arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}