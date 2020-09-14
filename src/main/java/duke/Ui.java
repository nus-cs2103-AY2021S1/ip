package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui object deals with interactions with the user, including on screen messages and taking in user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initializes the Ui object with a new Scanner object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows the greeting message.
     */
    public String showGreeting() {
        String message = "Are ya winning, son?\n\n"
                + "Here are the following commands you can use:\n\n"
                + "todo {name of task}\n"
                + "deadline {name of task} /by {YYYY-MM-DD}\n"
                + "event {name of task} /at {YYYY-MM-DD}\n"
                + "done {task index}\n"
                + "delete {task index}\n"
                + "find {keyword}\n"
                + "list\n"
                + "statistics\n";
        return cleanMessage(message);
    }

    /**
     * Shows the goodbye message.
     */
    public String showGoodbye() {
        String message = "Alright I'll see you around!\n";
        return cleanMessage(message);
    }

    /**
     * Shows the loading file error.
     */
    public String showLoadingError() {
        return (cleanMessage("Error loading file!"));
    }

    /**
     * Shows the list of tasks in the list.
     *
     * @param tasks Tasks currently in the list.
     */
    public String showList(TaskList tasks) {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            message += (i + 1)
                    + ". "
                    + task
                    + "\n";
        }
        return cleanMessage(message);
    }

    /**
     * Shows some statistics about the Tasks in the list.
     *
     * @param tasks The current list of tasks.
     * @param statistics The statistics of the tasks in the list.
     * @return The list of statistics to be shown to the user.
     */
    public String showStatistics(TaskList tasks, Statistics statistics) {
        String message = "Here are some statistics about your tasks:\n\n"
                + "You have completed " + statistics.getNumOfDoneTodos()
                + " out of " + statistics.getNumOfTodos()
                + " Todos. (" + statistics.getPercentageOfDoneTodos() + "%)\n"
                + "You have completed " + statistics.getNumOfDoneDeadlines()
                + " out of " + statistics.getNumOfDeadlines()
                + " Deadlines. (" + statistics.getPercentageOfDoneDeadlines() + "%)\n"
                + "You have completed " + statistics.getNumOfDoneEvents()
                + " out of " + statistics.getNumOfEvents()
                + " Events. (" + statistics.getPercentageOfDoneEvents() + "%)\n"
                + "\n"
                + "In total, you have completed " + statistics.getNumOfDoneTasks()
                + " out of " + tasks.getSize()
                + " tasks. (" + statistics.getPercentageOfDoneTasks() + "%)\n";
        return cleanMessage(message);
    }

    /**
     * Shows the add message when the user adds a task.
     *
     * @param task The added task.
     * @param tasks The current list of tasks.
     */
    public String showAdd(Task task, TaskList tasks) {
        String message = "Got it. I've added this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        return cleanMessage(message);
    }

    /**
     * Shows the delete message when the user deletes a task.
     *
     * @param task The deleted task.
     * @param tasks The current list of tasks.
     */
    public String showDelete(Task task, TaskList tasks) {
        String message = "Noted! I've removed this task:\n"
                + task
                + "\n"
                + "Now you have "
                + tasks.getSize()
                + " tasks in the list.";
        return cleanMessage(message);
    }

    /**
     * Shows the message when the user marks a task as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showDoneTask(Task task) {
        String message = "Nice! I've marked this task as done:\n"
                + task;
        return cleanMessage(message);
    }

    /**
     * Shows the message when the user finds a list of tasks with a keyword.
     *
     * @param tasks The list of tasks containing the keyword.
     */
    public String showFind(TaskList tasks) {
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            message += (i + 1)
                    + ". "
                    + task
                    + "\n";
        }
        return cleanMessage(message);
    }

    /**
     * Shows the error message.
     *
     * @param message The error message to be shown.
     */
    public String showError(String message) {
        return cleanMessage(message);
    }

    /**
     * Wraps the message between top and bottom lines.
     *
     * @param message The message to be wrapped.
     * @return The wrapped message.
     */
    public String cleanMessage(String message) {
        if (message.endsWith("\n")) {
            // If the message ends with a newline, remove the newline
            message = message.substring(0, message.length() - 1);
        }

        return message;
    }
}
