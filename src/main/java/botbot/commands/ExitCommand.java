package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

/**
 * Terminates the bot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param storage Storage to save task list to.
     * @param tasks Task list of the bot.
     * @param ui Ui to exit from.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.exit();
    }

    /**
     * Returns true if the given command is an exit command.
     * 
     * @param command Command.
     * @return True if given command is exit command, false otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
