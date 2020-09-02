package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the command that will be executed by Duke.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of {@link Task}s.
     * @param storage The Storage object of Duke.
     * @throws DukeException Exception when the execution encounters an error at any stage.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns a boolean to indicate if the Command is an exit command.
     *
     * @return Boolean flag that is false by default.
     */
    public boolean isExit() {
        return false;
    }
}
