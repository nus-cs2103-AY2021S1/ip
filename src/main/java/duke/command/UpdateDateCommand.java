package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidUpdateException;
import duke.exception.DukeLoadingErrorException;
import duke.task.Task;

public class UpdateDateCommand extends UpdateCommand {

    /**
     * The new date of the task.
     */
    private String date;

    /**
     * Creates a new instance of a UpdateDateCommand with attributes defined
     * in the parameters.
     *
     * @param taskNo number of the task to be updated.
     * @param date new date of the task.
     */
    public UpdateDateCommand(int taskNo, String date) {
        super(taskNo);
        this.date = date;
    }

    /**
     * Executes a updateDate operation.
     *
     * @param taskList TaskList that the task is edited from.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     * @throws DukeInvalidUpdateException when attempting to update an invalid detail.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeLoadingErrorException, DukeInvalidUpdateException {
        Task updatedTask = taskList.updateTaskDate(super.taskNo, this.date);
        storage.save(taskList);
        String uiMessage = String.format(
                "Noted. I've updated this task:\n%s\n%s",
                updatedTask.toString(),
                taskList.getTaskSizeMessage());
        return ui.print(uiMessage);
    }
}
