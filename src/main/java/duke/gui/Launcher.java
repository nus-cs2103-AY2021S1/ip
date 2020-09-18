package main.java.duke.gui;

import main.java.duke.Duke;
import javafx.application.Application;

/**
 * Serves as an entry point into our application.
 * A launcher class to work around classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}