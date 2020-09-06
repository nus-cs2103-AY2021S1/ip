package main.ui;

import java.util.stream.IntStream;

import main.task.Task;
import main.task.TaskList;

/**
 * Represents the ui of stuff.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Ui {
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_GREETING = "Hello! I'm Stuff\n"
            + "What can I do for you?";
    private static final String MESSAGE_NO_TASKS = "There are no tasks yet!";
    private static final String MESSAGE_NO_TASKS_FOUND = "There are no tasks found!";
    private static final String MESSAGE_TASKS_FOUND = "Here are the matching tasks "
            + "in your list:\n";
    private static final String MESSAGE_ADD_SUCCESS = "Got it. I've added this task:\n"
            + "%s\nNow you have %d %s in the list.";
    private static final String MESSAGE_DONE_SUCCESS = "Nice! I've marked "
            + "this task as done:\n%s";
    private static final String MESSAGE_REMOVE_SUCCESS = "Noted. I've removed "
            + "this task:\n%s\nNow you have %d %s in the list.";
    private static final String MESSAGE_ERROR = "Seems like something went wrong!";

    /**
     * Returns the string bidding the user farewell.
     * @return the string bidding the user farewell.
     */
    public String printExitMessage() {
        return MESSAGE_EXIT;
    }

    /**
     * Returns the greeting message.
     * @return the greeting message.
     */
    public String printGreetingMessage() {
        return MESSAGE_GREETING;
    }

    /**
     * Returns a string with all tasks listed
     * @param tasks the task list.
     * @return the string with all tasks listed.
     */
    public String printTaskList(TaskList tasks) {
        boolean isEmptyList = tasks.size() == 0;

        if (isEmptyList) {
            return MESSAGE_NO_TASKS;
        }

        return IntStream.range(0, tasks.size())
                .mapToObj(i -> new StringBuilder(
                        String.format("%d. %s\n", i + 1, tasks.get(i))))
                .reduce(new StringBuilder(), StringBuilder::append)
                .toString();
    }

    /**
     * Returns a string with all the tasks found via the find command.
     * @param tasks the tasks found via the find command.
     * @return a string with all the tasks found via the find command.
     */
    public String printFoundList(TaskList tasks) {
        boolean isEmptyList = tasks.size() == 0;

        if (isEmptyList) {
            return MESSAGE_NO_TASKS_FOUND;
        }

        return IntStream.range(0, tasks.size())
                .mapToObj(i -> new StringBuilder(
                        String.format("%d. %s\n", i + 1, tasks.get(i))))
                .reduce(new StringBuilder(MESSAGE_TASKS_FOUND),
                        StringBuilder::append)
                .toString();
    }

    /**
     * Returns a string indicating a task has been added successfully.
     * @param task the task added.
     * @param taskNum the number of tasks in the list.
     * @return the string indicating a task has been added successfully.
     */
    public String printAddSuccess(Task task, int taskNum) {
        assert(taskNum >= 0);

        boolean isSingular = taskNum == 1;

        return String.format(MESSAGE_ADD_SUCCESS,
                task, taskNum, isSingular ? "task" : "tasks");
    }

    /**
     * Returns a string indicating a task has been marked as done.
     * @param task the task marked as done.
     * @return a string indicating a task has been marked as done.
     */
    public String printDoneSuccess(Task task) {
        return String.format(MESSAGE_DONE_SUCCESS, task);
    }

    /**
     * Returns a string indicating a task has been removed successfully.
     * @param removed the task removed.
     * @param taskNum the number of tasks in the list.
     * @return a string indicating a task has been removed successfully.
     */
    public String printRemoveSuccess(Task removed, int taskNum) {
        assert(taskNum >= 0);

        boolean isSingular = taskNum == 1;

        return String.format(MESSAGE_REMOVE_SUCCESS,
                removed, taskNum, isSingular ? "task" : "tasks");
    }

    /**
     * Returns a string with a generic error message.
     * @return a string with a generic error message.
     */
    public String printErrorMessage() {
        return MESSAGE_ERROR;
    }
}
