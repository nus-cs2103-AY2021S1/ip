package main.ui;

import main.task.Task;
import main.task.TaskList;

/**
 * Represents the ui of duke.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class Ui {

    /**
     * Returns the string bidding the user farewell.
     * @return the string bidding the user farewell.
     */
    public String printExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the greeting message.
     * @return the greeting message.
     */
    public String printGreeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Returns a string with all tasks listed
     * @param tasks the task list.
     * @return the string with all tasks listed.
     */
    public String printTaskList(TaskList tasks) {
        StringBuilder list = new StringBuilder();

        if (tasks.size() == 0) {
            return "There are no tasks yet!";
        }

        for (int i = 0; i < tasks.size(); i++) {
            list.append(String.format("     %d.%s\n", i + 1, tasks.get(i)));
        }

        return list.toString();
    }

    /**
     * Returns a string with all the tasks found via the find command.
     * @param tasks the tasks found via the find command.
     * @return a string with all the tasks found via the find command.
     */
    public String printFoundList(TaskList tasks) {
        StringBuilder foundList;

        if (tasks.size() == 0) {
            return "There are no tasks found!";
        } else {
            foundList = new StringBuilder("Here are the matching tasks in your list:\n");
        }

        for (int i = 0; i < tasks.size(); i++) {
            foundList.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }

        return foundList.toString();
    }

    /**
     * Returns a string indicating a task has been added successfully.
     * @param task the task added.
     * @param taskNum the number of tasks in the list.
     * @return the string indicating a task has been added successfully.
     */
    public String printAddSuccess(Task task, int taskNum) {
        boolean isSingular = taskNum == 1;
        return String.format("Got it. I've added this task:\n%s\n"
                        + "Now you have %d %s in the list.",
                task, taskNum, isSingular ? "task" : "tasks");
    }

    /**
     * Returns a string indicating a task has been marked as done.
     * @param task the task marked as done.
     * @return a string indicating a task has been marked as done.
     */
    public String printDoneSuccess(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Returns a string indicating a task has been removed successfully.
     * @param removed the task removed.
     * @param taskNum the number of tasks in the list.
     * @return a string indicating a task has been removed successfully.
     */
    public String printRemoveSuccess(Task removed, int taskNum) {
        boolean isSingular = taskNum == 1;
        return String.format("Noted. I've removed this task:\n%s\n"
                        + "Now you have %d %s in the list.",
                removed, taskNum, isSingular ? "task" : "tasks");
    }

    /**
     * Returns a string with a generic error message.
     * @return a string with a generic error message.
     */
    public String printError() {
        return "Seems like something went wrong!";
    }
}
