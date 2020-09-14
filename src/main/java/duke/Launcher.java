package duke;

import javafx.application.Application;
//@@author RuiFengg-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        ;
    }
}
