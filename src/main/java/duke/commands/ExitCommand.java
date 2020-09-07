package duke.commands;

import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the command which will display the farewell message to the user upon execution.
 */

public class ExitCommand extends Command {
    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        return new CommandOutput(Messages.FAREWELL_MESSAGE, true);
    }
}
