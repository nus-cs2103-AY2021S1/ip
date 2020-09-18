package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.sort.SortBy;
import duke.sort.SortByDate;
import duke.sort.SortByName;

/**
 * Represents a command to sort the tasks the user has.
 */
public class SortCommand extends Command {

    private final SortBy sortBy;

    /**
     * Instantiates a SortCommand with the sort criteria.
     * @param sortBy the criteria for sorting
     */
    public SortCommand(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Returns false since this is not an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by sorting the tasks according to a criteria given by user
     * and displaying the newly sorted task list to user. The new task list sequence
     * is also saved in storage.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage updating on disk
     * @return A string containing the newly sorted task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (sortBy == SortBy.NAME) {
                taskList.sort(new SortByName());
            } else {
                taskList.sort(new SortByDate());
            }
            storage.saveData(taskList);
            return ui.listTasks(taskList);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
