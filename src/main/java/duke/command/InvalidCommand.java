package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Throws an exception that is to be caught by the bot.
     *
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws DukeException To signify an invalid command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry I don't know what this means!");
    }
}
