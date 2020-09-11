package duke.commands;

import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates an unknown command.
 */

public class UnknownCommand extends Command {

    /**
     * Creates an UnknownCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public UnknownCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException {
        throw new UnknownCommandException();
    }
}
