package olivia.ui;

import java.util.List;

import olivia.resource.TaskList;
import olivia.task.Task;
import olivia.util.OliviaException;

/**
 * Ui class that handles the user interaction component of the bot.
 */

public class Ui {

    /**
     * Prints to user if the given save file has loaded successfully and the tasks found
     * in the save file.
     *
     * @param tasks a TaskList containing the tasks in the save file.
     */

    public String showLoaded(TaskList tasks) {
        StringBuilder ret = new StringBuilder("A save file has been found and loaded!\n"
                + "Your current tasks are: \n");
        int i = 0;
        if (tasks.size() == 0) {
            ret.append("... empty! Good work!\n");
        } else {
            while (tasks.size() > i) {
                ret.append(String.format("%d. %s\n", ++i, tasks.getTask(i).toString()));
            }
        }
        return ret.toString();
    }

    /**
     * Prints to user if a Task has successfully been added to the TaskList and
     * the current number of Tasks in it.
     *
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was added to the TaskList.
     */

    public String showAdd(TaskList tasks, Task task) {
        return String.format("Noted. I've added this task:\n%s\n"
                + "You now have %d %s in your list.", task.toString(),
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints to user if a Task has been marked as complete.
     *
     * @param task the Task that was marked as complete.
     */

    public String showDone(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n",
                task.toString());
    }

    /**
     * Prints to user if a Task has successfully been deleted from the TaskList and
     * the current number of Tasks in it.
     *
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     */

    public String showDelete(TaskList tasks, Task task) {
        return String.format("Noted. I've removed this task:\n%s\n"
                + "You now have %d %s in your list.", task.toString(),
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints to user if a DukeException has been caught, printing the corresponding
     * error message.
     *
     * @param e a DukeException containing the error message
     */

    public String showError(OliviaException e) {
        return String.format("%s\n", e.getMessage());
    }

    /**
     * Prints to user a list of matching Tasks given a keyword to search for
     *
     * @param list a list of matching Tasks
     */

    public String showFind(List<Task> list) {
        int i = 0;
        StringBuilder ret = new StringBuilder("Here are the matching tasks in your list:\n");
        while (list.size() > i) {
            ret.append(String.format("%d. %s\n", ++i, list.get(i - 1).toString()));
        }
        return ret.toString();
    }

    /**
     * Prints to user the current Tasks found in the TaskList.
     *
     * @param list the TaskList associated with the current Duke object
     */

    public String showList(TaskList list) {
        int i = 0;
        StringBuilder ret = new StringBuilder("Here are the tasks in your list:\n");
        while (list.size() > i) {
            ret.append(String.format("%d. %s\n", ++i, list.getTask(i).toString()));
        }
        return ret.toString();
    }

    public String showUpdate(Task task) {
        return String.format("I have updated this task:\n%s", task.toString());
    }

    /**
     * Prints a welcome message for the user.
     */

    public String welcome() {
        return "Hello! I'm Olivia!\nI am an interactive bot that helps you tracks the tasks you need to do.\n";
    }

}
