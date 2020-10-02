package shiro.command;

import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;

/**
 * represents a command to display the possible commands
 */
public class HelpCommand extends Command {

    /**
     * class constructor
     */
    public HelpCommand() {
        super("help");
    }

    /**
     * returns a command result containing the list of commands available to the user
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return a command result containing the list of commands available to the user
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        return new CommandResult(Message.helpMessage());
    }
}
