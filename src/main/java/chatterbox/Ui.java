package chatterbox;

import chatterbox.task.Task;

/**
 * Utility class for printing formatted messages of the Chatterbox program.
 */
public class Ui {
    /**
     * Shows the add task message.
     *
     * @param t The task that has been added.
     * @param totalTasks    The total number of tasks in the list currently.
     */
    public static String getAddTaskMessage(Task t, int totalTasks) {
        return "Got it. I've added this task: \n"
            + t + "\n"
            + "Now you have " + totalTasks + " tasks in the list";
    }

    /**
     * Shows the delete task message.
     *
     * @param t The task that has been deleted.
     * @param totalTasks    The total number of tasks in the list currently.
     */
    public static String getDeleteTaskMessage(Task t, int totalTasks) {
        return "Noted! I've removed this task from your list: \n"
            + t + "\n"
            + " Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Shows the first message the user sees when the program starts.
     */
    public static String getWelcomeMessage() {
        return "Hello I'm Chatterbox. What can I do for you?";
    }

    /**
     * Shows an error message.
     *
     * @param error The message to show.
     */
    public static String getErrorMessage(String error) {
        return "☹ OOPS!!! " + error;
    }

    /**
     * Shows the last message the user sees when the program exits.
     */
    public static String getFarewellMessage() {
        return "Goodbye! Hope to see you again soon!";
    }
}
