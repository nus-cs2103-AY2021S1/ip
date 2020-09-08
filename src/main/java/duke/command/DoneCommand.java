package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeInvalidIndexException;
import duke.exception.DukeLoadingErrorException;

import duke.task.Task;

/**
 * Represents a command that marks a task as completed.
 */
public class DoneCommand extends Command {

    /**
     * The number of the task to be completed.
     */
    private int taskNo;

    /**
     * Creates a new instance of a DoneCommand with attributes defined
     * in the parameters.
     *
     * @param taskNo number of the task to be completed.
     */
    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes a completeTask operation.
     *
     * @param taskList TaskList that the task is completed in.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     * @throws DukeInvalidIndexException If invalid index is given.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeLoadingErrorException, DukeInvalidIndexException {
        try {
            Task completedTask = taskList.completeTask(taskNo);
            storage.saveTasks(taskList);
            String uiMessage = String.format("Nice! I've marked this task as done:\n%s", completedTask.toString());
            return ui.print(uiMessage);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }
}
