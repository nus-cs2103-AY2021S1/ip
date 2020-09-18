package cartona.ui;

import cartona.task.Task;
import cartona.task.TaskList;

/**
 * The Ui class generates text strings that represent messages from the program.
 *
 * @author Jaya Rengam
 */
public class Ui {

    /**
     * Returns the given error message to the console.
     * @param errorMessage
     */
    public String getErrorMessageFormatted(String errorMessage) {
        assert !(errorMessage.equals("")) : "Error message cannot be empty";
        return String.format(" %s%n", errorMessage);
    }

    /**
     * Returns the startup message.
     */
    public String getWelcomeMessageFormatted() {
        return String.format(" Hello! I'm Cartona.%n"
                        + " What can I do for you?");
    }

    /**
     * Returns a message reflecting the successful adding of a Task.
     * @param task The task that was added.
     * @param taskListSize The current size of the TaskList.
     */
    public String printTaskAddingMessage(Task task, int taskListSize) {
        return String.format(" Got it. I've added this task:%n       %s%n     "
                            + "Now you have %d tasks in the list.",
                        task, taskListSize);
    }

    /**
     * Returns a message reflecting the successful deletion of a Task.
     * @param task The task that was deleted.
     * @param taskListSize The current size of the TaskList.
     */
    public String printTaskDeletionMessage(Task task, int taskListSize) {
        return String.format(" Noted. I've removed this task:%n       %s%n"
                + " Now you have %d tasks in the list.%n",
                    task, taskListSize);
    }

    /**
     * Returns a message reflecting the successful completion of a Task.
     * @param task The task that was marked as completed.
     */
    public String printTaskDoneMessage(Task task) {
        return String.format(" Nice! I've marked this task as not done:%n"
                + "   %s%n",
                    task);
    }

    /**
     * Returns a message reflecting the successful edit of a Task.
     */
    public String printTaskEditMessage(int taskIdToEdit, Task task) {
        return String.format(" Nice! I've edited task %d:"
                + "   %s%n",
                    taskIdToEdit, task);
    }

    /**
     * Returns a numbered list of Tasks from the provided TaskList.
     */
    public String printTaskList(TaskList taskList) {
        return String.format(" Here are the tasks in your list:%n"
                            + "%s",
                    taskList);
    }

    /**
     * Returns a numbered list of matching Tasks (from a FindTask Command operation)
     *
     * @param matchingTaskList the TaskList to be printed
     */
    public String printMatchingTaskList(TaskList matchingTaskList) {
        return String.format(" Here are the matching tasks in your list:%n"
                            + "%s",
                matchingTaskList);
    }

    /**
     * Returns the exit message.
     */
    public String printExitMessage() {
        return String.format(" List saved! Goodbye!%n");
    }
}
