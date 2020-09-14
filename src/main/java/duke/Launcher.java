package duke;

import javafx.application.Application;

/**
 * Launches the programme.
 */
public class Launcher {
    /**
     * Launches Duke.
     *
     * @param args Java command line arguments.
     */
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
