package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an action to list out the Task from TaskList.
 */
public class ListCommand extends Command {

    /**
     * Constructs a <code>ListCommand</code> object.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Lists out the Task in TaskList.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Ui ui, Storage storage) {
        String responseMessage = tasks.toString();
        boolean shouldExit = getIsExit();
        assert !shouldExit : "shouldExit should be false";
        return new CommandResponse(responseMessage, shouldExit);
    }
}
