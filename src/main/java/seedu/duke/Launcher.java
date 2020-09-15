package seedu.duke;

import javafx.application.Application;
import seedu.duke.ui.Main;

//@@author Jeffry Lum
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
//@@author
