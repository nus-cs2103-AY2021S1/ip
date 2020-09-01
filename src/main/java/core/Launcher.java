package core;

import javafx.application.Application;

import java.io.IOException;

/**
 * Main class containing the entry point of the program.
 */
public class Launcher {

    /**
     * Entry point of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            Storage.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Application.launch(IPbot.class, args);
    }

}
