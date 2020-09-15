package botbot.commands;

import botbot.utils.Storage;
import botbot.utils.TaskList;
import botbot.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;
    
    /**
     * Creates an invalid command to alert the user of an error.
     *
     * @param errorMessage Error to be printed to user.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the invalid command.
     *
     * @param storage Storage of the bot.
     * @param tasks Task list of the bot.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showErrorResponse(errorMessage);
    }
}
