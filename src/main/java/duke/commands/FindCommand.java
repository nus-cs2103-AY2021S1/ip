package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;

/**
 * Represents the Command to find tasks that matches a provided keyword.
 */
public class FindCommand implements Command {

    private final String keyword;

    /**
     * Initializes a FindCommand.
     *
     * @param keyword The search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the list of tasks that matches the keyword.
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @return The string representation of the list of tasks that matches the keyword.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        ArrayList<String> findTasksRepr = tasks.find(keyword);
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String s : findTasksRepr) {
            sb.append(i).append(": ").append(s).append('\n');
            i++;
        }
        return sb.toString();
    }
}
