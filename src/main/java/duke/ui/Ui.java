package duke.ui;

import java.util.Scanner;

import duke.TaskList;
import duke.task.Task;

/**
 * Handles all interactions with the user in the CLI version of Duke.
 */
public class Ui {

    /** Duke a.k.a. Star Bot's logo shown upon start up */
    private static final String LOGO = "     _______.___________.    ___     "
            + " .______      \n"
            + "    /       |           |   /   \\     |   _  \\     \n"
            + "   |   (----`---|  |----`  /  ^  \\    |  |_)  |    \n"
            + "    \\   \\       |  |      /  /_\\  \\   |      /     \n"
            + ".----)   |      |  |     /  _____  \\  |  |\\  \\----.\n"
            + "|_______/       |__|    /__/     \\__\\ | _| `._____|\n"
            + "                                                   \n"
            + "         .______     ______   .___________.        \n"
            + "         |   _  \\   /  __  \\  |           |        \n"
            + "         |  |_)  | |  |  |  | `---|  |----`        \n"
            + "         |   _  <  |  |  |  |     |  |             \n"
            + "         |  |_)  | |  `--'  |     |  |             \n"
            + "         |______/   \\______/      |__|             \n"
            + "                                                   ";

    /** Divider that delineates Duke's replies */
    private static final String DIVIDER =
            "------------------------------------------------------\n";

    private static final String GREETING = "\nHello, I'm Star Bot! What can I do for you?\n";
    private static final String GOODBYE_MESSAGE = "Goodbye, see you again soon! :)";
    private static final String EMPTY_LIST_MESSAGE = "Your list is empty! Let's add some tasks!";

    /** Scanner used to scan user's input */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Greets the user upon program start up.
     */
    public void showWelcome() {
        System.out.println(LOGO + GREETING);
    }

    /**
     * Shows the program's response to the user's input. The look of these replies are standardised by wrapping them in
     * dividers.
     *
     * @param reply The text to present to the user.
     */
    private void showReply(String reply) {
        assert !reply.isEmpty() : "showReply called with no valid reply string.";
        System.out.println(DIVIDER + reply + "\n" + DIVIDER);
    }

    /**
     * Says goodbye to the user before termination of the program.
     */
    public void showGoodbye() {
        showReply(GOODBYE_MESSAGE);
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
     * Shows a standardised reply for when the user adds a task.
     *
     * @param newTask The task being added.
     * @param tasks The list of tasks that the task is being added to.
     */
    public void showReplyForAddTask(Task newTask, TaskList tasks) {
        showReply("Got it. I've added this task:\n" + newTask.toStringForCli() + "\nNow you have "
                + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Shows a standardised reply for when the user completes a task.
     *
     * @param doneTask The task that has been completed.
     */
    public void showReplyForDoneTask(Task doneTask) {
        showReply("Nice! I've marked this task as done:\n" + doneTask.toStringForCli());
    }

    /**
     * Shows a standardised reply for when the user removes a task.
     *
     * @param removedTask The task being removed.
     * @param tasks The list of tasks that the task is being removed from.
     */
    public void showReplyForDeleteTask(Task removedTask, TaskList tasks) {
        showReply("Noted. I've removed this task:\n" + removedTask.toStringForCli() + "\nNow you have "
                + tasks.getNumberOfTasks() + " tasks in the list.");
    }

    /**
     * Formats the task list to be shown to the user.
     *
     * @param tasks The list of tasks to be shown.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showReply(EMPTY_LIST_MESSAGE);
        } else {
            showReply("Here are the tasks in your list:" + tasks.toStringForCli());
        }
    }

    /**
     * Formats the task list to be shown to the user.
     */
    public void showFoundTaskList(TaskList tasks, String keyWords) {
        if (tasks.isEmpty()) {
            showReply("Sorry! No tasks were found with the word(s)\n\"" + keyWords + "\".");
        } else {
            showReply("Here are the tasks in your list with the word(s)\n\"" + keyWords + "\":"
                    + tasks.toStringForCli());
        }
    }
}
