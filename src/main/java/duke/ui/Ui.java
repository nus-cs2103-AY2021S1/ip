package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Interacts with the user.
 */
public class Ui {
    private static String horizontalLine = "_______________________________________";

    /**
     * Prints the welcome message
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        String greeting = "Hello human, I'm Duke the supporting chatbot\n"
                + "What can I do for you?";
        return logo + "\n" + horizontalLine + "\n" + greeting;
    }

    /**
     * Shows the items in the task.
     * @param tasks The list of items to be shown
     */
    public String showTasks(TaskList tasks) {
        String lazyHumanBash = "You have nothing in your list. Find something to do you human!";
        String showTasksMsg = "Here are the task(s) in your list:";
        if (tasks.size() == 0) {
            return lazyHumanBash;
        } else {
            return showTasksMsg + "\n" + tasks.toString();
        }
    }

    /**
     * Notifies the user that the file cannot be loaded.
     */
    public void showFileLoadingError() {
        System.out.println("Data cannot be loaded");
    }

    /**
     * Prints out any error message that passed in
     * @param errorMessage The error message to be printed
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Says goodbye to the user.
     */
    public String showGoodbye() {
        return "Bye human. See you again soon!";
    }

    /**
     * Prints out the number of item in the task.
     * @param tasks The task
     */
    public String taskReport(TaskList tasks) {
        return "You have " + tasks.size() + " task(s) in your list";
    }

    /**
     * Notifies the user after successfully deleting a task.
     * @param task The deleted task
     * @param tasks The remaining task list
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return "Noted. I've deleted this task:\n"
                + task.toString() + "\n"
                + taskReport(tasks);
    }

    /**
     * Notifies the user after successfully marking a task as done.
     * @param task The task being marked as done
     */
    public String showMarkAsDoneTask(Task task) {
        return "Good job bud! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Notifies the user whenever the system cannot understand the input command.
     */
    public String showUnrecognizedCommandMessage() {
        return "I don't understand a single word you say, human!\n"
                + "Speak robot language pls";
    }

    /**
     * Notifies the user after successfully add a task into a task list.
     * @param task The added task
     * @param tasks The task list
     */
    public String showAddSuccessful(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + taskReport(tasks);
    }

    /**
     * Show the user the result of a find command.
     * @param tasks The list of tasks that fulfill the requirement
     * @return The message  results
     */
    public String showFindResult(TaskList tasks) {
        return "Here are the matching task(s) in your list: \n" + tasks.toString();
    }

    /**
     * Show this message when undoing a command successfully
     * @param tasks the new task list after undoing the command
     * @return The message noticing that the user has successfully undo a command
     */
    public String showUndoSuccessfulMsg(TaskList tasks) {
        return "You have undone successfully. Here is your current list:\n" + tasks.toString();
    }

    /**
     * Show this message when cannot undo anymore
     * @return The message noticing that the user cannot undo anymore
     */
    public String showUnableToUndoMessage() {
        return "You cannot undo anymore.";
    }

    /**
     * Show this message when a task is cloned
     * @param task The cloned task
     * @return The message noticing that the task has been cloned
     */
    public String showCloneSuccess(Task task) {
        return "I have cloned this task:\n" + task.toString();
    }

    /**
     * Show this message after updating a task
     * @param index The positon of the updated task
     * @param task The updated version of the task
     * @return The message noticing that the task has been updated
     */
    public String showUpdateComplete(int index, Task task) {
        return "The task at position " + index + " has been updated to this:\n" + task.toString();
    }
}
