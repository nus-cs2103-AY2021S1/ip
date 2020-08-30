package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    private String error;
    
    /**
     * Creates an invalid command to alert the user of an error.
     *
     * @param error Error to be printed to user.
     */
    public InvalidCommand(String error) {
        this.error = error;
    }

    /**
     * Executes the invalid command.
     *
     * @param storage Storage of the bot.
     * @param tasks Task list of the bot.
     * @param ui Ui to print error message.
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printStatus(error);
    }
}
