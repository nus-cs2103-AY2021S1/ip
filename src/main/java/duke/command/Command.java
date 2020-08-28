package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command.
 * @author Tee Kok Siang
 */
public abstract class Command {
    /** Indicates if this is an exit command */
    protected boolean isExit;
    /** Task for AddCommand */
    protected Task task;

    /**
     * Constructs a Command object.
     */
    protected Command() {
        this.isExit = false;
    }

    /**
     * Constructs a Command object.
     *
     * @param isExit Indicates if this is an exit command.
     */
    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns task.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Executes command.
     * An abstract method to be overridden by the subclasses.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}
