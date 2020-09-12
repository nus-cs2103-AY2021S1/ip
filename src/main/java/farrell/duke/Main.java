package main.java.farrell.duke;

import javafx.application.Application;
import main.java.farrell.duke.gui.UiManager;

/** The main entry point for the program */
public class Main {
    /**
     * The main entry point for the program.
     * This launches JavaFx and only exists when the JavaFx window is closed.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(UiManager.class, args);
    }
}
