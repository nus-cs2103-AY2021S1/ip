package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Run the Duck chat bot with a Graphical User Interface by launching Main.class.
     **/
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
