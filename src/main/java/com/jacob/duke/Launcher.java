package main.java.com.jacob.duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        //require a no argument constructor -> how to pass arguments?
        Application.launch(Main.class, args);
    }
}
