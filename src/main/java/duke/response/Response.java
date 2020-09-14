package duke.response;

import duke.task.TaskList;
import duke.task.Task;

/**
 * User interface which deals with user interactions.
 * Also prints relevant error messages and responses.
 */
public class Response {
    /**
     * Represents two spaces.
     */
    public static final String TWO_INDENT = "  ";

    /**
     * Returns the Bye message.
     *
     * @return Bye message.
     */
    public static String printBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the introduction message.
     * Messages are printed in a chat window.
     *
     * @return Introduction message.
     */
    public static String intro() {
        return ("Hello! I'm Doraemon!\n"
                + "What do you meow?" + '\n');
    }

    /**
     * Returns the task marked as done in a chat window.
     *
     * @param task The task that is marked as done.
     */
    public String showMarkDone(Task task) {
        return ("Nice! I've marked this task as done: \n"
                + TWO_INDENT + task);
    }

    /**
     * Returns the added task in a chat window.
     * It also tells the number of items in the list after addition.
     *
     * @param addedTask The task that is added.
     * @param listSize The number of items in the list.
     */
    public String showAdded(Task addedTask, int listSize) {
        return ("Got it. I've added this task:\n"
                + TWO_INDENT + addedTask + "\n"
                + String.format("Now you have %d tasks in the in the list", listSize));
    }

    /**
     * Returns the deleted task in a chat window.
     * It also tells the number of items in the list after deletion.
     *
     * @param deletedTask The task that is deleted.
     * @param listSize The number of items in the list.
     */
    public String showDeleted(Task deletedTask, int listSize) {
        return ("Noted. I've removed this task: \n"
                + TWO_INDENT + deletedTask
                + String.format("\nNow you have %d tasks in the list.", listSize));
    }

    /**
     * Returns the loading error message in a chat window.
     *
     * @return Loading error message.
     */
    public String showLoadingError() {
        return ("Oops, error in loading the tasks! "
                + "Please check the duke.txt file");
    }

    /**
     * Returns the tasks in the list as a String in a chat window.
     *
     * @param tasks The TaskList object with the list of tasks.
     * @return String representation of Tasks in the list.
     */
    public String showList(TaskList tasks) {
        return "Currently, you have: \n" + tasks.getListAsString();
    }

    /**
     * Prints the error message in a chat window.
     *
     * @param e The error in which message is printed.
     * @return Error message of e.
     */
    public String showError(Exception e) {
        return (e.getMessage());
    }

}
