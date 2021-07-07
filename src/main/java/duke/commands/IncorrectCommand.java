package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Indicates that the command is invalid.
 */
public class IncorrectCommand extends Command {
    private final String messageToUser;

    /**
     * Constructor initialising messageToUser.
     * @param messageToUser Message to be printed for the user.
     */
    public IncorrectCommand(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    /**
     * Creates a CommandResult containing message to the user.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult noting that the command is invalid.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(messageToUser);
    }
}
