package duke;

import java.time.LocalDate;

import duke.task.Task;

/**
 * Deals with coming up with responses to the user.
 */
public class Ui {
    /** Logo of the Chatbot. */
    private static final String LOGO = "    _  \\     \\     __ __|    _  \\     _ _|    ___|   |    /\n"
                    + "   |     |   _  \\       |      |     |      |     |        '   /\n"
                    + "  ___/    ___  \\     |     __ <       |     |        .   \\\n"
                    + " _|     _/        _\\ _|    _|   \\_\\  ___|  \\____| _| \\_\\\n";

    /**
     * Returns a welcome message to the user.
     *
     * @return The welcome message to the user.
     */
    public static String greet() {
        return "Hello from\n"
                + LOGO
                + "Hello! This is Patrick\nWhat can I do for you?\n";
    }

    /**
     * Returns an exit message to the user.
     *
     * @return The exit message to the user.
     */
    public static String respondExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns all the tasks in the specified Task list.
     *
     * @param taskList The task list that contains the list of tasks to be printed.
     * @return The list of tasks from the specified task list, or none if there are no tasks in the task list.
     */
    public static String respondList(TaskList taskList) {
        if (taskList.getNumberOfTasks() == 0) {
            return "You currently do not have any tasks.";
        }
        return "Here are the tasks in your list: \n" + taskList;
    }

    /**
     * Returns a message to the user indicating that the specified task has been added to the
     * specified task list.
     *
     * @param task The task that was added.
     * @param taskList The task list that the task was added to.
     * @return An acknowledgement that the task has been added.
     */
    public static String respondAddTask(Task task, TaskList taskList) {
        return "Got it. I've added this task: \n\t" + task + "\n" + getHowManyTasks(taskList);
    }

    /**
     * Returns a message to the user indicating that the specified task has been deleted from the
     * specified task list.
     *
     * @param task The task that was deleted.
     * @param taskList The task list that the task was deleted from.
     * @return An acknowledgement that the task has been removed.
     */
    public static String respondDeleteTask(Task task, TaskList taskList) {
        return "Noted. I've removed this task: \n\t" + task + "\n" + getHowManyTasks(taskList);
    }

    /**
     * Returns a message to the user indicating that the specified task has been marked done.
     *
     * @param task The task that was marked done.
     * @return An acknowledgement that the task has been marked as done.
     */
    public static String respondDoneTask(Task task) {
        return "Nice! I've marked this task as done: \n\t" + task;
    }

    /**
     * Prints the tasks that occur on the specified date.
     *
     * @param taskList The task list to get the tasks from.
     * @param localDate The date that you want to get the tasks for.
     * @return The tasks that occur on the specified date, or none if there are no tasks on that date.
     */
    public static String respondViewTasks(TaskList taskList, LocalDate localDate) {
        TaskList taskListOnDate = taskList.getTaskListOnDate(localDate);
        if (taskListOnDate.getNumberOfTasks() == 0) {
            return "You do not have any tasks on this date.";
        }
        return "Here are the tasks on this date: \n" + taskListOnDate;
    }

    /**
     * Prints the tasks that contain the specified keyword.
     *
     * @param taskList The task list to find the tasks in.
     * @param keyword The string that the tasks you are finding should contain.
     * @return The tasks that contain the keyword, or none if there are no tasks that contain the keyword.
     */
    public static String respondFindTasks(TaskList taskList, String keyword) {
        TaskList tasksWithKeyword = taskList.getTasksWithKeyword(keyword);
        if (tasksWithKeyword.getNumberOfTasks() == 0) {
            return "You do not have any tasks that contain this keyword.";
        }
        return "Here are the matching tasks in your list: \n" + tasksWithKeyword;
    }

    /**
     * Gets the number of tasks in the specified task list.
     *
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
     *
     * @param e The exception to get the message from.
     * @return The error message of the exception.
     */
    public static String respondError(Exception e) {
        return e.getMessage();
    }
}
