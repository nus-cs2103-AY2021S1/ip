package duke.command;

import duke.*;
import duke.exception.*;

public class UnknownCommand extends Command {

    public UnknownCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeInvalidCommandException {
        throw new DukeInvalidCommandException();
    }
}
