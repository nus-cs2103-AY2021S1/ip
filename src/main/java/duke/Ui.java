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
     *
     * @return current message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Prints welcome message for user.
     */
    public void showGreeting() {
        this.message = "Greetings! I'm Duke" + "\n" + "What can I do for you?"
                + "\n\n" + "Type 'help' to find out more about the commands!";
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

    /**
     * Provide in-app guidance for users.
     */
    public void showHelpMessage() {
        this.message = "Hello! Here are the things that I can help you with:\n"
                + "-------------\n"
                + "1. list \u2192 displays the tasks to be completed\n"
                + "2. find <keyword> \u2192 search for the items that matches the keyword you have provided\n"
                + "3. done <index> \u2192 mark a task as completed\n"
                + "4. delete <index> \u2192 delete a task\n"
                + "5. todo <task name> \u2192 add a ToDo task to the list\n"
                + "6. deadline <task name> /by <yyyy-mm-dd> <hhmm> \u2192 add a Deadline task to the list\n"
                + "7. event <task name> /at <yyyy-mm-dd> <hhmm> \u2192 add a Event task to the list\n"
                + "8. bye \u2192 exits the programme";
    }
}
