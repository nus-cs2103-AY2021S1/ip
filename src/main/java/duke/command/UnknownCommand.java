package duke.command;

import duke.exceptions.DukeUnknownInputException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;


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
    public CommandResult execute(TaskList tasks, Output output, Storage storage) throws DukeUnknownInputException {
        throw new DukeUnknownInputException();
    }

}
