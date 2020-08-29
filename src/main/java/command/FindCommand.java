package command;

import java.util.List;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;


/**
 * Represents the find command. The find command searches for tasks based on the query and lists them.
 */
public class FindCommand extends Command {
    /**
     * Query of the find command.
     */
    private final String query;

    /**
     * Creates a new Find command.
     *
     * @param query Query for the find command to search for.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command. The execution involves finding tasks based on the query and listing out the search
     * results.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     */
    public void execute(TaskList lst, Ui ui, Storage storage) {
        List<Task> filteredTasks = lst.filter(query);
        ui.showFindStatement(filteredTasks.size() <= 0);
        for (int i = 0; i < filteredTasks.size(); i++) {
            int num = i + 1;
            ui.showTask(filteredTasks.get(i), num);
        }
        ui.showLine();
    }
}
