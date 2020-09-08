package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;
import duke.exception.DukeInvalidUpdateException;
import duke.exception.DukeLoadingErrorException;
import duke.task.Task;

public class UpdateDescCommand extends UpdateCommand {

    /**
     * The new description of the task.
     */
    private String description;

    /**
     * Creates a new instance of a UpdateDescCommand with attributes defined
     * in the parameters.
     *
     * @param taskNo number of the task to be updated.
     * @param description new description of the task.
     */
    public UpdateDescCommand(int taskNo, String description) {
        super(taskNo);
        this.description = description;
    }

    /**
     * Executes a updateDesc operation.
     *
     * @param taskList TaskList that the task is edited from.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     * @throws DukeInvalidUpdateException when attempting to update an invalid detail.
     * @throws DukeInvalidIndexException If invalid index is given.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeLoadingErrorException, DukeInvalidIndexException {
        try {
            Task updatedTask = taskList.updateTaskDesc(super.taskNo, this.description);
            storage.saveTasks(taskList);
            String uiMessage = String.format(
                    "Noted. I've updated this task:\n%s\n%s",
                    updatedTask.toString(),
                    taskList.getTaskSizeMessage());
            return ui.print(uiMessage);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }

    }
}
