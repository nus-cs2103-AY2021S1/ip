package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

public class IncorrectCommand extends Command {
    private final String messageToUser;

    public IncorrectCommand(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(messageToUser);
    }
}
