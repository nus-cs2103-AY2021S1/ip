package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */

public class Launcher {
    private Duke duke;

    public Launcher() {
        duke = new Duke();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
