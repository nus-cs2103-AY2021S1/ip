package duke.command;

import duke.*;
import duke.exception.*;

/**
 * Represents a command that cannot be understood by the chat bot.
 */
public class UnknownCommand extends Command {

    /**
     * Class constructor.
     */
    public UnknownCommand() {
        super("unknown");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeUnknownInputException {
        throw new DukeUnknownInputException();
    }

}
