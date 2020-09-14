package botbot;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author wakululuu-reused.
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
