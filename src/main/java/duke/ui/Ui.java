package duke.ui;

import java.util.List;
import java.util.stream.IntStream;

import duke.task.Task;

/**
 * Represents the class that deals with interactions with the user.
 */
public class Ui {

    /**
     * The greeting message to be shown when the programme starts.
     */
    private static final String GREETINGS = "Hello! I'm Duke. What can I do for you?\n";

    /**
     * The exit message to be shown when the programme terminates.
     */
    private static final String GOODBYE = "Goodbye! Hope to see you again soon!";

    /**
     * Returns string representation of the welcome message to the user.
     *
     * @return The welcome message to the user.
     */
    public String showWelcomeMessage() {
        return GREETINGS;
    }

    /**
     * Returns string representation of the exit message to the user.
     *
     * @return The exit message to the user.
     */
    public String showExitMessage() {
        return GOODBYE;
    }

    /**
     * Returns string representation of the message when a task is being added.
     *
     * @param task The task to be added.
     * @param numTasks The total number of tasks after adding.
     * @return The message indicating a task has been added.
     */
    public String showAddMessage(Task task, int numTasks) {
        String message = "Okay! Task added for you!\n";
        message = message.concat(task.toString() + "\n");
        message = message.concat("Now you have " + numTasks + " task(s) in the list." + "\n");
        return message;
    }

    /**
     * Returns string representation of the message when a task is being deleted.
     *
     * @param task The task to be deleted.
     * @param numTasks The total number of tasks after deleting.
     * @return The message indicating a task has been deleted.
     */
    public String showDeleteMessage(Task task, int numTasks) {
        String message = "Noted. The following task is removed:\n";
        message = message.concat(task.toString() + "\n");
        message = message.concat("Now you have " + numTasks + " task(s) in the list." + "\n");
        return message;
    }

    /**
     * Returns string representation of the message when a task is being marked as done.
     *
     * @param task The task to be marked as done.
     * @return The message indicating a task has been marked as done.
     */
    public String showDoneMessage(Task task) {
        String message = "Good job! I've marked this task as done:\n";
        message = message.concat(task + "\n");
        return message;
    }

    /**
     * Returns string representation of the message when the list of tasks is sorted.
     *
     * @return The message indicating the list has been sorted
     */
    public String showSortMessage(List<Task> tasks) {
        return tasks.isEmpty()
                ? "There are no tasks to sort!"
                : "Alright! I have sorted your tasks in order!";
    }

    /**
     * Returns string representation of the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The list of tasks to the user.
     */
    public String displayTasks(List<Task> tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = "No tasks added to your list yet!\n";
        } else {
            message = "Here are the tasks in your list:\n";
            message = IntStream
                    .range(0, tasks.size())
                    .mapToObj(i -> i + 1 + ". " + tasks.get(i) + "\n")
                    .reduce(message, String::concat);
        }
        return message;
    }

    /**
     * Returns string representation of the list of matching tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String displayMatchingTasks(List<Task> tasks) {
        String message;
        if (tasks.isEmpty()) {
            message = "No matching tasks found in your list!\n";
        } else {
            message = "Here are the matching tasks in your list:\n";
            message = IntStream
                    .range(0, tasks.size())
                    .mapToObj(i -> i + 1 + ". " + tasks.get(i) + "\n")
                    .reduce(message, String::concat);
        }
        return message;
    }
}
