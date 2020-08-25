package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command for the Duke program. These are commands that users can enter as input to interact with Duke.
 * There are several types of commands, namely: add, delete and done task commands, and exit, list and unknown commands.
 */
public class Command {

    /** Indicates if the command is telling the program to terminate */
    public boolean isExit = false;

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws DukeException If the command cannot be executed properly (is an invalid or unknown command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {}
}
