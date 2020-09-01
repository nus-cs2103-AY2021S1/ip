package duke;

import javafx.application.Application;

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
