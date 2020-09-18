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
     * Returns a String that represents if the given save file has loaded successfully
     * and the tasks found in the save file.
     *
     * @param tasks a TaskList containing the tasks in the save file.
     * @return a String that represents the tasks in the TaskList.
     */

    public String showLoaded(TaskList tasks) {
        StringBuilder ret = new StringBuilder("Your current tasks are: \n");
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
     * Returns a String that represents if a Task has successfully been added to the
     * TaskList and the current number of Tasks in it.
     *
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was added to the TaskList.
     * @return a String with the added Task and the current number of Tasks in the list.
     */

    public String showAdd(TaskList tasks, Task task) {
        return String.format("Noted. I've added this task:\n%s\n"
                + "You now have %d %s in your list.", task.toString(),
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Returns a String that represents if a Task has been marked as complete.
     *
     * @param task the Task that was marked as complete.
     * @return a String denoting that the specified Task has been marked.
     */

    public String showDone(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n",
                task.toString());
    }

    /**
     * Returns a String that represents if a Task has successfully been deleted
     * from the TaskList and the current number of Tasks in it.
     *
     * @param tasks the TaskList associated with the current Duke object.
     * @param task the Task that was deleted from the TaskList.
     * @return a String denoting that the specified Task has been removed.
     */

    public String showDelete(TaskList tasks, Task task) {
        return String.format("Noted. I've removed this task:\n%s\n"
                + "You now have %d %s in your list.", task.toString(),
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Returns a String that represents if a DukeException has been caught,
     * printing the corresponding error message.
     *
     * @param e a DukeException containing the error message.
     * @return a String with the error message.
     */

    public String showError(OliviaException e) {
        return String.format("%s\n", e.getMessage());
    }

    /**
     * Returns a String that represents list of matching Tasks given a keyword
     * to search for.
     *
     * @param list a list of matching Tasks.
     * @return a String of the list of tasks.
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
     * Returns a String that represents the current Tasks found in the TaskList.
     *
     * @param list the TaskList associated with the current Duke object.
     * @return a String of the list of tasks.
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
     * Returns a String that represents a welcome message for the user.
     *
     * @return a String of the welcome message.
     */

    public String welcome() {
        return "Hello! I'm Olivia!\nI am an interactive bot that helps you tracks the tasks you need to do.\n";
    }

}
