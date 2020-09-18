package meimei.command;

import meimei.Ui;
import meimei.botexception.BotException;
import meimei.Storage;
import meimei.TaskList;

/**
 * Represents a (bot) command that can be executed by the bot.
 */
public abstract class Command {

    /**
     * Executes the command as derived from the user's input.
     *
     * @param tasks The user's tasks.
     * @param storage Handles updating the hard disk accordingly.
     * @param ui
     * @throws BotException If a BotException is thrown in executing the command.
     * @return response to user
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws BotException;

    /**
     * Returns true when the command is an exit command and false otherwise.
     *
     * @return A boolean indicating if the command is an exit command.
     */
    public abstract boolean isExit();
}
