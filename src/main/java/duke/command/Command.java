package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks list of tasks
     * @param ui object to output messages
     * @param storage object to write TaskList to file
     */
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException;

    /**
     * Checks if command will exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
