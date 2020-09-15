package botbot.commands;

import botbot.Main;
import botbot.utils.Storage;
import botbot.utils.TaskList;
import botbot.ui.Ui;

/**
 * Terminates the bot.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "bye";
    private static final int SUCCESS_STATUS_CODE = 0;

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
        Main.closeStage();
        System.exit(SUCCESS_STATUS_CODE);
        return Ui.exit();
    }
}
