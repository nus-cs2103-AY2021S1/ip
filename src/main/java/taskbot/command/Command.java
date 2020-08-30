package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;

/**
 * Encapsulates the commands the user
 * can give TaskBot.
 */
public abstract class Command {
    // Flag for the ExitCommand
    private final boolean isExit;

    /**
     * Creates a Command.
     *
     * @param isExit Signifies whether it is an ExitCommand.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Performs the relevant operation tied to the Command type on the given task list.
     *
     * @param taskList The task list to be operated on.
     * @return a String describing the outcome of the execution.
     * @throws TaskbotException Any exception thrown by the Commands.
     */
    public abstract String execute(TaskList taskList) throws TaskbotException;

    /**
     * @return A boolean saying whether it is an ExitCommand.
     */
    public boolean isExit() {
        return isExit;
    };
}
