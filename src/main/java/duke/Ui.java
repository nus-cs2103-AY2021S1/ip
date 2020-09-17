package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /**
     * Prints a greeting to the user.
     */
    public static String printWelcome() {
        return "Hey I'm Ferb!\nHow can I help you today?";
    }

    /**
     * Prints an exit message to the user.
     */
    public static String printExit() {
        return "See ya later alligator :-)";
    }

    /**
     * Prints all the tasks in the task list.
     *
     * @param taskList is the list of tasks to be printed.
     */
    public static String printList(TaskList taskList) {
        return "Here are your tasks:" + taskList;
    }

    /**
     * Prints a message noting that a task is done.
     *
     * @param task is the task marked as done.
     */
    public static String printDoneTask(Task task) {
        return "Awesome! You've done the following:\n  " + task;
    }

    /**
     * Prints a message noting that a task is deleted from the task list.
     *
     * @param task is the task that was deleted.
     * @param taskList is the list of tasks in which the specified task was deleted from.
     */
    public static String printDeleteTask(Task task, TaskList taskList) {
        return "Alright! You don't have to worry about this:\n  " + task + getNumOfTasks(taskList);
    }

    /**
     * Prints a message noting that a task is added into the task list.
     *
     * @param task is the task that was added.
     * @param taskList is the list in which the task was added to.
     */
    public static String printAddTask(Task task, TaskList taskList) {
        return "Got it! I've added this task:\n  " + task + getNumOfTasks(taskList);
    }

    /**
     * Prints a numbered list of the tasks with the specified keyword.
     *
     * @param keyword is the keyword used to find the tasks.
     * @param taskList is the task list to find the tasks with keyword from.
     */
    public static String printFindTasks(String keyword, TaskList taskList) {
        return "Hey look! I found matching tasks:" + taskList.getMatchingTasks(keyword);
    }

    /**
     * Prints a message noting that a duplicate task is detected.
     *
     * @return the duplicate message.
     */
    public static String printDuplicateTask() {
        return "Oh no! Duplicate task detected.";
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
            msg = String.format("\n*You have %d task now*", numOfTasks);
        } else {
            msg = String.format("\n*You have %d tasks now*", numOfTasks);
        }
        return msg;
    }

    /**
     * Prints an error message should there be an exception.
     *
     * @param e is the exception in which we get the message from.
     */
    public static String showError(Exception e) {
        return (e.getMessage());
    }
}
