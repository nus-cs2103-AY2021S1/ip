package duke.fxcommand;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * Finds the list of tasks that matches the keyword, then prints it to the console.
     *
     * @param ui      The ui of Duke.
     * @param storage The storage object.
     * @param tasks   The taskList.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
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
