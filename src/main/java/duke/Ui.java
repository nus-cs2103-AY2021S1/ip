package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private static final String DIVIDER_LINE = "__________________________________________________\n";

    /**
     * Prints a response to the user with 2 dividing lines.
     *
     * @param print is the response to the user with 2 dividing lines.
     * @return a string containing the response to the user.
     */
    public static String printWithDivider(String print) {
        return DIVIDER_LINE + print + "\n" + DIVIDER_LINE;
    }

    /**
     * Prints a greeting to the user.
     */
    public static String printWelcome() {
        return printWithDivider("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints an exit message to the user.
     */
    public static String printExit() {
        return printWithDivider("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the tasks in the task list.
     *
     * @param taskList is the list of tasks to be printed.
     */
    public static String printList(TaskList taskList) {
        return printWithDivider("Here are the tasks in your list:" + taskList);
    }

    /**
     * Prints a message noting that a task is done.
     *
     * @param task is the task marked as done.
     */
    public static String printDoneTask(Task task) {
        return printWithDivider("Nice! I've marked this task as done:\n\t" + task);
    }

    /**
     * Prints a message noting that a task is deleted from the task list.
     *
     * @param task is the task that was deleted.
     * @param taskList is the list of tasks in which the specified task was deleted from.
     */
    public static String printDeleteTask(Task task, TaskList taskList) {
        return printWithDivider("Noted. I've removed this task:\n\t" + task + getNumOfTasks(taskList));
    }

    /**
     * Prints a message noting that a task is added into the task list.
     *
     * @param task is the task that was added.
     * @param taskList is the list in which the task was added to.
     */
    public static String printAddTask(Task task, TaskList taskList) {
        return printWithDivider("Got it. I've added this task:\n\t" + task + getNumOfTasks(taskList));
    }

    /**
     * Prints a numbered list of the tasks with the specified keyword.
     *
     * @param keyword is the keyword used to find the tasks.
     * @param taskList is the task list to find the tasks with keyword from.
     */
    public static String printFindTasks(String keyword, TaskList taskList) {
        return printWithDivider("Here are the matching tasks in your list:" + taskList.getMatchingTasks(keyword));
    }

    /**
     * Prints a message noting that a duplicate task is detected.
     *
     * @return the duplicate message.
     */
    public static String printDuplicateTask() {
        return printWithDivider("Sorry!!! Duplicate detected.");
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @param taskList is the list to get the number of tasks from.
     * @return a message with the number of tasks in the list.
     */
    public static String getNumOfTasks(TaskList taskList) {
        int numOfTasks = taskList.getNumOfTasks();
        String msg = "";
        if (numOfTasks == 1) {
            msg = String.format("\nNow you have %d task in the list.", numOfTasks);
        } else {
            msg = String.format("\nNow you have %d tasks in the list.", numOfTasks);
        }
        return msg;
    }

    /**
     * Prints an error message should there be an exception.
     *
     * @param e is the exception in which we get the message from.
     */
    public static String showError(Exception e) {
        return printWithDivider(e.getMessage());
    }
}
