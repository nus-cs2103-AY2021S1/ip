package duke.command;

import duke.*;
import duke.exception.*;

/**
 * Represents command that is not recognised by chat bot.
 */
public class UnknownCommand extends Command {

    /**
     * Class constructor.
     * @param command String parsed by Parser object.
     */
    public UnknownCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeInvalidCommandException {
        throw new DukeInvalidCommandException();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnknownCommand;
    }
}
