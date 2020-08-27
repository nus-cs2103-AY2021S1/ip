package Command;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Task.Task;

/**
 * Command is an abstract class that will execute user inputs from the Parser
 * and cause changes to the TaskList and Ui.
 * @author Joshua
 */
public abstract class Command {
    /**
     * This is the task that may be involved with the Command.
     */
    public Task task;

    /**
     * Carries out the given Command with the given TaskList, Ui and Storage. This is
     * an abstract method and needs to be overridden.
     * @param taskList the TaskList to be updated.
     * @param ui the Ui that interacts with the user.
     * @param storage the Storage that is updated with TaskList.
     * @throws DukeException throws exception that prints error message.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
