package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;

/**
 * Represents the Command to list all tasks in the taskList.
 */
public class ListCommand implements Command {


    /**
     * Gets a string representation of the taskList.
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @return The string representation of the taskList.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        ArrayList<String> tasksListRepr = tasks.getListRepr();
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String s : tasksListRepr) {
            sb.append(i).append(": ").append(s).append('\n');
            i++;
        }
        return sb.toString();
    }
}
