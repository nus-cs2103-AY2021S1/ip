package duke;

import java.util.ArrayList;

/**
 * Encapsulates the user interactions.
 */
public class Ui {
    private String message;
    /**
     * Instantiates Ui.
     */
    Ui() {
        this.message = "";
    }

    /**
     * Clear the current message.
     */
    public void clearMessage() {
        this.message = "";
    }

    /**
     * Get the current message.
     * @return current message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Prints welcome message for user.
     */
    public void showGreeting() {
        this.message = "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        this.message = "Sorry, an error occurred while loading the data\n";
    }

    /**
     * Prints exit message.
     */
    public void showExitMessage() {
        this.message = "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks TaskList to be printed.
     */
    public void showTasksList(TaskList tasks) {
        int index = 1;
        ArrayList<Task> tasksList = tasks.getTasksList();
        this.message = "Here are the tasks in your list:\n";
        for (Task t : tasksList) {
            this.message = this.message + index + ". " + t + "\n";
            index++;
        }
    }

    /**
     * Prints the task added.
     *
     * @param task Task added.
     * @param size Size of the updated TaskList.
     */
    public void showAddTask(Task task, int size) {
        String taskText = "Got it. I've added this task:" + "\n" + task + "\n";
        String totalText = "Now you have " + size + " tasks in the list";
        this.message = taskText + totalText;
    }

    /**
     * Prints the task deleted.
     *
     * @param task Task deleted.
     * @param size Size of the updated TaskList.
     */
    public void showDeleteTask(Task task, int size) {
        this.message = "Noted. I've removed this task:\n" + task + "\n"
                + "Now you have " + size + " tasks in the list\n";
    }

    /**
     * Prints the completed Task.
     *
     * @param task Task completed.
     */
    public void showTaskDone(Task task) {
        this.message = "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    /**
     * Print error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        this.message = message;
    }

    /**
     * Prints the tasks that matches the keyword.
     *
     * @param tasks TaskList of tasks that matches keyword.
     */
    public void showTasksFound(TaskList tasks) {
        this.message = "Here are the matching tasks in your list:\n";
        int index = 1;
        ArrayList<Task> tasksList = tasks.getTasksList();
        for (Task t : tasksList) {
            this.message = this.message + index + ". " + t + "\n";
            index++;
        }
    }
}
