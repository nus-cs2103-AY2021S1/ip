package seedu.duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        runGUI(args);
    }

    public static void runCLI(String[] args) {
        new Duke().run();
    }

    public static void runGUI(String[] args) {
        Application.launch(Main.class, args);
    }
}
