package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Abstract class that models a command.
 */
public abstract class Command {
    private boolean shouldLoop;

    public Command(boolean shouldLoop) {
        this.shouldLoop = shouldLoop;
    }

    /**
     * Executes the command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    
    public boolean shouldLoop() {
        return this.shouldLoop;
    }
}