package duke;

import javafx.application.Application;

//@@author Jeffry Lum
//Guides for SE Student Project: Java FX Tutorial
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launch the application by calling start function in Main class.
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(duke.Main.class, args);
    }
}
