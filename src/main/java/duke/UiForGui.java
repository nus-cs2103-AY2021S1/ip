package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Handles all interactions with the user.
 */
public class UiForGui {

    /** Scanner used to scan user's input */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Greets the user upon program start up.
     */
    public static String showWelcome() {
        return "Hello, I'm Star Bot! What can I do for you?\nSay \"bye\" to exit.\n";
    }

    /**
     * Says goodbye to the user before termination of the program.
     */
    public String showGoodbye() {
        return "Goodbye, see you again soon! :)";
    }

    /**
     * Informs the user that an error occurred.
     *
     * @param e The error that occurred in the program.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Shows a standardised reply for when the user adds a task.
     *
     * @param newTask The task being added.
     * @param tasks The list of tasks that the task is being added to.
     */
    public String showReplyForAddTask(Task newTask, TaskList tasks) {
        return "Got it. I've added this task:\n" + newTask + "\nNow you have " + tasks.getNumberOfTasks()
                + " tasks in the list.";
    }

    /**
     * Shows a standardised reply for when the user completes a task.
     *
     * @param doneTask The task that has been completed.
     */
    public String showReplyForDoneTask(Task doneTask) {
        return "Nice! I've marked this task as done:\n" + doneTask;
    }

    /**
     * Shows a standardised reply for when the user removes a task.
     *
     * @param removedTask The task being removed.
     * @param tasks The list of tasks that the task is being removed from.
     */
    public String showReplyForDeleteTask(Task removedTask, TaskList tasks) {
        return "Noted. I've removed this task:\n" + removedTask + "\nNow you have " + tasks.getNumberOfTasks()
                + " tasks in the list.";
    }

    /**
     * Formats the task list to be shown to the user.
     *
     * @param tasks The list of tasks to be shown.
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Your list is empty! Let's add some tasks!";
        } else {
            return "Here are the tasks in your list:" + tasks.toString();
        }
    }

    /**
     * Formats the task list to be shown to the user.
     */
    public String showFoundTaskList(TaskList tasks, String keyWords) {
        if (tasks.isEmpty()) {
            return "Sorry! No tasks were found with the word(s)\n\"" + keyWords + "\".";
        } else {
            return "Here are the tasks in your list with the word(s)\n\"" + keyWords + "\":" + tasks.toString();
        }
    }
}
