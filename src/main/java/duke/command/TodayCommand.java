package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Represents a Command given by the user to list all Tasks whose date is the current date (today).
 */
public class TodayCommand extends Command {
    /**
     * Prints all lists in the TaskList, whose date is the current date (today).
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String containing a user message detailing all Tasks whose date is the current date.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        return DukeMessages.printTodayMessage(list.getTasksToday());
    }
}
