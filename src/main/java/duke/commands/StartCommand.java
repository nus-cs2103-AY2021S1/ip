package duke.commands;

import duke.MessageManager;
import duke.TaskManager;

/**
 * Represents a start command that welcomes the user.
 */
public class StartCommand extends Command {
    /**
     * Initializes a command that lists all tasks.
     *
     * @param input User input.
     */
    public StartCommand(String input) {
        this.input = input;
        this.isExit = false;
    }

    /**
     * Execution instructions for the command.
     * Welcomes the user and shows all current tasks and returns the message for Duke to show.
     *
     * @param taskManager TaskManager.
     * @return String response of command.
     */
    @Override
    public String execute(TaskManager taskManager) {
        String welcomeString = "Meow! I'm your personal cat assistant here to keep you productive.";
        String listString = MessageManager.getListMessage(taskManager);
        return String.format("%s\n%s", welcomeString, listString);
    }
}
