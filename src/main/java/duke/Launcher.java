package duke;

import duke.ui.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the Duke application.
     *
     * @param args Empty argument.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
