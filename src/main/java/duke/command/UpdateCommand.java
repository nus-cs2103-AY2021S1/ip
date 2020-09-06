package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidUpdateException;
import duke.exception.DukeLoadingErrorException;

public abstract class UpdateCommand extends Command {
    /**
     * The number of the task to be updated.
     */
    protected int taskNo;

    /**
     * Abstract constructor for UpdateCommand.
     *
     * @param taskNo number of the task to be updated.
     */
    protected UpdateCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes a updateTask operation.
     *
     * @param taskList TaskList that the task is edited from.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     * @throws DukeInvalidUpdateException when attempting to update an invalid detail.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException,
            DukeInvalidUpdateException;
}
