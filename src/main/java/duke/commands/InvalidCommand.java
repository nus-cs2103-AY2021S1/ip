package duke.commands;

import duke.TaskManager;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Initializes an invalid command.
     *
     * @param input User input.
     */
    public InvalidCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Returns an invalid command message.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        this.isError = true;
        return "I don't know what you just sent...";
    }
}
