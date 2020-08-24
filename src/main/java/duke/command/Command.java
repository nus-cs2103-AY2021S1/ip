package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.tasks.TaskList;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes command
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @throws DukeException if exception encountered
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit() {
        return isExit;
    }
}
