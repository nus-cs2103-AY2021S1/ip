package shiro.command;

import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;
import javafx.application.Platform;

/**
 * represents a command to exit the application
 */
public class ExitCommand extends Command {

    /**
     * class constructor
     */
    public ExitCommand() {
        super("bye");
    }

    /**
     * causes the application to close.
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the application has been successfully shut down
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        Platform.exit();
        return new CommandResult(Message.exitMessage(), true);
    }
}
