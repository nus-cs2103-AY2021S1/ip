package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Represents a Command given by the user to find tasks based on a search string provided.
 */
public class FindCommand extends Command {
    private String matchString;

    /**
     * Creates a FindCommand.
     * @param matchString A String provided by the user, to search all tasks against.
     */
    public FindCommand(String matchString) {
        this.matchString = matchString;
    }

    /**
     * Executes the FindCommand.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String that contains a message to user, detailing the matching tasks.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        return DukeMessages.printFindMessage(list.findTasks(this.matchString));
    }
}
