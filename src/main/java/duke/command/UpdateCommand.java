package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;

/**
 * UpdateCommand is the abstract superclass of all command classes that requires editing (except
 * adding new items) of the tasklist in the execute command
 */
public abstract class UpdateCommand extends Command {
    public UpdateCommand(CommandType commandType, String commandString) {
        super(commandType, commandString);
    }

    /**
     * Returns the index in the command string
     *
     * @return an int that represent the index in the command string
     * @throws DukeException when the index is invalid, i.e. when it cannot be parsed as an integer
     */
    public int getTaskTargetIndex() throws DukeException {
        return Parser.getTaskTargetIndex(super.getCommandType(), super.getCommandString());
    }
}
