package duke.ui;

import java.util.ArrayList;

import duke.model.task.Task;

/**
 * Deals with user interactions.
 */
public class Ui {
    static final String WELCOME = "Hello. I am Claude! What may I do for you today?";
    static final String GOODBYE = "Goodbye! Hope to see you again soon!";

    public Ui() {}

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        return WELCOME;
    }

    /**
     * Prints goodbye message.
     */
    public String showGoodbye() {
        return (GOODBYE);
    }

    /**
     * Prints a given ArrayList of Tasks.
     * @param taskList ArrayList of Tasks to be printed.
     */
    public String buildTaskList(ArrayList<Task> taskList) {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s = s + (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return s;
    }

    /**
     * Prints error message from failing to load save file.
     */
    public void showLoadingError() {
        System.out.println("Unable to load or find save file. "
                + "Save file is either not found or corrupted. "
                + "Initiating new instance.");
    }
}
