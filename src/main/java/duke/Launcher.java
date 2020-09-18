package duke;

import duke.Main;
import javafx.application.Application;

/**
 * Serves as an entry point into our application.
 * A launcher class to work around classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}