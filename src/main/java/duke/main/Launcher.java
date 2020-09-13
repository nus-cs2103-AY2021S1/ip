package duke.main;

import javafx.application.Application;

// @@author Jeffry Lum-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart1.html
/**
 * Driver class to run the main method.
 */
public class Launcher {
    /**
     * Runs Duke chat-bot.
     * It first initialises by reading a saved list of tasks in data/tasks.txt.
     *
     * @param args Main method arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
// @@author
