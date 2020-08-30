package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to Tasks that contains the keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Class constructor.
     *
     * @param keyword The key word to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Performs an action of searching the tasks by key word.
     * Prints out the list of task containing the keyword.
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui for Duke.
     * @param storage The Storage for Duke.
     * @return True because the program will continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWindow(tasks.getTaskWithKeyword(keyword));
        return true;
    }
}
