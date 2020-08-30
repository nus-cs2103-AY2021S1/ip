package Command;

import java.util.ArrayList;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;


/**
 * Represents the find command that searches for task by date.
 */
public class FindCommand extends Command {
    /**
     * Runs the search function by looking through all the tasks stored and checking the date.
     * @param name
     * @throws ErrorExceptions
     */
    public static void execute(String name) {
        ArrayList<task> clone = TaskManager.getStore();
        int count = 1;
        System.out.println("Here are your tasks with this keywords!");
        for (task i : clone) {
            if (i.getTaskName().contains(name)) {
                System.out.println("    " + count + ". " + TaskManager.read(i));
                count++;
            }
        }
    }
}
