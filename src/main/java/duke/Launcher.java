package src.main.java.duke;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(src.main.java.duke.Main.class, args);
    }
}
