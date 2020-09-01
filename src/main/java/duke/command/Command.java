package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command
 * All command types must extend from this class
 */
public abstract class Command {

    /**
     * Executes the command
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
}
