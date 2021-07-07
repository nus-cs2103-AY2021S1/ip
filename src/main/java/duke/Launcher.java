package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The initial method to launch the entire application.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
