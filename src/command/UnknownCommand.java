package command;

import exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.UnknownCommandException;

import java.util.Arrays;

/**
 * Represents a <code>Command</code> whose task is throwing an exception stating that the command is not defined.
 * The <code>UnknownCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class UnknownCommand extends Command {
    public UnknownCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Throws an exception stating that the command is not defined.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @throws DukeException In all cases to state that the command is not defined.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new UnknownCommandException();
    }

    /**
     * Returns false to indicate not to exit the Duke.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof UnknownCommand) {
            UnknownCommand other = (UnknownCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
