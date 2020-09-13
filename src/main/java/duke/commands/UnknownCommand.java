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
     * Initialize an UnknownCommand object.
     *
     * @param inputArr Array of containing user's input.
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
