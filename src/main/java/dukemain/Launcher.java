package dukemain;

import javafx.application.Application;
import ui.Main;

/**
 * A launcher class to workaround classpath issues.
 * Credits: CS2103 JavaFX Tutorial
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}