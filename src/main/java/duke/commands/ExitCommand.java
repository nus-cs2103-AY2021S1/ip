package duke.commands;

import duke.MessageManager;
import duke.TaskManager;

/**
 * Represents a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Initializes an exit command.
     *
     * @param input User input.
     */
    public ExitCommand(String input) {
        this.input = input;
        this.isExit = true;
    }

    /**
     * Execution instructions for the command.
     * Terminates Duke and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        return MessageManager.getByeMessage();
    }
}
