package duke.command;

import duke.Bot;
import duke.task.TaskList;

/**
 * This Command makes Duke report an error to the user. No special formatting is added.
 */
public class ErrorCommand implements Command {
    private String message;

    /**
     * Creates an ErrorCommand.
     *
     * @param message the error message for Duke to show
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Bot bot, TaskList list) {
        bot.sayLine(message);
    }
}
