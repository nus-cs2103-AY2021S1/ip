package duke.command;

import duke.*;
import duke.task.*;
import duke.exception.*;

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
     * @param taskNo number of the task to be deleted.
     */
    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes a deleteTask operation.
     * @param taskList TaskList that the task is deleted from.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        Task deletedTask = taskList.deleteTask(taskNo);
        storage.save(taskList);
        String uiMessage = String.format(
                "Noted. I've removed this task:\n%s\n%s",
                deletedTask.toString(),
                taskList.getTaskSizeMessage());
        ui.print(uiMessage);
    }
}
