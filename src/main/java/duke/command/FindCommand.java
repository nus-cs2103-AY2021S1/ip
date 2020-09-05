package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The find command allows user to search for tasks with descriptions that
 * contain the query string. Currently, the implementation allows for all
 * containment including substrings. For example, the query "end" will bring
 * up descriptions like "friend" and "send message" instead of matching full strings.
 *
 * Future implementation can force descriptions to be split and match exact elements.
 */
public class FindCommand implements Command {

    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * This command execution is summarized as:
     *
     *     1. Create a list to contain task numbers
     *     2. Scan the task list
     *         - Add index to the list if task matches query
     *     3. Calls task list to get the filtered list message
     *
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = tasks.getFilteredListMessage(query);
        ui.sendMessage(msg);
        return msg;
    }
}
