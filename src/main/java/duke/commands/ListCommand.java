package duke.commands;

import duke.MessageManager;
import duke.TaskManager;

/**
 * Represents a command list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Initializes a command that lists all tasks.
     *
     * @param input User input.
     */
    public ListCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * List all tasks and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        String message = MessageManager.getListMessage(taskManager);
        return message;
    }
}
