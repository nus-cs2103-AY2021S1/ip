package duke.commands;

import duke.TaskManager;
import duke.Ui;

/**
 * Represents an abstract command.
 */
public abstract class Command {

    public boolean isExit;
    String input;

    /**
     * Instructions to be executed for the command.
     *
     * @param taskManager the taskManager
     * @param ui          the ui to return output to
     */
    public abstract void execute(TaskManager taskManager, Ui ui);
}
