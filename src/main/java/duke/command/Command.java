package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * <p>duke.command.Command class defines the behaviour of a command entered by the user.</p>
 */
public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws DukeException;

    public abstract boolean isBye();
}
