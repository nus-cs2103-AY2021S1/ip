package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that will find matching tasks in the last list based on a specified keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a new Find Command with the specified keyword.
     *
     * @param keyword the specified keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks in the task list which contain the keyword and displays them.
     *
     * @param tasks the task list where the tasks which contain the keyword can be found.
     * @param ui the ui that will display a message when the tasks have been found.
     * @param storage unused.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindMessage(keyword, tasks);
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
