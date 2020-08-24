package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Tasks;

/**
 * Command determines whether the program should continue running and executes commands.
 */
public abstract class Command {
    /**
     * The Command type.
     */
    private CommandType commandType;

    /**
     * True if program should terminate, false otherwise.
     *
     * @return the boolean.
     */
    public boolean isExit() {
        return this.commandType == CommandType.BYE;
    }

    /**
     * Execute command.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @throws DukeException If there is a duke exception thrown.
     */
    public abstract void execute(Tasks tasks, Ui ui, Storage storage) throws DukeException;
}
