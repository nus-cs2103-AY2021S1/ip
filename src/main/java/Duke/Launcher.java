package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs Launcher.
     * @param args arguments to input.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        new Duke("D:/uni/CS2103T/Duke(iP)/ip/data/tasks.txt").run();
    }
}
