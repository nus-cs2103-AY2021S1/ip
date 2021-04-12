package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the logic for finding tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand.
     *
     * @param searchTerm Search term to use.
     */
    public FindCommand(String searchTerm) {
        super(searchTerm);
    }

    /**
     * Performs a search on all the task descriptions.
     * The search is fuzzy.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return A list of tasks whose description contains the search term
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> results = taskList.findTasks(args);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            if (i != 0) {
                string.append("\n");
            }
            string.append(results.get(i).toString());
        }
        return string.toString();
    }
}
