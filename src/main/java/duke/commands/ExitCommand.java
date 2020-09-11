package duke.commands;

import duke.task.TaskManager;

import duke.utils.Messages;

/**
 * Represents the command which will display the farewell message to the user upon execution.
 */

public class ExitCommand extends Command {

    /**
     * Returns a {@code CommandOutput} object with the farewell message.
     *
     * @param taskManager the {@code TaskManager} which handles all the actions with regards to the {@code Task} object.
     * @return the output from executing the command.
     */
    @Override
    public CommandOutput executeCommand(TaskManager taskManager) {
        return new CommandOutput(Messages.FAREWELL_MESSAGE, true);
    }
}
