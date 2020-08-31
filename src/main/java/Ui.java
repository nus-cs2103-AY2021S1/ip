import java.util.Scanner;

/**
 * The Ui class handles input and output from the user in the console.
 *
 * @author Jaya Rengam
 */
public class Ui {
    private Scanner sc;

    private static String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     */
    public String getNextLineInput() {
        return sc.nextLine();
    }

    /**
     * Prints the given error message to the console.
     * @param errorMessage
     */
    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s     %s%n%s", HORIZONTAL_LINE, errorMessage, HORIZONTAL_LINE);
    }

    /**
     * Prints the startup message.
     */
    public void printWelcomeMessage() {
        System.out.printf("%s     Hello! I'm Cartona.%n     What can I do for you?%n%s",
                HORIZONTAL_LINE, HORIZONTAL_LINE);
    }

    /**
     * Prints a message reflecting the successful adding of a task.
     * @param task The task that was added.
     * @param taskListSize The current size of the TaskList.
     */
    public void printTaskAddingMessage(Task task, int taskListSize) {
        System.out.printf("%s     Got it. I've added this task:%n       %s%n     "
                            + "Now you have %d tasks in the list.%n%s",
                HORIZONTAL_LINE, task, taskListSize, HORIZONTAL_LINE);
    }

    /**
     * Prints a message reflecting the successful deletion of a task.
     * @param task The task that was deleted.
     * @param taskListSize The current size of the TaskList.
     */
    public void printTaskDeletionMessage(Task task, int taskListSize) {
        String deleteMsg = String.format("     Noted. I've removed this task:%n       %s%n", task);
        String countMsg = String.format("     Now you have %d tasks in the list.%n", taskListSize);
        System.out.printf(HORIZONTAL_LINE + deleteMsg + countMsg + HORIZONTAL_LINE);
    }

    /**
     * Prints a message reflecting the successful completion of a task.
     * @param task The task that was marked as completed.
     */
    public void printTaskDoneMessage(Task task) {
        String completion = "     Nice! I've marked this task as done:\n"
                + String.format("       %s%n", task);
        System.out.printf(HORIZONTAL_LINE + completion + HORIZONTAL_LINE);
    }

    /**
     * Prints a numbered list of Tasks from the provided TaskList.
     */
    public void printTaskList(TaskList taskList) {
        String toPrint = HORIZONTAL_LINE + "     Here are the tasks in your list:\n";
        toPrint += taskList.toString();
        toPrint += HORIZONTAL_LINE;
        System.out.printf(toPrint);
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        String toPrint = HORIZONTAL_LINE + "     List saved! Goodbye!%n" + HORIZONTAL_LINE;
        System.out.printf(toPrint);
    }
}