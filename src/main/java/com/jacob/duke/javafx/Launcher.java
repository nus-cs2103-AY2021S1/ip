package main.java.com.jacob.duke.javafx;

import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the JavaFx application.
     *
     * @param args Passed arguments to initialize main method.
     */
    public static void main(String[] args) {
        //require a no argument constructor -> how to pass arguments?
        Application.launch(Main.class, args);
    }
}
