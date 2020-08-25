package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents an UnknownCommand where user input is unclear.
 */
public class UnknownCommand extends Command {

    String gibberish;

    /**
     * Constructor for UnknownCommand class.
     *
     * @param gibberish Full input by the user.
     */
    public UnknownCommand(String gibberish) {
        this.gibberish = gibberish;
    }

    /**
     * Executes the command to inform user of unclear input.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @throws DukeException Always throws exception as input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(String.format("I'm sorry, but I don't know what \"%s\" means :-(", gibberish));
    }
}
