package duke.commands;

import static duke.utils.Messages.MESSAGE_BYE;

import duke.tasklist.TaskList;

/**
 * Represents the command that displays the goodbye message to the user when executed.
 */
public class ByeCommand extends Command {

    /**
     * Returns a CommandResult with a goodbye message.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(MESSAGE_BYE, true);
    }
}
