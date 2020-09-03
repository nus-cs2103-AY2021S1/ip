package duke;

import javafx.application.Application;

/**
 * Launches the programme.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            Application.launch(Main.class, args);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
