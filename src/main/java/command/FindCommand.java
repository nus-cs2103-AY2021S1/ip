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
     * @return String response by the application after executing the command.
     */
    public String execute(TaskList lst, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        // uses filter method defined in TaskList class
        List<Task> filteredTasks = lst.filter(query);
        result.append(ui.showFindStatement(filteredTasks.isEmpty()));
        for (Task task : filteredTasks) {
            int taskNum = lst.indexOf(task) + 1;
            result.append(ui.showTask(task, taskNum));
        }
        assert result.length() != 0 : "Response should not be empty";
        return result.toString();
    }
}
