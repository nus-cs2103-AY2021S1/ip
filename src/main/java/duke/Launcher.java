package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        runGui(args);
    }

    public static void runCli(String[] args) {
        new Duke().run();
    }

    public static void runGui(String[] args) {
        Application.launch(Main.class, args);
    }
}
