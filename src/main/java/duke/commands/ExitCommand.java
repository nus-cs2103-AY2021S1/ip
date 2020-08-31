package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.StorageOperationException;

import duke.storage.Storage;

import duke.task.TaskManager;

import duke.utils.Colour;
import duke.utils.Messages;

/**
 * Represents the command which will display the farewell message to the user upon execution.
 */

public class ExitCommand extends Command {
    @Override
    public CommandOutput executeCommand(TaskManager taskManager, Storage storage) throws DukeException {
        try {
            storage.save(taskManager);
            return new CommandOutput(Messages.FAREWELL_MESSAGE, true);
        } catch (StorageOperationException e) {
            throw new DukeException(Colour.convertTextToRed(e.getMessage()));
        }
    }
}
