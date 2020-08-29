package taskbot.command;

import taskbot.exceptions.TaskbotException;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

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
     * Executes the relevant operation tied to the Command type.
     * 
     * @param taskList The task list to be operated on.
     * @param ui The UI that can be operated on.
     * @throws TaskbotException Any exception thrown by the Commands.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws TaskbotException;

    /**
     * @return A boolean saying whether it is an ExitCommand.
     */
    public boolean isExit() {
        return isExit;
    };
}
