package duke;

import java.time.LocalDate;

import duke.task.Task;

/**
 * Deals with coming up with responses to the user.
 */
public class Ui {
    /** Divider line enclosing all responses. */
    private static final String DIVIDER_LINE = "________________________________________________\n";

    /**
     * Returns a response to the user, enclosed within two divider lines.
     * @param response The response to be printed.
     * @return The response within two divider lines.
     */
    public static String respondWith(String response) {
        return DIVIDER_LINE + response + "\n" + DIVIDER_LINE;
    }

    /**
     * Returns a welcome message to the user.
     * @return The welcome message to the user.
     */
    public static String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + respondWith("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * Returns an exit message to the user.
     * @return The exit message to the user.
     */
    public static String respondExit() {
        return respondWith("Bye. Hope to see you again soon!");
    }

    /**
     * Returns all the tasks in the specified Task list.
     * @param taskList The task list that contains the list of tasks to be printed.
     * @return The list of tasks from the specified task list.
     */
    public static String respondList(TaskList taskList) {
        return respondWith("Here are the tasks in your list:" + taskList);
    }

    /**
     * Returns a message to the user indicating that the specified task has been added to the
     * specified task list.
     * @param task The task that was added.
     * @param taskList The task list that the task was added to.
     * @return An acknowledgement that the task has been added.
     */
    public static String respondAddTask(Task task, TaskList taskList) {
        return respondWith("Got it. I've added this task: \n\t" + task + "\n" + getHowManyTasks(taskList));
    }

    /**
     * Returns a message to the user indicating that the specified task has been deleted from the
     * specified task list.
     * @param task The task that was deleted.
     * @param taskList The task list that the task was deleted from.
     * @return An acknowledgement that the task has been removed.
     */
    public static String respondDeleteTask(Task task, TaskList taskList) {
        return respondWith("Noted. I've removed this task: \n\t" + task + "\n" + getHowManyTasks(taskList));
    }

    /**
     * Returns a message to the user indicating that the specified task has been marked done.
     * @param task The task that was marked done.
     * @return An acknowledgement that the task has been marked as done.
     */
    public static String respondDoneTask(Task task) {
        return respondWith("Nice! I've marked this task as done:\n\t" + task);
    }

    /**
     * Prints the tasks that occur on the specified date.
     * @param taskList The task list to get the tasks from.
     * @param localDate The date that you want to get the tasks for.
     * @return The tasks that occur on the specified date.
     */
    public static String respondViewTasks(TaskList taskList, LocalDate localDate) {
        TaskList taskListOnDate = taskList.getTaskListOnDate(localDate);
        if (taskListOnDate.getNumberOfTasks() == 0) {
            return respondWith("You do not have any tasks on this date.");
        }
        return respondWith("Here are the tasks on this date:" + taskListOnDate);
    }

    /**
     * Prints the tasks that contain the specified keyword.
     * @param taskList The task list to find the tasks in.
     * @param keyword The string that the tasks you are finding should contain.
     * @return The tasks that contain the keyword.
     */
    public static String respondFindTasks(TaskList taskList, String keyword) {
        TaskList tasksWithKeyword = taskList.getTasksWithKeyword(keyword);
        return respondWith("Here are the matching tasks in your list:" + tasksWithKeyword);
    }

    /**
     * Gets the number of tasks in the specified task list.
     * @param taskList The task list to get the number of tasks from.
     * @return A string indicating the number of tasks in the specified task list.
     */
    public static String getHowManyTasks(TaskList taskList) {
        int numberOfTasks = taskList.getNumberOfTasks();
        if (numberOfTasks == 0) {
            return "You do not have any tasks in your list!";
        } else if (numberOfTasks == 1) {
            return String.format("Now you have %d task in the list.", numberOfTasks);
        } else {
            return String.format("Now you have %d tasks in the list.", numberOfTasks);
        }
    }

    /**
     * Returns the error message of the exception.
     * @param e The exception to get the message from.
     * @return The error message of the exception.
     */
    public static String respondError(Exception e) {
        return respondWith(e.getMessage());
    }
}
