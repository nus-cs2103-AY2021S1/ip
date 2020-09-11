package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

/**
 * Terminates the bot.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "bye";
    
    /**
     * Executes the exit command.
     *
     * @param storage Storage to save task list to.
     * @param tasks Task list of the bot.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return Ui.exit();
    }
}
