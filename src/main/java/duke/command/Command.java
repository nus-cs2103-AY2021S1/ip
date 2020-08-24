package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Parent class of all supported commands
 */
public abstract class Command {

    public boolean isExit() {
        return false;
    }

    /**
     * Implements the execution of the command
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
