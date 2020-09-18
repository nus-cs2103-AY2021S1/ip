package duke;

import duke.gui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches Duke.
     *
     * @param args Standard arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
