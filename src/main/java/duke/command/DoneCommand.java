package duke.command;

import duke.*;
import duke.task.*;
import duke.exception.*;

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
     * @param taskNo number of the task to be completed.
     */
    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes a completeTask operation.
     * @param taskList TaskList that the task is completed in.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        Task completedTask = taskList.completeTask(taskNo);
        storage.save(taskList);
        String uiMessage = String.format("Nice! I've marked this task as done:\n%s", completedTask.toString());
        ui.print(uiMessage);
    }
}
