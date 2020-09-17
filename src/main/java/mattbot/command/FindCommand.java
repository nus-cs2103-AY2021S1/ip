package mattbot.command;

import java.util.ArrayList;

import mattbot.errors.ErrorExceptions;
import mattbot.tasks.TaskManager;
import mattbot.tasks.task;


/**
 * Represents the find command that searches for task by date.
 */
public class FindCommand extends Command {
    /**
     * Runs the search function by looking through all the tasks stored and checking the date.
     * Returns the filtered list.
     *
     * @param name
     * @return String filtered list.
     * @throws ErrorExceptions
     */
    public static String execute2(String name) {
        ArrayList<task> clone = TaskManager.getStore();
        int count = 1;
        String result = "";
        result = result + "Here are your tasks with this keywords!";
        for (task i : clone) {
            if (i.getTaskName().contains(name)) {
                result = result + System.lineSeparator();
                result = result + "    " + count + ". " + TaskManager.read(i);
                count++;
            }
        }
        return result;
    }
}
