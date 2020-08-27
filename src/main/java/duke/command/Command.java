package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

/**
 * A Command represents an instruction to be executed by Duke.
 */
public abstract class Command {
    /**
     * Identifies of the Command results in the termination of Duke program.
     */
    public abstract boolean isExit();

    /**
     * Contains the execution instructions specific to each Command.
     *
     * @param tasks TaskList kept by Duke.
     * @param ui Ui object initialized by Duke.
     * @param storage Storage object initialized by Duke.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
