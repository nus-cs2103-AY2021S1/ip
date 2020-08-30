package duke.command;

import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * Represents a Command given by the user to list all Tasks whose date is the current date (today).
 */
public class TodayCommand extends Command {
    /**
     * Prints all lists in the TaskList, whose date is the current date (today).
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        Ui.todayMessage(list.getTaskList());
    }
}
