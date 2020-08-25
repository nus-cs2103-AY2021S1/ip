package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Handles all interactions with the user.
 */
public class Ui {

    /** Duke a.k.a. Star Bot's logo shown upon start up */
    private static final String LOGO = "     _______.___________.    ___     " +
            " .______      \n" +
            "    /       |           |   /   \\     |   _  \\     \n" +
            "   |   (----`---|  |----`  /  ^  \\    |  |_)  |    \n" +
            "    \\   \\       |  |      /  /_\\  \\   |      /     \n" +
            ".----)   |      |  |     /  _____  \\  |  |\\  \\----.\n" +
            "|_______/       |__|    /__/     \\__\\ | _| `._____|\n" +
            "                                                   \n" +
            "         .______     ______   .___________.        \n" +
            "         |   _  \\   /  __  \\  |           |        \n" +
            "         |  |_)  | |  |  |  | `---|  |----`        \n" +
            "         |   _  <  |  |  |  |     |  |             \n" +
            "         |  |_)  | |  `--'  |     |  |             \n" +
            "         |______/   \\______/      |__|             \n" +
            "                                                   ";

    /** Divider that delineates Duke's replies */
    private static final String DIVIDER =
            "------------------------------------------------------\n";

    /** Scanner used to scan user's input */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Greets the user upon program start up.
     */
    public void showWelcome() {
        System.out.println(LOGO + "\nHello, I'm Star Bot! What can I do for " +
                "you?\nSay \"bye\" to exit.\n");
    }

    /**
     * Says goodbye to the user before termination of the program.
     */
    public void showGoodbye() {
        showReply("Goodbye, see you again soon! :)");
    }

    /**
     * Informs the user that an error occurred.
     *
     * @param e The error that occurred in the program.
     */
    public void showError(Exception e) {
        showReply(e.getMessage());
    }

    /**
     * Reads the user input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        return SCANNER.nextLine();
    }

    /**
     * Shows the program's response to the user's input. The look of these replies are standardised by wrapping them in
     * dividers.
     *
     * @param reply The text to present to the user.
     */
    public void showReply(String reply) {
        System.out.println(DIVIDER + reply + "\n" + DIVIDER);
    }

    /**
     * Shows a standardised reply for when the user adds a task.
     *
     * @param newTask The task being added.
     * @param tasks The list of tasks that the task is being added to.
     */
    public void showReplyForAddTask(Task newTask, TaskList tasks) {
        showReply("Got it. I've added this task:\n" + newTask + "\nNow " +
                "you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Shows a standardised reply for when the user completes a task.
     *
     * @param doneTask The task that has been completed.
     */
    public void showReplyForDoneTask(Task doneTask) {
        showReply("Nice! I've marked this task as done:\n" + doneTask);
    }

    /**
     * Shows a standardised reply for when the user removes a task.
     *
     * @param removedTask The task being removed.
     * @param tasks The list of tasks that the task is being removed from.
     */
    public void showReplyForDeleteTask(Task removedTask, TaskList tasks) {
        showReply("Noted. I've removed this task:\n" + removedTask +
                "\nNow you have " + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Formats the task list to be shown to the user.
     *
     * @param tasks The list of tasks to be shown.
     * @return The list of tasks in String format.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showReply("Your list is empty! Let's add some tasks!");
        } else {
            showReply("Here are the tasks in your list:" + tasks.toString());
        }
    }

    /**
     * Formats the task list to be shown to the user.
     */
    public void showFoundTaskList(TaskList tasks, String keyWords) {
        if (tasks.isEmpty()) {
            showReply("Sorry! No tasks were found with the word(s)\n\"" + keyWords + "\".");
        } else {
            showReply("Here are the tasks in your list with the word(s)\n\"" + keyWords + "\":" + tasks.toString());
        }
    }
}
