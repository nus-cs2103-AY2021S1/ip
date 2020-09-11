package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountException;

/**
 * Represents an edit command.
 */
public abstract class EditCommand extends Command {
    protected Integer taskIndex;

    /**
     * Instantiates a new edit command.
     *
     * @param taskIndex Index of task edited.
     */
    public EditCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public abstract String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountException;
}
