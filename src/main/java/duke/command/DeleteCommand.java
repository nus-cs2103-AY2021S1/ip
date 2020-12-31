package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeInvalidIndexException;
import duke.exception.DukeLoadingErrorException;

import duke.task.Task;

/**
 * Represents a command that deletes a task.
 */
public class DeleteCommand extends Command {

    /**
     * The number of the task to be deleted.
     */
    private int taskNo;

    /**
     * Creates a new instance of a DeleteCommand with attributes defined
     * in the parameters.
     *
     * @param taskNo number of the task to be deleted.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes a deleteTask operation.
     *
     * @param taskList TaskList that the task is deleted from.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     * @throws DukeInvalidIndexException If invalid index is given.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeLoadingErrorException, DukeInvalidIndexException {
        try {
            Task deletedTask = taskList.deleteTask(taskNo);
            storage.saveTasks(taskList);
            String uiMessage = String.format(
                    "Noted. I've removed this task:\n%s\n%s",
                    deletedTask.toString(),
                    taskList.getTaskSizeMessage());
            return ui.print(uiMessage);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }
}
