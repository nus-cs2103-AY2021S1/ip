package duke.Command;

import duke.Exception.DukeException;
import duke.Exception.InvalidInputException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class InvalidCommand extends Command {

    public InvalidCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new InvalidInputException("I'm sorry, but I don't know what that means! :-(");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
