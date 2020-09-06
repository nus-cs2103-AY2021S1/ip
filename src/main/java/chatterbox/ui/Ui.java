package chatterbox.ui;

import chatterbox.task.Task;

/**
 * Utility class for printing formatted messages of the Chatterbox program.
 */
public class Ui {
    /**
     * Returns the add task message.
     *
     * @param t The task that has been added.
     * @param totalTasks    The total number of tasks in the list currently.
     * @return The add task message.
     */
    public static String getAddTaskMessage(Task t, int totalTasks) {
        assert t != null;
        assert totalTasks > 0;
        return "Got it. I've added this task: \n"
            + t + "\n"
            + "Now you have " + totalTasks + " tasks in the list";
    }

    /**
     * Returns the delete task message.
     *
     * @param t The task that has been deleted.
     * @param totalTasks    The total number of tasks in the list currently.
     * @return The delete task message.
     */
    public static String getDeleteTaskMessage(Task t, int totalTasks) {
        assert t != null;
        assert totalTasks >= 0;
        return "Noted! I've removed this task from your list: \n"
            + t + "\n"
            + " Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Returns the done task message.
     *
     * @param t The task that has been marked as done.
     * @return The done task message.
     */
    public static String getDoneTaskMessage(Task t) {
        return "Nice! I've marked this task as done: \n" + t;
    }

    /**
     * Returns the first message the user sees when the program starts.
     *
     * @return The welcome message.
     */
    public static String getWelcomeMessage() {
        return "Hello I'm Chatterbox. What can I do for you?";
    }

    /**
     * Returns an error message.
     *
     * @param error The message to show.
     * @return The error meesage.
     */
    public static String getErrorMessage(String error) {
        return "☹ OOPS!!! " + error;
    }
}
