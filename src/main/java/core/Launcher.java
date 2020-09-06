package core;

import java.io.IOException;

import javafx.application.Application;

/**
 * Main class containing the entry point of the program.
 */
public class Launcher {

    /**
     * Entry point of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // task list should be empty on startup (before load)
        assert TaskList.size() == 0;
        try {
            Storage.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Application.launch(IpBot.class, args);
    }

}
