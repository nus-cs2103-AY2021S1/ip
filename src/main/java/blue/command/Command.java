package blue.command;

import blue.Storage;
import blue.exception.BlueException;
import blue.task.Tasks;
import blue.ui.Ui;

/**
 * Command determines whether the program should continue running and executes commands.
 */
public abstract class Command {
    /**
     * The Command type.
     */
    protected CommandType commandType;

    /**
     * True if program should terminate, false otherwise.
     *
     * @return the boolean.
     */
    protected boolean isExit() {
        return this.commandType == CommandType.BYE;
    }

    /**
     * Execute command and returns a message.
     *
     * @param tasks   the task list.
     * @param ui      interacts with user.
     * @param storage loads and save tasks.
     * @return the response to the command.
     * @throws BlueException If there is a blue exception thrown.
     */
    public abstract CommandResponse execute(Tasks tasks, Ui ui, Storage storage) throws BlueException;
}
