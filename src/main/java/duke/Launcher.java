package duke;

import javafx.application.Application;

/**
 * Class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application by calling start function in Main class.
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(duke.Main.class, args);
    }

}
