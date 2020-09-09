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
     * Finds the tasks in the task list that contain the keyword.
     *
     * @param tasks the task list.
     * @param ui the ui that will generate the find message.
     * @param storage the storage where the tasks will be saved.
     * @return the ui-generated message.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        return ui.generateFindMessage(keyword, tasks);
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
