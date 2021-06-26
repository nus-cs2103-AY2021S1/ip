package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This interface represents an action which Duke can take in response to user input.
 */
public interface Command {

    /**
     * Executes this Command.
     *
     * @param ui Ui which the Command can interact with.
     * @param list TaskList which the Command can read and modify.
     */
    public void execute(Ui ui, TaskList list);
}
