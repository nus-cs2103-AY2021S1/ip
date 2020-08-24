package taskbot.command;

import taskbot.exceptions.InvalidIndexException;
import taskbot.exceptions.TaskAlreadyCompleteException;
import taskbot.exceptions.TaskbotException;
import taskbot.exceptions.WrongFormatException;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * Encapsulates the commands the user
 * can give TaskBot
 */
public abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws TaskbotException;

    public boolean isExit() {
        return isExit;
    };
}
