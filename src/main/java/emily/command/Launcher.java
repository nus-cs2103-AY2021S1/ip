package main.java.emily.command;


import javafx.application.Application;
import main.java.emily.app.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}