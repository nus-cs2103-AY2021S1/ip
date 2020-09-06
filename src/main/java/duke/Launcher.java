package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs the program.
     * @param args java command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
        //Application.launch(Main.class, args);
    }
}
