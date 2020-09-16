package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Represents a Command given by the user to list all Tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints all lists in the TaskList.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String containing a user message containing all the user's Tasks.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        return DukeMessages.printListMessage(list.getTaskList());
    }
}
