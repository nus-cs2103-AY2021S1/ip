package focus;

import javafx.application.Application;

/** Represents a launcher class to workaround classpath issues. */
public class Launcher {
    /**
     * Entry point to run Focus.
     *
     * @param args Args.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
