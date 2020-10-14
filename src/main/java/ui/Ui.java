package ui;

import magic.Format;
import magic.MyString;
import task.Task;
import task.TaskList;

public class Ui {

    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        return MyString.WELCOME;
    }

    /**
     * Prints goodbye message
     */
    public static String goodbye() {
        return MyString.GOODBYE;
    }

    /**
     * Prints header and task added.
     *
     * @param task Task to print
     */
    public static String showAddTask(Task task, int size) {
        String totalTasksString = showTotalTask(size);
        return String.format(Format.ADD_TASK_HEADER,
                task.toString(), totalTasksString);
    }

    /**
     * Prints header and task done.
     *
     * @param task Task to print
     */
    public static String showDoneTask(Task task) {
        return String.format(Format.DONE_TASK_HEADER, task);
    }

    /**
     * Prints header and task removed.
     *
     * @param task Task to print
     */
    public static String showRemovedTask(Task task, int size) {
        String totalTasksString = showTotalTask(size);
        return String.format(Format.REMOVE_TASK_HEADER,
                task.toString(), totalTasksString);
    }

    /**
     * Prints header and number of tasks in list.
     *
     * @param size number of tasks in the list.
     */
    public static String showTotalTask(int size) {
        assert size >= 0 : MyString.ERROR_INVALID_SIZE;
        if (size == 0) {
            return Format.TOTAL_TASK_HEADER_EMPTY;
        } else {
            String plural = size != 1 ? "tasks" : "task";
            return String.format(Format.TOTAL_TASK_HEADER, size, plural);
        }
    }

    /**
     * Format result of Find task.
     *
     * @param result String of find task.
     * @return Formatted string result.
     */
    public static String showFindTask(TaskList result) {
        String list = result.listToString();
        if (list.isEmpty()) {
            return Format.FIND_TASK_HEADER_EMPTY;
        }
        return String.format(Format.FIND_TASK_HEADER,
                result.listToString());
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
        return String.format(Format.TAGGED_TASK_HEADER,
                task.toString());
    }

    /**
     * Prints Header and list of tasks in Task List.
     * @param taskList Task List to print
     * @return  Formatted String result.
     */
    public static String showListTask(TaskList taskList) {
        String list = taskList.listToString();
        if (list.isEmpty()) {
            return Format.LIST_TASK_HEADER_EMPTY;
        }

        return String.format(Format.LIST_TASK_HEADER,
                taskList.listToString());
    }
}
