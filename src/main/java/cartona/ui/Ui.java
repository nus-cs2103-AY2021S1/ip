package cartona.ui;

import cartona.task.Task;
import cartona.task.TaskList;

/**
 * The Ui class generates text strings that represent messages from the program.
 *
 * @author Jaya Rengam
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public Ui() {

    }

    /**
     * Returns the given error message to the console.
     * @param errorMessage
     */
    public String getErrorMessageFormatted(String errorMessage) {
        assert !(errorMessage.equals("")) : "Error message cannot be empty";
        return String.format("%s     %s%n%s", HORIZONTAL_LINE, errorMessage, HORIZONTAL_LINE);
    }

    /**
     * Returns the startup message.
     */
    public String getWelcomeMessageFormatted() {
        return String.format("%s     Hello! I'm Cartona.%n"
                        + "     What can I do for you?"
                        + "%n%s",
                HORIZONTAL_LINE, HORIZONTAL_LINE);
    }

    /**
     * Returns a message reflecting the successful adding of a Task.
     * @param task The task that was added.
     * @param taskListSize The current size of the TaskList.
     */
    public String printTaskAddingMessage(Task task, int taskListSize) {
        return String.format("%s     Got it. I've added this task:%n       %s%n     "
                            + "Now you have %d tasks in the list."
                            + "%n%s",
                HORIZONTAL_LINE, task, taskListSize, HORIZONTAL_LINE);
    }

    /**
     * Returns a message reflecting the successful deletion of a Task.
     * @param task The task that was deleted.
     * @param taskListSize The current size of the TaskList.
     */
    public String printTaskDeletionMessage(Task task, int taskListSize) {
        return String.format(HORIZONTAL_LINE
                + "     Noted. I've removed this task:%n       %s%n"
                + "     Now you have %d tasks in the list.%n"
                + HORIZONTAL_LINE,
                    task, taskListSize);
    }

    /**
     * Returns a message reflecting the successful completion of a Task.
     * @param task The task that was marked as completed.
     */
    public String printTaskDoneMessage(Task task) {
        return String.format(HORIZONTAL_LINE
                + "     Nice! I've marked this task as not done:%n"
                + "       %s%n"
                + HORIZONTAL_LINE,
                    task);
    }

    /**
     * Returns a message reflecting the successful edit of a Task.
     */
    public String printTaskEditMessage(int taskIdToEdit, Task task) {
        return String.format(HORIZONTAL_LINE
                + "     Nice! I've edited task %d:"
                + "       %s%n"
                + HORIZONTAL_LINE,
                    taskIdToEdit, task);
    }

    /**
     * Returns a numbered list of Tasks from the provided TaskList.
     */
    public String printTaskList(TaskList taskList) {
        return String.format(HORIZONTAL_LINE
                + "     Here are the tasks in your list:%n"
                + "%s"
                + HORIZONTAL_LINE,
                    taskList);
    }

    /**
     * Returns a numbered list of matching Tasks (from a FindTask Command operation)
     *
     * @param matchingTaskList the TaskList to be printed
     */
    public String printMatchingTaskList(TaskList matchingTaskList) {
        return String.format(HORIZONTAL_LINE
                        + "     Here are the matching tasks in your list:%n"
                        + "%s"
                        + HORIZONTAL_LINE,
                matchingTaskList);
    }

    /**
     * Returns the exit message.
     */
    public String printExitMessage() {
        return String.format(HORIZONTAL_LINE
                + "     List saved! Goodbye!%n"
                + HORIZONTAL_LINE);
    }
}
