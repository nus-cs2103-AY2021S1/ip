package duke.command;

import duke.*;
import duke.task.*;
import duke.exception.*;

/**
 * Represents a command that adds a task.
 */
public class AddCommand extends Command {

    /**
     * The Task allocated to the Command.
     */
    private Task task;

    /**
     * Creates a new instance of an AddCommand with attributes defined
     * in the parameters.
     * @param task Task to be allocated.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes an addTask operation.
     * @param taskList TaskList that the task is added to.
     * @param ui Ui responsible for the operation.
     * @param storage Storage where the changes are written to.
     * @throws DukeLoadingErrorException If I/O operation fails during Storage#save.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        taskList.addTask(task);
        storage.save(taskList);
        String uiMessage = String.format(
                "Got it. I've added this task:\n%s\n%s",
                task.toString(),
                taskList.getTaskSizeMessage());
        ui.print(uiMessage);

    }
}
