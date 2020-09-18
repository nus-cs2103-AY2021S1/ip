package dude;

import javafx.application.Application;

/**
 * A Launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Dude bot application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
