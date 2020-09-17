package ui;

import task.Task;
import task.TaskList;

public class Ui {

    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Prints goodbye message
     */
    public static String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints header and task added.
     *
     * @param task Task to print
     */
    public static String showAddTask(Task task, int size) {
        String totalTasksString = showTotalTasks(size);
        return String.format("Go it. I've added this task:\n%s\n%s",
                task.toString(), totalTasksString);
    }

    /**
     * Prints header and task done.
     *
     * @param task Task to print
     */
    public static String showDoneTask(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Prints header and task removed.
     *
     * @param task Task to print
     */
    public static String showRemovedTask(Task task, int size) {
        String totalTasksString = showTotalTasks(size);
        return String.format("Noted. I've removed this task:\n%s\n%s",
                task.toString(), totalTasksString);
    }

    /**
     * Prints header and number of tasks in list.
     *
     * @param size number of tasks in the list.
     */
    public static String showTotalTasks(int size) {
        assert size >= 0 : "Invalid Size";
        if (size == 0) {
            return "TaskList is empty! Please add a task first.";
        } else {
            String plural = size != 1 ? "tasks" : "task";
            return String.format("Now you have %d %s in the list.", size, plural);
        }
    }

    /**
     * Format result of Find task.
     *
     * @param result String of find task.
     * @return Formatted string result.
     */
    public static String showFindTask(TaskList result) {
        return String.format("Here are the matching tasks in your list:\n%s", result.listToString());
    }

    /**
     * Prints error message.
     *
     * @param e Exception to print.
     */
    public static String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints Header and task Tagged.
     * @param task Task that has been tagged.
     * @return Formatted String result.
     */
    public static String showTaggedTask(Task task) {
        return String.format("Noted. The following task has been tagged!\n%s",
                task.toString());
    }

    /**
     * Prints Header and list of tasks in Task List.
     * @param taskList Task List to print
     * @return  Formatted String result.
     */
    public static String showListTasks(TaskList taskList) {
        String list = taskList.listToString();
        if (!list.isEmpty()) {
            return String.format("No matching tasks found");
        }

        return String.format("Here are the tasks in your list:\n%s",
                taskList.listToString());
    }
}
